package com.example.amir.shetu.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.AgentBuyerListAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.AgentBuyerListListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.AgentBuyerList;

import java.util.List;


public class AgentBuyerFragment extends Fragment implements NextPageListener, ProductClickListener, AgentBuyerListListener {

    private PaginationManager paginationManager;
    private int agentId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
    AgentBuyerListAdapter adapter;


    public AgentBuyerFragment() {
        // Required empty public constructor
    }


    public static AgentBuyerFragment newInstance() {
        AgentBuyerFragment fragment = new AgentBuyerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_agent_buyer, container, false);
        paginationManager=new PaginationManager<AgentBuyerList.Datum>(rootView,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", String.valueOf(agentId));
        ApiDataManager.getAgentBuyerList(this,agentId,1);

        return rootView;
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
        ApiDataManager.getAgentBuyerList(this,agentId,page);

    }

    @Override
    public void productClick(View productView, int productId) {

    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }

    @Override
    public void getAgentBuyerList(List<AgentBuyerList.Datum> agentbuyList,List<AgentBuyerList> agentBuyerList, int endpage, String errorMessage) {
        if (agentbuyList!=null){
//
          String total = agentBuyerList.get(0).getMessage();
            if(paginationManager.isInitialLoad()){
                adapter = new AgentBuyerListAdapter(agentbuyList,this,total,getContext());
                paginationManager.initialLoad(adapter,agentbuyList,endpage);
            }else {
                paginationManager.loadNext(agentbuyList);
            }
        }
    }
}
