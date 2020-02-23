package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.DeviceList;

import java.util.List;

public interface DeviceTokenListener {
    void getDeviceList(List<DeviceList> Token, String errorMessage);
}
