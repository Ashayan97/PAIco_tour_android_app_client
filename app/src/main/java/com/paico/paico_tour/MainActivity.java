package com.paico.paico_tour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private SignInUpPageTabAdapter signInUpPageTabAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth= FirebaseAuth.getInstance();
        accessCheck();
        findView();
        setSignInUpPageOpener();

    }

    private void accessCheck() {

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                    finish();
                }
            }
        };
    }

    private void findView() {
        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.table_layout);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void setSignInUpPageOpener(){
        getSupportFragmentManager().popBackStack();
        signInUpPageTabAdapter=new SignInUpPageTabAdapter(getSupportFragmentManager(), new Finisher() {
            @Override
            public void finishActivity() {
                finish();
            }
        });
        viewPager.setAdapter(signInUpPageTabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);

    }
}
