package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.PreorderOrderList;

import java.util.List;

public interface PreOrderOrderListListener {
    void getPreOrderOrderList(List<PreorderOrderList> preOrderOrderList , String error);
}
