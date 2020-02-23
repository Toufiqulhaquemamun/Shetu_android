package com.example.amir.shetu.interfaces.ApiDataManager;


import com.example.amir.shetu.model.SMEList;

import java.util.List;

public interface SMEListListenter {
    public void getSMEList(List<SMEList.SME> sme, int endPage, String errorMessage);
}
