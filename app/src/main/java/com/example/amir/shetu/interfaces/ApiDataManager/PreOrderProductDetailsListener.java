package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.CommodityPostDetail;

public interface PreOrderProductDetailsListener {
    void getPreorderPostDetail(CommodityPostDetail product, String errorMessage);
}
