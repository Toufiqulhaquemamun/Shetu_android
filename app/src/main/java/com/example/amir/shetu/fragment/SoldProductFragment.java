package com.example.amir.shetu.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.SoldProductAdapter;
import com.example.amir.shetu.dialog.SoldProductDialog;
import com.example.amir.shetu.interfaces.ApiDataManager.SoldProductListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public class SoldProductFragment extends Fragment implements NextPageListener,SoldProductListener,ProductClickListener {

    View rootView;

    private  Bundle bundle;

    private SoldProductDialog soldProductDialog;

    private FragmentManager fm;

    private static PaginationManager paginationManager;

    private int userId;

    public static SoldProductFragment newInstance() {
        return new SoldProductFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_sale_bids, null);

        paginationManager =new PaginationManager(rootView, PaginationManager.LINEAR_TYPE,this);

        paginationManager.showProgressBar();


        userId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);


        ApiDataManager.getSoldProduct(this,PaginationManager.startPage,userId);

        bundle = new Bundle();

        fm=getActivity().getFragmentManager();



        return rootView;
    }

    @Override
    public void setNextPageData(int page) {

        ApiDataManager.getSoldProduct(this,page,userId);
    }

    @Override
    public void getSoldProduct(List<SellHistoryDetail> products, int endPage, String errorMessage) {

        if(paginationManager.isInitialLoad()){

            SoldProductAdapter adapter=new SoldProductAdapter(products,this);

            paginationManager.initialLoad(adapter,products,endPage);
        }else {

            paginationManager.loadNext(products);
        }
    }

    @Override
    public void productClick(View itemView, int id) {

        bundle = new Bundle();

        bundle.putInt(PreferenceManager.PRODUCT_ID, id);

        soldProductDialog =new SoldProductDialog();

        soldProductDialog.setArguments(bundle);

        soldProductDialog.show(fm,"");
    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }
}
