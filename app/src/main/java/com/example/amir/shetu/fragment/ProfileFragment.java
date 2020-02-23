package com.example.amir.shetu.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;

public class ProfileFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);

        tabLayout = view.findViewById(R.id.tabs);
        viewPager =view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0 : return new PersonalInformationFragment();
                case 1 : return new OrganizationalInformationFragment();
                case 2 : return new AgentInformationFragment();
            }
            return null;
        }

        @Override
        public int getCount(){
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position){

            switch (position){
                case 0 : return getString(R.string.personal);
                case 1 : return getString(R.string.organizational);
                case 2 : return getString(R.string.agent);
            }
            return null;
        }

    }
}

