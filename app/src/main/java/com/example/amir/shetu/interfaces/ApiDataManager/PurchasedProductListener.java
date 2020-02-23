package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.SellHistoryDetail;

import java.util.List;

public interface PurchasedProductListener {

    void getPurchasedProduct(List<SellHistoryDetail> products, int endPage, String errorMessage);
}
