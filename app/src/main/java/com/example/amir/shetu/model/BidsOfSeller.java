package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BidsOfSeller {
    @SerializedName("id")
    private Integer id;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("price")
    private double price;

    @SerializedName("moneyReciept")
    private String moneyReciept;

    @SerializedName("depositInfo")
    private String depositInfo;

    @SerializedName("vat")
    private double vat;

    @SerializedName("serviceCharge")
    private double serviceCharge;

    @SerializedName("shippingCharge")
    private double shippingCharge;

    @SerializedName("deliveryLocationId")
    private Integer deliveryLocationId;

    @SerializedName("deliveryLocation")
    private Address deliveryLocation;

    @SerializedName("biddingTime")
    private String biddingTime;

    @SerializedName("approvedTime")
    private String approvedTime;

    @SerializedName("lockTime")
    private String lockTime;

    @SerializedName("expiryTime")
    private String expiryTime;

    @SerializedName("moneyReceiptUploadTime")
    private String moneyReceiptUploadTime;

    @SerializedName("moneyReceiptVerifyTime")
    private String moneyReceiptVerifyTime;

    @SerializedName("productVerifyTime")
    private String productVerifyTime;

    @SerializedName("shippingDate")
    private String shippingDate;

    @SerializedName("agentReceivedTime")
    private String agentReceivedTime;

    @SerializedName("deliveryCode")
    private Integer deliveryCode;

    @SerializedName("deliveryCodeTried")
    private Integer deliveryCodeTried;


    @SerializedName("bidStatusId")
    private int bidStatusId;

    @SerializedName("bidStatus")
    private BidStatus bidStatus;

    @SerializedName("deliveryAgentId")
    private Integer deliveryAgentId;

    @SerializedName("deliveryAgent")
    private User deliveryAgent;

    @SerializedName("commodityPostId")
    private Integer commodityPostId;

    @SerializedName("CommodityPost")
    private CommodityPost CommodityPost;

    @SerializedName("commodityId")
    private Integer commodityId;

    @SerializedName("commodity")
    private Commodity commodity;

    @SerializedName("gradeId")
    private Integer gradeId;

    @SerializedName("grade")
    private CommodityGrade grade;

    @SerializedName("unitId")
    private Integer unitId;

    @SerializedName("unit")
    private Unit unit;

    @SerializedName("preOrderId")
    private Integer preOrderId;

    @SerializedName("preOrder")
    private PreOrder preOrder;

    @SerializedName("buyerId")
    private Integer buyerId;

    @SerializedName("buyer")
    private User buyer;

    @SerializedName("sellerId")
    private Integer sellerId;

    @SerializedName("seller")
    private User seller;

    @SerializedName("bidType")
    private Integer bidType;

    @SerializedName("bidImages")
    private List<BidImage> bidImages = null;

    private String comment;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMoneyReciept() {
        return moneyReciept;
    }

    public void setMoneyReciept(String moneyReciept) {
        this.moneyReciept = moneyReciept;
    }

    public String getDepositInfo() {
        return depositInfo;
    }

    public void setDepositInfo(String depositInfo) {
        this.depositInfo = depositInfo;
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

    public double getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(double shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public Integer getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(Integer deliveryLocationId) {
        this.deliveryLocationId = deliveryLocationId;
    }

    public Address getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(Address deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getBiddingTime() {
        return biddingTime;
    }

    public void setBiddingTime(String biddingTime) {
        this.biddingTime = biddingTime;
    }

    public String getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getMoneyReceiptUploadTime() {
        return moneyReceiptUploadTime;
    }

    public void setMoneyReceiptUploadTime(String moneyReceiptUploadTime) {
        this.moneyReceiptUploadTime = moneyReceiptUploadTime;
    }

    public String getMoneyReceiptVerifyTime() {
        return moneyReceiptVerifyTime;
    }

    public void setMoneyReceiptVerifyTime(String moneyReceiptVerifyTime) {
        this.moneyReceiptVerifyTime = moneyReceiptVerifyTime;
    }

    public String getProductVerifyTime() {
        return productVerifyTime;
    }

    public void setProductVerifyTime(String productVerifyTime) {
        this.productVerifyTime = productVerifyTime;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getAgentReceivedTime() {
        return agentReceivedTime;
    }

    public void setAgentReceivedTime(String agentReceivedTime) {
        this.agentReceivedTime = agentReceivedTime;
    }

    public Integer getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(Integer deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Integer getDeliveryCodeTried() {
        return deliveryCodeTried;
    }

    public void setDeliveryCodeTried(Integer deliveryCodeTried) {
        this.deliveryCodeTried = deliveryCodeTried;
    }

    public int getBidStatusId() {
        return bidStatusId;
    }

    public void setBidStatusId(int bidStatusId) {
        this.bidStatusId = bidStatusId;
    }

    public BidStatus getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
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

    public Integer getCommodityPostId() {
        return commodityPostId;
    }

    public void setCommodityPostId(Integer commodityPostId) {
        this.commodityPostId = commodityPostId;
    }

    public com.example.amir.shetu.model.CommodityPost getCommodityPost() {
        return CommodityPost;
    }

    public void setCommodityPost(com.example.amir.shetu.model.CommodityPost commodityPost) {
        CommodityPost = commodityPost;
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

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public CommodityGrade getGrade() {
        return grade;
    }

    public void setGrade(CommodityGrade grade) {
        this.grade = grade;
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

    public Integer getPreOrderId() {
        return preOrderId;
    }

    public void setPreOrderId(Integer preOrderId) {
        this.preOrderId = preOrderId;
    }

    public PreOrder getPreOrder() {
        return preOrder;
    }

    public void setPreOrder(PreOrder preOrder) {
        this.preOrder = preOrder;
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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Integer getBidType() {
        return bidType;
    }

    public void setBidType(Integer bidType) {
        this.bidType = bidType;
    }

    public List<BidImage> getBidImages() {
        return bidImages;
    }

    public void setBidImages(List<BidImage> bidImages) {
        this.bidImages = bidImages;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
