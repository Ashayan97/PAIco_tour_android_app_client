package com.paico.paico_tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.paico.paico_tour.intefaces.Finisher;

public class MainActivity extends AppCompatActivity {
    private SignInUpPageTabAdapter signInUpPageTabAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accessCheck();
        findView();
        setSignInUpPageOpener();

    }

    private void accessCheck() {
//        String accessKey=MySharedPreferences.getInstance(this).getAccessKey();
//        if (accessKey!=null){
//            //TODO
//        }


        //TODO Remove later
        startActivity(new Intent(MainActivity.this, DrawerActivity.class));
        finish();
        //

    }

    private void findView() {
        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.table_layout);

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
