package com.paico.paico_tour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        accessCheck();

    }

    private void accessCheck() {
            if (isNetworkConnected()) {
                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser() != null) {
                            startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                            finish();
                        } else {
                            SigninFragment signinFragment = new SigninFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, signinFragment).commit();
                        }
                    }
                };
                mAuth.addAuthStateListener(mAuthListener);
            } else {
                Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
            }

    }


    @Override
    protected void onStart() {
        super.onStart();


    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

//    private void setSignInUpPageOpener(){
//        getSupportFragmentManager().popBackStack();
//        signInUpPageTabAdapter=new SignInUpPageTabAdapter(getSupportFragmentManager(), new Finisher() {
//            @Override
//            public void finishActivity() {
//                finish();
//            }
//        });
//        viewPager.setAdapter(signInUpPageTabAdapter);
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.setCurrentItem(1);
//
//    }
}
