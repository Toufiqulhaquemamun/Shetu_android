package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

public class PreOrderCommodityPost {
    @SerializedName("id")
    private Integer id;
    @SerializedName("expectedQuantity")
    private Integer expectedQuantity;
    @SerializedName("remainingQuantity")
    private Integer remainingQuantity;
    @SerializedName("expectedPrice")
    private Double expectedPrice;
    @SerializedName("uploadTime")
    private String uploadTime;
    @SerializedName("unitId")
    private Integer unitId;
    @SerializedName("unit")
    private Unit unit;
    @SerializedName("gradeId")
    private Integer gradeId;
    @SerializedName("grade")
    private String grade;
    @SerializedName("commodityId")
    private Integer commodityId;
    @SerializedName("commodity")
    private Commodity commodity;
    @SerializedName("expectedDeliveryStartDate")
    private String expectedDeliveryStartDate;
    @SerializedName("expectedDeliveryEndDate")
    private String expectedDeliveryEndDate;
    @SerializedName("description")
    private String description;
    @SerializedName("deliveryLocationId")
    private Integer deliveryLocationId;
    @SerializedName("deliveryLocation")
    private Address deliveryLocation;
    @SerializedName("buyerId")
    private Integer buyerId;
    @SerializedName("buyer")
    private User buyer;
    @SerializedName("deliveryAgentId")
    private Integer deliveryAgentId;
    @SerializedName("deliveryAgent")
    private User deliveryAgent;
    @SerializedName("status")
    private Integer status;
    @SerializedName("bids")
    private Bid bids;


    public PreOrderCommodityPost(int expectedQuantity, double expectedPrice, Integer unitId, Integer gradeId, Integer commodityId, String expectedDeliveryStartDate, String expectedDeliveryEndDate, String description, Integer buyerId,Address deliveryLocation ) {
        this.commodityId = commodityId;
        this.buyerId = buyerId;
        this.gradeId = gradeId;
        this.unitId = unitId;
        this.expectedQuantity = expectedQuantity;
        this.expectedPrice = expectedPrice;
        this.description= description;
        this.expectedDeliveryStartDate = expectedDeliveryStartDate;
        this.expectedDeliveryEndDate = expectedDeliveryEndDate;
        this.deliveryLocation=deliveryLocation;

    }

    public Integer getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(Integer deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }

    public Integer getDeliveryAgentId() {
        return deliveryAgentId;
    }

    public void setDeliveryAgentId(Integer deliveryAgentId) {
        this.deliveryAgentId = deliveryAgentId;
    }

    public User getDeliveryAgent() {
        return deliveryAgent;
    }

    public void setDeliveryAgent(User deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }

    public Address getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(Address deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public Integer getId() {
        return id;
    }


    public Integer getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(Integer expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Double getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(Double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getExpectedDeliveryStartDate() {
        return expectedDeliveryStartDate;
    }

    public void setExpectedDeliveryStartDate(String expectedDeliveryStartDate) {
        this.expectedDeliveryStartDate = expectedDeliveryStartDate;
    }

    public String getExpectedDeliveryEndDate() {
        return expectedDeliveryEndDate;
    }

    public void setExpectedDeliveryEndDate(String expectedDeliveryEndDate) {
        this.expectedDeliveryEndDate = expectedDeliveryEndDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Bid getBids() {
        return bids;
    }

    public void setBids(Bid bids) {
        this.bids = bids;
    }
}
