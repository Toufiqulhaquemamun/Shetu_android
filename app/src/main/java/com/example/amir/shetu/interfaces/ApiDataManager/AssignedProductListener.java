package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.SMECommodityList;

import java.util.List;

public interface AssignedProductListener {
    void getAssignedProductList(List<SMECommodityList> products, String errorMessage);
}
