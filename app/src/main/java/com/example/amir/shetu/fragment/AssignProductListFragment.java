package com.example.amir.shetu.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.AssignProductAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.AssignedProductListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PreferenceManager;
import com.example.amir.shetu.manager.RecyclerViewManger;
import com.example.amir.shetu.model.SMECommodityList;

import java.util.List;


public class AssignProductListFragment extends Fragment implements AssignedProductListener,ProductClickListener{

    private View rootView;

    private RecyclerViewManger recyclerViewManger;

    private Bundle bundle;

    private FragmentManager fm;

    public static AssignProductListFragment newInstance() {

        return new AssignProductListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_assign_product_list, null);

        recyclerViewManger=new RecyclerViewManger(rootView,RecyclerViewManger.LINEAR_TYPE);

        recyclerViewManger.showProgressBar();

        int userId= PreferenceManager.getInstance().getInt(PreferenceManager.USER_ID);
        Log.d("ID", String.valueOf(userId));
        ApiDataManager.getAssignedProductList(this,userId);

        bundle=new Bundle();

        fm=getActivity().getFragmentManager();

        return rootView;
    }


    @Override
    public void getAssignedProductList(List<SMECommodityList> products, String errorMessage) {

        if(products.size()<1){
            recyclerViewManger.hideRecyclerView();
            recyclerViewManger.showEmptyView();
            recyclerViewManger.hideProgressBar();
        }else {
            AssignProductAdapter adapter=new AssignProductAdapter(products,this);
            recyclerViewManger.setAdapter(adapter);
            recyclerViewManger.showRecyclerView();
            recyclerViewManger.hideEmptyView();
            recyclerViewManger.hideProgressBar();
        }
    }

    @Override
    public void productClick(View itemView, int id) {
//        bundle=new Bundle();
//        bundle.putInt(PreferenceManager.PRODUCT_ID,id);
//        ProductInformationDialog informationDetails =new ProductInformationDialog();
//        informationDetails.setArguments(bundle);
//        informationDetails.show(fm,"");

    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }

    public interface ProductClickListener {

        void productClick(View productView, int productId,int bidId);
    }
}
