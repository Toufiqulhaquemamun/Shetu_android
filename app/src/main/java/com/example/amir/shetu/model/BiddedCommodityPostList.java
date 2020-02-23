package com.example.amir.shetu.model;

public class BiddedCommodityPostList {

    public int id;

    public int quantity;

    public String unit;

    public double price;

    public String biddingTime;

    public String expiryTime;

    public String bidStatus;

    public String bidStatusDisplayName;

    public int commodityId;

    public String commodityName;

    public int sellerId;

    public String sellerName;

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public String getBiddingTime() {
        return biddingTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public String getBidStatusDisplayName() {
        return bidStatusDisplayName;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }
}
