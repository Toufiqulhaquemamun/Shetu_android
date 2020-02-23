package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.BiddedCommodityPostList;

import java.util.List;

public interface PurchaseBidsListener {

    void getPurchaseBids(List<BiddedCommodityPostList> bids, int endPage, String errorMessage);
}
