package com.example.amir.shetu.factory;

import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.example.amir.shetu.other.GridSpacingItemDecoration;


public class RecyclerViewLayoutFactory {


    public static String LINEAR_TYPE="linear";

    public static String GRID_TYPE="grid";


    protected RecyclerView.LayoutManager getLayoutManager(String type, RecyclerView recyclerView){

        RecyclerView.LayoutManager mLayoutManager=null;

        if(type.equals(LINEAR_TYPE)){

            mLayoutManager = new LinearLayoutManager(recyclerView.getContext());

        }else {

            mLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2);

            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10,recyclerView), true));
        }

        return mLayoutManager;
    }

    private int dpToPx(int dp,RecyclerView recyclerView) {

        Resources r = recyclerView.getResources();

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
