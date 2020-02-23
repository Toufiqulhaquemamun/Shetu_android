package com.example.amir.shetu.model;

public class PackCalculation {
    int position;
    int buyquantity;
    int costperunit;
    int bagamount;
    int quantityamount;
    double pricetotal;

    public PackCalculation(int position, int buyquantity, int costperunit, int bagamount, int quantityamount, double pricetotal) {
        this.position = position;
        this.buyquantity = buyquantity;
        this.costperunit = costperunit;
        this.bagamount = bagamount;
        this.quantityamount = quantityamount;
        this.pricetotal = pricetotal;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getBuyquantity() {
        return buyquantity;
    }

    public void setBuyquantity(int buyquantity) {
        this.buyquantity = buyquantity;
    }

    public int getCostperunit() {
        return costperunit;
    }

    public void setCostperunit(int costperunit) {
        this.costperunit = costperunit;
    }

    public int getBagamount() {
        return bagamount;
    }

    public void setBagamount(int bagamount) {
        this.bagamount = bagamount;
    }

    public int getQuantityamount() {
        return quantityamount;
    }

    public void setQuantityamount(int quantityamount) {
        this.quantityamount = quantityamount;
    }

    public double getPricetotal() {
        return pricetotal;
    }

    public void setPricetotal(double pricetotal) {
        this.pricetotal = pricetotal;
    }
}
