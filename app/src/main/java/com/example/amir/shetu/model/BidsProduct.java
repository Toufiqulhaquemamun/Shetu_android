package com.example.amir.shetu.model;

public class BidsProduct {
    private String buyerName;
    private String buyerQuantity;
    private String buyerPrice;
    private String totalPrice;
    private String action;

    public BidsProduct(String buyerName, String buyerQuantity, String buyerPrice, String totalPrice, String action) {
        this.buyerName = buyerName;
        this.buyerQuantity = buyerQuantity;
        this.buyerPrice = buyerPrice;
        this.totalPrice = totalPrice;
        this.action = action;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerQuantity() {
        return buyerQuantity;
    }

    public void setBuyerQuantity(String buyerQuantity) {
        this.buyerQuantity = buyerQuantity;
    }

    public String getBuyerPrice() {
        return buyerPrice;
    }

    public void setBuyerPrice(String buyerPrice) {
        this.buyerPrice = buyerPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
