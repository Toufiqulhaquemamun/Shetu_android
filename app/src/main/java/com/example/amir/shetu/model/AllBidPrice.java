package com.example.amir.shetu.model;

public class AllBidPrice {

    private int bidId;

    private String buyerName;

    private int buyerId;

    private double quantity;

    private String unit;

    private double buyerPrice;

    private double totalPrice;

    private String status;

    private String moneyReceipt;

    public String statusDisplayName;
    public double remainingQuantity ;

    public int getBidId() {
        return bidId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public double getBuyerPrice() {
        return buyerPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDisplayName() {

        return statusDisplayName;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public String getImage() {
        return moneyReceipt;
    }

    public String getMoneyReceipt() {
        return moneyReceipt;
    }

    public double getRemainingQuantity() {
        return remainingQuantity;
    }
}
