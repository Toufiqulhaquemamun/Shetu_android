package com.example.amir.shetu.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.amir.shetu.R;

public class LoanApplyFragment extends Fragment {

    View rootView;

    public static LoanApplyFragment newInstance()
    {
        return new LoanApplyFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_loan_apply,null);
        ((Activity) rootView.getContext()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return rootView;
    }


    @Override
    public String toString() {
        return "ProfileSME";
    }
}
