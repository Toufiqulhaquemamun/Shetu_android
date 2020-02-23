package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.ReceiveableList;

import java.util.List;

public interface ReceiveableListListener {
    public void getReceiveablList(List<ReceiveableList.RcvProductList> receiveableList, int endPage, String errorMessage);
}
