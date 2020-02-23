package com.example.amir.shetu.model;

import java.util.List;

public class FileCategory {
    private Integer id;
    private String name;
    private String bnName;
    private String organizationCategories;
    private List<FileCommodity> commodities = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBnName() {
        return bnName;
    }

    public void setBnName(String bnName) {
        this.bnName = bnName;
    }

    public Object getOrganizationCategories() {
        return organizationCategories;
    }

    public void setOrganizationCategories(String organizationCategories) {
        this.organizationCategories = organizationCategories;
    }

    public List<FileCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<FileCommodity> commodities) {
        this.commodities = commodities;
    }
}
