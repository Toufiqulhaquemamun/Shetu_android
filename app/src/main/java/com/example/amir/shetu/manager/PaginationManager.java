package com.example.amir.shetu.manager;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.amir.shetu.interfaces.other.NextPageListener;

import java.util.List;

public class PaginationManager<ProductType> extends RecyclerViewManger {

    private List<ProductType> productList;

    private LinearLayoutManager linearLayoutManager;

    private int visibleThreshold = 5;


    private int lastVisibleItem, totalItemCount;

    private boolean loading;

    public static int startPage=1;

    private  static int currentPage=startPage;

    private  static  int endPage;

    private NextPageListener listener;

    public PaginationManager(View rootView, String recyclerViewType, NextPageListener listener) {

        super(rootView,recyclerViewType);

        linearLayoutManager = (LinearLayoutManager) recyclerView
                .getLayoutManager();

        this.listener=listener;
    }


    private void createRecyclerAdapter() {
        recyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView,
                                           int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        totalItemCount = productList.size();

                        lastVisibleItem = linearLayoutManager

                                .findLastVisibleItemPosition();
                        if (!loading
                                && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                            if(currentPage<endPage){

                                listener.setNextPageData(++currentPage);

                            }
                            loading = true;
                        }
                    }
                });

        setLoaded();


    }


    public void initialLoad(RecyclerView.Adapter adapter, List<ProductType> productList, int endPage) {

        this.adapter=adapter;

        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);

        this.productList =productList;

        this.endPage=endPage;

        if(productList.size()>0){

            showRecyclerView();

            createRecyclerAdapter();

        }else {

            showEmptyView();

        }

        hideProgressBar();
    }



    private void setLoaded() {

        loading = false;
    }



    public void loadNext(List<ProductType> productList){

        addLoadingProgressBar();

        removeLoadingProgressBar();

        for (ProductType data:productList){

            this.productList.add(data);
        }

        setLoaded();
    }


    private void removeLoadingProgressBar() {

        productList.remove(productList.size() - 1);

        adapter.notifyItemRemoved(productList.size());
    }

    public static void initializePageNumber() {

        currentPage=startPage;

        endPage=2;
    }


    private void addLoadingProgressBar() {

        productList.add(null);

        adapter.notifyItemInserted(productList.size() - 1);
    }

    public boolean isInitialLoad(){

        if(this.adapter==null){
            return true;
        }else {
            return false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Fragment fragment){

        if(requestCode==StaticDataManager.START_BID_DETAILS && resultCode== Activity.RESULT_OK){

            refreshFragment(fragment);
        }
    }

}
