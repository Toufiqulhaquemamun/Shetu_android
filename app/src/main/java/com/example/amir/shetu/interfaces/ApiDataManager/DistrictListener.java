package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.District;

import java.util.List;

public interface DistrictListener {
    void getDistrictList(List<District> districts,String errormassage);
}
