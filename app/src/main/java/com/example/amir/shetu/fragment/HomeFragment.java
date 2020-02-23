package com.example.amir.shetu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.activity.HomeProductDetailsActivity;
import com.example.amir.shetu.activity.LoginActivity;
import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.HomeAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.HomeProductListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.model.CommodityPostList;

import java.util.List;

public class HomeFragment extends Fragment implements HomeProductListener,ProductClickListener,NextPageListener {

    private static PaginationManager paginationManager;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, null);

        paginationManager =new PaginationManager(rootView, PaginationManager.GRID_TYPE,this);

        paginationManager.showProgressBar();

        ApiDataManager.getHomeProduct(this,1);

        return rootView;
    }



    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        PaginationManager.initializePageNumber();
    }


    @Override
    public void getHomeProductInformation(List<CommodityPostList.Data> products, int endPage, String errorMessage) {

        if(products!=null){

            if(paginationManager.isInitialLoad()){

                HomeAdapter adapter=new HomeAdapter(products,this);

                paginationManager.initialLoad(adapter,products,endPage);

            }else {

                paginationManager.loadNext(products);
            }

        }
    }

    @Override
    public void productClick(View itemView, int id) {

        if(PreferenceManager.getInstance().getString(PreferenceManager.TOKEN)!=null){

            Intent intent = new Intent(getContext(), HomeProductDetailsActivity.class);

            PreferenceManager.getInstance().setInt(PreferenceManager.PRODUCT_ID,id);

            getContext().startActivity(intent);

        }else {

            Intent intent = new Intent(getContext(), LoginActivity.class);

            getContext().startActivity(intent);
        }
    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }

    @Override
    public void setNextPageData(int page) {

        ApiDataManager.getHomeProduct(this,page);
    }


}
