package com.example.amir.shetu.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.amir.shetu.R;
import com.example.amir.shetu.adapter.ProductGradeAdapter;
import com.example.amir.shetu.interfaces.ApiDataManager.ProductGradeListener;
import com.example.amir.shetu.interfaces.other.NextPageListener;
import com.example.amir.shetu.interfaces.other.ProductClickListener;
import com.example.amir.shetu.manager.ApiDataManager;
import com.example.amir.shetu.manager.PaginationManager;
import com.example.amir.shetu.model.PdffileList;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductGradeFragment extends Fragment implements NextPageListener, ProductGradeListener, ProductClickListener {


    private PaginationManager paginationManager;
    private API api= RetrofitInstance.getApi();
    private ProductGradeAdapter adapter;

    private ProgressBar progressBar;

    public ProductGradeFragment() {
        // Required empty public constructor
    }

    public static ProductGradeFragment newInstance() {
        ProductGradeFragment fragment = new ProductGradeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_product_grade, container, false);
        paginationManager=new PaginationManager<PdffileList>(rootView,PaginationManager.LINEAR_TYPE,this);
        Log.d("AgentId", "ProductGrade");
        ApiDataManager.getFileList(this,1);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PaginationManager.initializePageNumber();
    }

    @Override
    public void setNextPageData(int page) {
        ApiDataManager.getFileList(this,page);
    }

    @Override
    public void getFileList(List<PdffileList> files, int endPage, String errorMessage) {
        if (files!=null){
            if(paginationManager.isInitialLoad()){
                adapter = new ProductGradeAdapter(files,this,getContext());
                paginationManager.initialLoad(adapter,files,endPage);
            }else {
                paginationManager.loadNext(files);
            }
        }
    }

    @Override
    public void productClick(View productView, int productId) {
        PdffileList model = (PdffileList) productView.getTag();
    }

    @Override
    public <T> void itemClick(View productView, T item) {

    }
}
