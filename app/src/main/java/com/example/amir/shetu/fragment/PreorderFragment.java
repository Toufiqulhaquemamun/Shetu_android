package com.example.amir.shetu.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.activity.PreorderHomeDetailsActivity;
import com.example.amir.shetu.adapter.PreorderAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.PreorderListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.PreOrder;
import com.example.amir.shetu.model.PreorderList;

import java.util.Collections;
import java.util.List;


public class PreorderFragment extends Fragment implements NextPageListener, ProductClickListener, PreorderListener {

    private PaginationManager paginationManager;
    private int agentId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
    PreorderAdapter adapter;


    public PreorderFragment() {
       Log.d("PreOrder","");
    }


    public static PreorderFragment newInstance() {
        PreorderFragment fragment = new PreorderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_preorder, container, false);
        paginationManager=new PaginationManager<PreorderList>(rootView,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", String.valueOf(agentId));
        ApiDataManager.getPreorderList(this, 1);
        return rootView;
    }
    @Override
    public void getPreorderList(List<PreOrder> preorderList, int endpage, String errorMessage) {
        if (preorderList!=null){
//
            if(paginationManager.isInitialLoad()){
                adapter = new PreorderAdapter(preorderList,this);
                paginationManager.initialLoad(adapter, Collections.singletonList(preorderList),endpage);
            }else {
                paginationManager.loadNext(Collections.singletonList(preorderList));
            }
        }
    }


    @Override
    public void productClick(View productView, int productId) {
    }

    @Override
    public <T> void itemClick(View productView, T item) {
        PreOrder datum = (PreOrder) item;
        Log.d("data",datum.getDescription());
        Intent intent = new Intent(getContext(), PreorderHomeDetailsActivity.class);
        getContext().startActivity(intent);
        PreferenceManager.getInstance().setInt(PreferenceManager.PREORDER_PRODUCT_ID,datum.getId());
        PreferenceManager.getInstance().setInt(PreferenceManager.PREORDER_REMAIN_QUANTITY,datum.getRemainingQuantity());

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PaginationManager.initializePageNumber();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getPreorderList(this,page);

    }
}
