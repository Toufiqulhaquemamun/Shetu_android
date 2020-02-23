package com.example.amir.shetu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.TradeListAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.TradeListListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.TradeList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;


public class TradelistFragment extends Fragment implements NextPageListener, TradeListListener,ProductClickListener {

    private PaginationManager paginationManager;
    private int agentId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

    private API api= RetrofitInstance.getApi();

    public List<TradeList.TradelistSme> tradeList2;

    TradeListAdapter adapter;



    public TradelistFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TradelistFragment newInstance() {
        TradelistFragment fragment = new TradelistFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_tradelist, container, false);
        paginationManager=new PaginationManager<TradeList.TradelistSme>(rootView,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", String.valueOf(agentId));
        ApiDataManager.getTradeList(this,agentId,1);

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
        ApiDataManager.getTradeList(this,agentId,page);
    }

    @Override
    public void getTradeList(List<TradeList.TradelistSme> tradeList, int endPage, String errorMessage) {
        if (tradeList!=null){
            if(paginationManager.isInitialLoad()){
                adapter = new TradeListAdapter(tradeList,this);
                paginationManager.initialLoad(adapter,tradeList,endPage);


            }else {
                paginationManager.loadNext(tradeList);
            }
        }
    }

    @Override
    public void productClick(View productView, int position) {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();

    }

    @Override
    public <T> void itemClick(View productView, T item) {
        TradeList.TradelistSme sme = (TradeList.TradelistSme) item;

//        Log.d("Bid",sme.getStatus());
//        adapter.notifyDataSetChanged();

    }




}
