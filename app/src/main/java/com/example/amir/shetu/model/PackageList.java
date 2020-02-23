package com.example.amir.shetu.model;

public class PackageList {

    private Integer id;
    private String name;
    private String bnName;
    private Integer commodityId;
    private Object commodity;
    private Integer costPerUnit;
    private Integer quantity;
    private Object bidPackagings;
    private Object preorderPackagings;

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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Object getCommodity() {
        return commodity;
    }

    public void setCommodity(Object commodity) {
        this.commodity = commodity;
    }

    public Integer getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Integer costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Object getBidPackagings() {
        return bidPackagings;
    }

    public void setBidPackagings(Object bidPackagings) {
        this.bidPackagings = bidPackagings;
    }

    public Object getPreorderPackagings() {
        return preorderPackagings;
    }

    public void setPreorderPackagings(Object preorderPackagings) {
        this.preorderPackagings = preorderPackagings;
    }

}
