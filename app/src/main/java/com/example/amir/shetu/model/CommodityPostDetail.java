package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommodityPostDetail {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("grade")
    private String grade;

    @SerializedName("description")
    private String description;

    @SerializedName("features")
    private String features;

    @SerializedName("quantity")
    private Double quantity;

    @SerializedName("price")
    private Double price;

    @SerializedName("status")
    private String status;

    @SerializedName("unit")
    private String unit;

    @SerializedName("sellerId")
    private Integer sellerId;

    @SerializedName("sellerName")
    private String sellerName;

    @SerializedName("sellerEmail")
    private String sellerEmail;

    @SerializedName("sellerPhone")
    private String sellerPhone;

    @SerializedName("buyerId")
    private Integer buyerId;

    @SerializedName("buyerName")
    private String buyerName;

    @SerializedName("buyerEmail")
    private String buyerEmail;

    @SerializedName("buyerPhone")
    private String buyerPhone;

    @SerializedName("organizationName")
    private String organizationName;

    @SerializedName("deliveryLocation")
    private String deliveryLocation;

 @SerializedName("uploadTime")
    private String uploadTime;

 @SerializedName("deliveryStartDate")
    private String deliveryStartDate;

 @SerializedName("deliveryEndDate")
    private String deliveryEndDate;

    @SerializedName("organizationLocation")
    private String organizationLocation;

    @SerializedName("images")
    private List<Image> images;

    @SerializedName("bid")
    private Bid bid;

    @SerializedName("commodityId")
    private Integer commodityId;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    public String getFeatures() {
        return features;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOrganizationLocation() {
        return organizationLocation;
    }

    public List<Image> getImages() {
        return images;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public String getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public Bid getBid() {
        return bid;
    }

    public Integer getCommodityId() {
        return commodityId;
    }
}


