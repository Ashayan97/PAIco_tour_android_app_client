package com.paico.paico_tour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.net.InetAddress;

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
        mAuth = FirebaseAuth.getInstance();
        accessCheck();
//        setSignInUpPageOpener();

    }

    private void accessCheck() {
        if (isInternetAvailable()) {
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() != null) {
                        startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                        finish();
                    } else {
                        SigninFragment signinFragment = new SigninFragment(new Finisher() {
                            @Override
                            public void finishActivity() {
                                finish();
                            }
                        });
                        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_fragment_container,signinFragment).addToBackStack(null).commit();
                    }
                }
            };
        } else {
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
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
