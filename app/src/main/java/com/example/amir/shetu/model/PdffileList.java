package com.example.amir.shetu.model;

public class PdffileList {

    private Integer id;
    private String name;
    private String bnName;
    private String description;
    private String bnDescription;
    private String gradeDocument;
    private Integer categoryId;
    private FileCategory category;
    private String commodityGrades;
    private String smeAssaignedCommodities;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public FileCategory getCategory() {
        return category;
    }

    public void setCategory(FileCategory category) {
        this.category = category;
    }

    public String getCommodityGrades() {
        return commodityGrades;
    }

    public void setCommodityGrades(String commodityGrades) {
        this.commodityGrades = commodityGrades;
    }

    public String getSmeAssaignedCommodities() {
        return smeAssaignedCommodities;
    }

    public void setSmeAssaignedCommodities(String smeAssaignedCommodities) {
        this.smeAssaignedCommodities = smeAssaignedCommodities;
    }
}
