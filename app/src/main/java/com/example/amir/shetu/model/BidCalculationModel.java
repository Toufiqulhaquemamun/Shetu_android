package com.example.amir.shetu.model;

public class BidCalculationModel {
    private double totalPrice;
    private double vat;
    private double serviceCharge;
    private int quantity;
    private double shipCharge;
    private double netBalance;


    public BidCalculationModel(double totalPrice, double vat, double serviceCharge, int quantity, double shipCharge, double netBalance) {
        this.totalPrice = totalPrice;
        this.vat = vat;
        this.serviceCharge = serviceCharge;
        this.quantity = quantity;
        this.shipCharge = shipCharge;
        this.netBalance = netBalance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getShipCharge() {
        return shipCharge;
    }

    public void setShipCharge(double shipCharge) {
        this.shipCharge = shipCharge;
    }

    public double getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(double netBalance) {
        this.netBalance = netBalance;
    }
}
