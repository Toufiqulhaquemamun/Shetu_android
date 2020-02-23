package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.CommodityPostList;

import java.util.List;

public interface HomeProductListener {
    void getHomeProductInformation(List<CommodityPostList.Data> products, int endPage, String errorMessage);
}
