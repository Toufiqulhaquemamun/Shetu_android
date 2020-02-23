package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.CommodityPostList;

import java.util.List;

public interface SaleBidsListener {

    void getSaleBids(List<CommodityPostList.Data> bids, int endPage, String errorMessage);
}
