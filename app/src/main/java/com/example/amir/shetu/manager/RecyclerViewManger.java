package com.example.amir.shetu.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.amir.shetu.R;
import com.example.amir.shetu.factory.RecyclerViewLayoutFactory;

public  class RecyclerViewManger extends RecyclerViewLayoutFactory{

    protected RecyclerView.Adapter adapter;

    protected ProgressBar progressBar;

    protected ImageView warning_img;

    protected TextView warning_text;

    protected RecyclerView recyclerView;

    protected View rootView;



    public RecyclerViewManger(View rootView,String recyclerViewType) {

        this.rootView=rootView;

        initializeView();

        setUpRecyclerView(recyclerViewType);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private void initializeView() {

        recyclerView = rootView.findViewById(R.id.recycler_view);

        progressBar = rootView.findViewById(R.id.progressbar);

        warning_img= rootView.findViewById(R.id.warning_img);

        warning_text= rootView.findViewById(R.id.warning_text);
    }


    private void setUpRecyclerView(String recyclerViewType) {

        RecyclerView.LayoutManager mLayoutManager =getLayoutManager(recyclerViewType,recyclerView);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public void setAdapter(RecyclerView.Adapter adapter){

        this.adapter=adapter;

        recyclerView.setAdapter(adapter);
    }


    public void showRecyclerView(){

        recyclerView.setVisibility(View.VISIBLE);
    }


    public void hideRecyclerView(){
        recyclerView.setVisibility(View.GONE);
    }


    public void hideEmptyView(){

        warning_img.setVisibility(View.GONE);

        warning_text.setVisibility(View.GONE);
    }


    public void showEmptyView(){

        warning_img.setVisibility(View.VISIBLE);

        warning_text.setVisibility(View.VISIBLE);
    }


    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void refreshFragment(Fragment fragment){

        FragmentTransaction ft =  fragment.getFragmentManager().beginTransaction();

        ft.detach(fragment).attach(fragment).commit();
    }

}
