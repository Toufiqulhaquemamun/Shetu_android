package com.example.amir.shetu.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.ReceiveableListAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.ReceiveableListListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.ReceiveableList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;


public class ReceiveableFragment extends Fragment implements NextPageListener, ReceiveableListListener, ProductClickListener {
    private PaginationManager paginationManager;
    private int agentId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

    private API api= RetrofitInstance.getApi();

    public List<ReceiveableList.RcvProductList> receiveableList;
     ReceiveableListAdapter adapter;


    public ReceiveableFragment() {
        // Required empty public constructor
    }


    public static ReceiveableFragment newInstance() {
        ReceiveableFragment fragment = new ReceiveableFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_tradelist, container, false);
        paginationManager=new PaginationManager<>(rootView,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", String.valueOf(agentId));
        ApiDataManager.getReceiveablList(this,agentId,1);

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
        ApiDataManager.getReceiveablList(this,agentId,page);
    }

    @Override
    public void getReceiveablList(List<ReceiveableList.RcvProductList> receiveableList, int endPage, String errorMessage) {
        if (receiveableList!=null){
            if(paginationManager.isInitialLoad()){
                adapter = new ReceiveableListAdapter(receiveableList,this);
                paginationManager.initialLoad(adapter,receiveableList,endPage);


            }else {
                paginationManager.loadNext(receiveableList);
            }
        }
    }

    @Override
    public void productClick(View productView, int productId) {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
