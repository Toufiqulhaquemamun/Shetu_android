package com.example.amir.shetu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.activity.PurchaseBidDetails;
import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.PurchaseBidsAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.PurchaseBidsListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.PurchasedBidsClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.StaticDataManager;
import com.example.amir.shetu.model.BiddedCommodityPostList;

import java.util.List;

public class PurchaseBidsFragment extends Fragment implements PurchaseBidsListener,NextPageListener,PurchasedBidsClickListener{

//    private View rootView;

    private static PaginationManager paginationManager;

    private int userId;


    public static PurchaseBidsFragment newInstance()
    {
        return new PurchaseBidsFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_puchase_bid,null);

        paginationManager =new PaginationManager(rootView, PaginationManager.LINEAR_TYPE,this);

        paginationManager.showProgressBar();


        userId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        ApiDataManager.getPurchaseBids(this,PaginationManager.startPage,userId);

        return rootView;
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        paginationManager.onActivityResult(requestCode,resultCode,PurchaseBidsFragment.this);


    }


    @Override
    public void getPurchaseBids(List<BiddedCommodityPostList> bids, int endPage, String errorMessage) {

        if(paginationManager.isInitialLoad()){

            PurchaseBidsAdapter adapter=new PurchaseBidsAdapter(bids,this);

            paginationManager.initialLoad(adapter,bids,endPage);
        }else {

            paginationManager.loadNext(bids);
        }
    }

    @Override
    public void setNextPageData(int page) {

        ApiDataManager.getPurchaseBids(this,page,userId);
    }

    @Override
    public void PurchasedBids(View productView, int productId, int bidId) {

        Intent intent = new Intent(getContext(), PurchaseBidDetails.class);

        intent.putExtra(PreferenceManager.BID_ID, bidId);

        intent.putExtra(PreferenceManager.PRODUCT_ID, productId);

        startActivityForResult(intent, StaticDataManager.START_BID_DETAILS);
    }
}

