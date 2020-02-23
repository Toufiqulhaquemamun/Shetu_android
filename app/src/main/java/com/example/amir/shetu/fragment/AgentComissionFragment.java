package com.example.amir.shetu.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.AgentComissionListAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.AgentComissionListListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.AgentComission;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgentComissionFragment extends Fragment implements NextPageListener, AgentComissionListListener {

    private PaginationManager paginationManager;
    private TextView agentComisssion;
    private int agentId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
    private API api= RetrofitInstance.getApi();
    AgentComissionListAdapter adapter;


    public AgentComissionFragment() {
        // Required empty public constructor
    }

    public static AgentComissionFragment newInstance()
    {
        AgentComissionFragment fragment = new AgentComissionFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_agent_comission,container,false);
        agentComisssion=rootview.findViewById(R.id.product_totalprice);
        paginationManager = new PaginationManager<AgentComission>(rootview,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", String.valueOf(agentId));
        ApiDataManager.getAgentComissionList(this,agentId);
        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PaginationManager.initializePageNumber();
    }

    @Override
    public void getAgentComissionList(List<AgentComission> agentComissionList, int endpage, String errorMessage) {
        if(agentComissionList!=null)
        {
            int sum = 0;
            for (int i = 0; i < agentComissionList.size(); i++) {
                sum += agentComissionList.get(i).getAgentAmount();
                Log.d("Am", String.valueOf(sum));
                agentComisssion.setText(String.valueOf(sum));
            }
            if(paginationManager.isInitialLoad())
            {
                adapter= new AgentComissionListAdapter(agentComissionList,this,getContext());
                paginationManager.initialLoad(adapter,agentComissionList,endpage);
            }
            else {
                paginationManager.loadNext(agentComissionList);
            }
        }
    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getAgentComissionList(this,agentId);
    }
}
