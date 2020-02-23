package com.example.amir.shetu.model;

import java.util.List;

public class SellingStatus
{
    public int id;

    public String status;

    public String displayName;

    public String displayBnName;

    public List<CommodityPost> commodityPosts;

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayBnName() {
        return displayBnName;
    }

    public List<CommodityPost> getCommodityPosts() {
        return commodityPosts;
    }
}
