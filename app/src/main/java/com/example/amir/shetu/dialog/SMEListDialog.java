package com.example.amir.shetu.dialog;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.databinding.SmeListDialogBinding;
import com.example.amir.shetu.model.SMEList;

public class SMEListDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.sme_list_dialog,container,false);

        SmeListDialogBinding binding = DataBindingUtil.bind(v);
        SMEList.SME sme= (SMEList.SME) getArguments().getSerializable("sme");
        binding.setSme(sme);
        return v;
    }
}
