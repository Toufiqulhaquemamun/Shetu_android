package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

public class OrganizationCategory {

    @SerializedName("id")
    private Integer id;

    @SerializedName("OrganizationId")
    private Integer OrganizationId;

    @SerializedName("Organization")
    private Organization Organization;

    @SerializedName("CategoryId")
    private Integer CategoryId;

    @SerializedName("Category")
    public Category Category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        OrganizationId = organizationId;
    }

    public com.example.amir.shetu.model.Organization getOrganization() {
        return Organization;
    }

    public void setOrganization(Organization organization) {
        Organization = organization;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        Category = category;
    }



}
