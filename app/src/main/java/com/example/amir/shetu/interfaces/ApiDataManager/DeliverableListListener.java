package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.DeliverableList;

import java.util.List;

public interface DeliverableListListener {
    public void getDeliverablelList(List<DeliverableList.Datum> deliverableList, int endPage, String errorMessage);
}
