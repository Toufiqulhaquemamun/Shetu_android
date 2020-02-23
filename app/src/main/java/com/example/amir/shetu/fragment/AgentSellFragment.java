package com.example.amir.shetu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.AgentSellerListAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.AgentSellerListListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.AgentSellerList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;


public class AgentSellFragment extends Fragment implements NextPageListener, AgentSellerListListener, ProductClickListener {

    private PaginationManager paginationManager;
    private int agentId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
    private API api= RetrofitInstance.getApi();
    AgentSellerListAdapter adapter;


    public AgentSellFragment() {
        // Required empty public constructor
    }


    public static AgentSellFragment newInstance() {
        AgentSellFragment fragment = new AgentSellFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_tradelist, container, false);
        paginationManager=new PaginationManager<AgentSellerList.Datum>(rootView,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", String.valueOf(agentId));
        ApiDataManager.getAgentSellerList(this,agentId,1);

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
    public void getAgentSellerList(List<AgentSellerList.Datum> agentsellList,List<AgentSellerList> agentsellerList, int endpage, String error) {
        if (agentsellList!=null){
            String totalPrice = agentsellerList.get(0).getMessage();
            Log.d("Amount", agentsellerList.get(0).getMessage());
            if(paginationManager.isInitialLoad()){
                adapter = new AgentSellerListAdapter(agentsellList,this,getContext(),totalPrice);
                paginationManager.initialLoad(adapter,agentsellList,endpage);
            }else {
                paginationManager.loadNext(agentsellList);
            }
        }

    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getAgentSellerList(this,agentId,page);
    }

    @Override
    public void productClick(View productView, int productId) {

    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }
}
