package com.example.amir.shetu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.activity.SaleBidsDetailsActivity;
import com.example.amir.shetu.adapter.SaleBidsAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.SaleBidsListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.CommodityPostList;

import java.util.List;

public class SaleBidsFragment extends Fragment implements SaleBidsListener,ProductClickListener ,NextPageListener{

    private static PaginationManager paginationManager;

    private int userId;

    public static SaleBidsFragment newInstance() {

        return new SaleBidsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sale_bids, null);

        paginationManager =new PaginationManager(rootView, PaginationManager.GRID_TYPE,this);

        paginationManager.showProgressBar();

        userId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        ApiDataManager.getSaleBidsTemp(this, PaginationManager.startPage,userId);

        Log.d("Sell Bids","SaleBidsFragment");

        return rootView;
    }

    @Override
    public void getSaleBids(List<CommodityPostList.Data> bids, int endPage, String errorMessage) {

        if(paginationManager.isInitialLoad()){

            SaleBidsAdapter adapter=new SaleBidsAdapter(bids,this);

            paginationManager.initialLoad(adapter,bids,endPage);

        }else {

            paginationManager.loadNext(bids);
        }



    }

    @Override
    public void productClick(View itemView, int id) {

        Intent intent = new Intent(getContext(), SaleBidsDetailsActivity.class);

        intent.putExtra(PreferenceManager.USER_ID, id);

        startActivity(intent);
    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getSaleBidsTemp(this,page,userId);
    }
}
