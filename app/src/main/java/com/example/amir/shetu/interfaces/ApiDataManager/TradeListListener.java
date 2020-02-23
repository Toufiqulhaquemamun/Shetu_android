package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.TradeList;

import java.util.List;

public interface TradeListListener {
    public void getTradeList(List<TradeList.TradelistSme> tradeList, int endPage, String errorMessage);
}
