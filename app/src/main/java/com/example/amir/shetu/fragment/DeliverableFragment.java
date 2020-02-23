package com.example.amir.shetu.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.DeliverableListAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.DeliverableListListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.DeliverableList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliverableFragment extends Fragment implements NextPageListener, ProductClickListener, DeliverableListListener {

    private PaginationManager paginationManager;
    private int agentId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

    private API api= RetrofitInstance.getApi();

    DeliverableListAdapter adapter;

    public DeliverableFragment() {
        // Required empty public constructor
    }

    public static DeliverableFragment newInstance()
    {
        DeliverableFragment fragment = new DeliverableFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_deliverable, container, false);
        paginationManager=new PaginationManager<>(rootView,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", String.valueOf(agentId));
        ApiDataManager.getDeliverablelList(this, agentId, 1);
        return rootView;
    }

    @Override
    public void getDeliverablelList(List<DeliverableList.Datum> deliverableList, int endPage, String errorMessage) {
        if (deliverableList!=null){
            if(paginationManager.isInitialLoad()){
                adapter = new DeliverableListAdapter(deliverableList,this);
                paginationManager.initialLoad(adapter,deliverableList,endPage);


            }else {
                paginationManager.loadNext(deliverableList);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PaginationManager.initializePageNumber();
    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getDeliverablelList(this,agentId,page);

    }

    @Override
    public void productClick(View productView, int productId) {

    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }
}
