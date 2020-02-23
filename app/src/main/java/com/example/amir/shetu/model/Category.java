package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("id")
    private Integer id;

    @SerializedName("bnName")
    private String bnName;



    @SerializedName("OrganizationCategories")
    public List<OrganizationCategory> OrganizationCategories;

    @SerializedName("commodities")
    private List<Commodity> commodities;


    @SerializedName("id")
    public Integer getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBnName() {
        return bnName;
    }

    public void setBnName(String bnName) {
        this.bnName = bnName;
    }

    public List<OrganizationCategory> getOrganizationCategories() {
        return OrganizationCategories;
    }

    public void setOrganizationCategories(List<OrganizationCategory> organizationCategories) {
        OrganizationCategories = organizationCategories;
    }


    @SerializedName("commodities")
    public Object getCommodities() {
        return commodities;
    }

    @SerializedName("commodities")
    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }



}
