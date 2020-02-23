package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

public class SellHistoryDetail {

        @SerializedName("id")
        private Integer id;

        @SerializedName("quantity")
        private double quantity;

        @SerializedName("askingPrice")
        private double askingPrice;

        @SerializedName("sellingPrice")
        private double sellingPrice;

        @SerializedName("vat")
        private double vat;

        @SerializedName("serviceCharge")
        private double serviceCharge;

        @SerializedName("shippingCost")
        private double shippingCost;

        @SerializedName("total")
        private double total;

        @SerializedName("soldTime")
        private String soldTime;

        @SerializedName("unit")
        private String unit;

        @SerializedName("commodityName")
        private String commodityName;

        @SerializedName("bidTime")
        private String productBidTime;

        @SerializedName("sellerName")
        private String sellerName;

        @SerializedName("buyerName")
        private String buyerName;

    public Integer getId() {
        return id;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getVat() {
        return vat;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getTotal() {
        return total;
    }

    public String getSoldTime() {
        return soldTime;
    }

    public String getUnit() {
        return unit;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public String getProductBidTime() {
        return productBidTime;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }
}
