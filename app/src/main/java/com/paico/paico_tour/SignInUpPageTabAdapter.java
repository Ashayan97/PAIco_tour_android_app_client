package com.paico.paico_tour;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SignInUpPageTabAdapter extends FragmentPagerAdapter {
    private Finisher finisher;
    private String tabNames[] = {"Sign UP", "Sing In"};

    public SignInUpPageTabAdapter(FragmentManager fm,Finisher finisher) {
        super(fm);
        this.finisher=finisher;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 1)
            return new SigninFragment(finisher);
        else return new SignupFragment(finisher);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
