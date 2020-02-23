package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaleableProductBidDetails {

    @SerializedName("productId")
    private int productId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("description")
    private String description;

    @SerializedName("detail")
    private String detail;

    @SerializedName("quantity")
    private double quantity;

    @SerializedName("unit")
    private String unit;

    @SerializedName("askingPrice")
    private double askingPrice;

    @SerializedName("total")
    private double total;

    @SerializedName("images")
    private List<Image> images;

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getDetail() {
        return detail;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public double getTotal() {
        return total;
    }

    public List<Image> getImages() {
        return images;
    }
}
