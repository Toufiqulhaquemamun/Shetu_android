package com.example.amir.shetu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Commodity implements Serializable  {

    public int id;


    public String name;


    public String bnName;


    public String description;


    public String bnDescription;


    public String gradeDocument;


    public int categoryId;


    public Category category;


    public List<CommodityGrade> commodityGrades;


    public List<SMEAssaignedCommodity> SMEAssaignedCommodities;



    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBnDescription() {
        return bnDescription;
    }

    public void setBnDescription(String bnDescription) {
        this.bnDescription = bnDescription;
    }

    public String getGradeDocument() {
        return gradeDocument;
    }

    public void setGradeDocument(String gradeDocument) {
        this.gradeDocument = gradeDocument;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CommodityGrade> getCommodityGrades() {
        return commodityGrades;
    }

    public void setCommodityGrades(List<CommodityGrade> commodityGrades) {
        this.commodityGrades = commodityGrades;
    }

    public List<SMEAssaignedCommodity> getSMEAssaignedCommodities() {
        return SMEAssaignedCommodities;
    }

    public void setSMEAssaignedCommodities(List<SMEAssaignedCommodity> SMEAssaignedCommodities) {
        this.SMEAssaignedCommodities = SMEAssaignedCommodities;
    }


}
