package com.example.amir.shetu.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.PurchaseProductAdapter;
import com.example.amir.shetu.dialog.PurchaseProductDialog;
import com.example.amir.shetu.interfaces.ApiDataManager.PurchasedProductListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public class PurchaseProductFragment extends Fragment implements ProductClickListener,PurchasedProductListener,NextPageListener{


    private PurchaseProductAdapter adapter;

    private  Bundle bundle;

    private int userId;

    private PaginationManager paginationManager;


    private FragmentManager fm;

    private View rootView;

    public static PurchaseProductFragment newInstance() {
        return new PurchaseProductFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_puchase_bid, null);

        bundle = new Bundle();

        fm=getActivity().getFragmentManager();

        userId = PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);

        paginationManager=new PaginationManager(rootView,PaginationManager.LINEAR_TYPE,this);

        paginationManager.showProgressBar();

        ApiDataManager.getPurchasedProduct(this,PaginationManager.startPage,userId);

        return rootView;
    }

    @Override
    public void productClick(View productView, int productId) {

        bundle = new Bundle();

        bundle.putInt(PreferenceManager.PRODUCT_ID, productId);

        PurchaseProductDialog purchaseProductDialog =new PurchaseProductDialog();

        purchaseProductDialog.setArguments(bundle);

        purchaseProductDialog.show(fm,"");
    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }

    @Override
    public void getPurchasedProduct(List<SellHistoryDetail> products, int endPage, String errorMessage) {

        if(paginationManager.isInitialLoad()){

            PurchaseProductAdapter adapter=new PurchaseProductAdapter(products,this);

            paginationManager.initialLoad(adapter,products,endPage);
        }else {

            paginationManager.loadNext(products);
        }

    }

    @Override
    public void setNextPageData(int page) {

        ApiDataManager.getPurchasedProduct(this,page,userId);
    }
}

