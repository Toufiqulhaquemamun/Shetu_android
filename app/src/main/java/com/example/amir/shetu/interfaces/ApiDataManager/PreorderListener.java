package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.PreOrder;

import java.util.List;


public interface PreorderListener {
    public void getPreorderList(List<PreOrder> preorderList, int endpage, String errorMessage);
}
