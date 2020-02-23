package com.example.amir.shetu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.SMEListAdapter;
import com.example.amir.shetu.dialog.SMEListDialog;
import com.example.amir.shetu.interfaces.ApiDataManager.SMEListListenter;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.SMEList;

import java.util.List;


public class SMEListFragment extends Fragment implements NextPageListener,SMEListListenter,ProductClickListener {

    private PaginationManager paginationManager;
    private int userId=PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
    public SMEListFragment() {
        // Required empty public constructor
    }

    public static SMEListFragment newInstance() {
        SMEListFragment fragment = new SMEListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_smelist, container, false);
        paginationManager=new PaginationManager<SMEList.SME>(rootView,PaginationManager.LINEAR_TYPE,this);

        ApiDataManager.getSMEList(this,userId,1);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PaginationManager.initializePageNumber();
    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getSMEList(this,userId,page);
    }

    @Override
    public void getSMEList(List<SMEList.SME> sme, int endPage, String errorMessage) {
        if (sme!=null){
            if(paginationManager.isInitialLoad()){
                paginationManager.initialLoad(new SMEListAdapter(sme,this),sme,endPage);
            }else {
                paginationManager.loadNext(sme);
            }
        }

    }


    @Override
    public void productClick(View productView, int productId) {

    }

    @Override
    public <T> void itemClick(View productView, T item) {
        SMEList.SME sme= (SMEList.SME) item;
        SMEListDialog dialog=new SMEListDialog();
        Bundle bundle=new Bundle();
        bundle.putSerializable("sme",sme);
        dialog.setArguments(bundle);
        dialog.show(getActivity().getFragmentManager(),"");
    }
}
