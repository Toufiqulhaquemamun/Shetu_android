package com.example.amir.shetu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amir.shetu.R;
import com.example.amir.shetu.activity.PreOrderAllBidsActivity;
import com.example.amir.shetu.adapter.PreOrderPurchaseBidsAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.PreOrderOrderListListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.PreorderOrderList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreOrderPurchaseBidsFragment extends Fragment implements NextPageListener, PreOrderOrderListListener,PreOrderPurchaseBidsAdapter.Listener {

    private static PaginationManager paginationManager;

    private int userId;


    public PreOrderPurchaseBidsFragment() {
        // Required empty public constructor
    }
    public static PreOrderPurchaseBidsFragment newInstance()
    {
       return new PreOrderPurchaseBidsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_puchase_bid,container,false);
        paginationManager =new PaginationManager(rootView, PaginationManager.LINEAR_TYPE,this);
        paginationManager.showProgressBar();
        userId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
        ApiDataManager.getPreOrderOrderList(this,userId);
        return rootView;

    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getPreOrderOrderList(this,userId);
    }

    @Override
    public void getPreOrderOrderList(List<PreorderOrderList> preOrderOrderList, String error) {

        if(paginationManager.isInitialLoad()){

            PreOrderPurchaseBidsAdapter adapter=new PreOrderPurchaseBidsAdapter(preOrderOrderList,this);

            paginationManager.initialLoad(adapter,preOrderOrderList,1);
        }else {

            paginationManager.loadNext(preOrderOrderList);
        }
    }


    @Override
    public void itemClick(View itemView, String name, int bidId, int pos) {
        if(name.equals(StaticDataManager.ALL_BIDS))
        {
            Intent intent = new Intent(getContext(), PreOrderAllBidsActivity.class);
            //intent.putExtra(PreferenceManager.BID_ID, bidId);

            intent.putExtra(PreferenceManager.PREORDER_PRODUCT_ID, bidId);

            startActivityForResult(intent, StaticDataManager.START_BID_DETAILS);
        }
        else if(name.equals(StaticDataManager.STARTBID))
        {

            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


    @Override
    public <T> void productClick(View itemView, T item) {

    }
}
