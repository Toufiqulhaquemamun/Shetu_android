package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TradeList {

    private Object pageconfig;
    @SerializedName("data")
    private List<TradelistSme> data = null;
    private Integer count;
    private Boolean hasError;
    private Object message;
    private Object files;


    @SerializedName("data")
    public List<TradelistSme> getData() {
        return data;
    }

    public void setData(List<TradelistSme> data) {
        this.data = data;
    }

    public class TradelistSme implements Serializable
    {
        private Integer bidId;
        private String commodityName;
        private Integer quantity;
        private String unit;
        private Integer unitPrice;
        private String buyerName;
        private String sellerName;
        private String buyerPhone;
        private String sellerPhone;
        private String bidDateTime;
        private String expiryDate;
        private Integer statusId;
        private String status;
        private String statusDisplayName;

        public Integer getBidId() {
            return bidId;
        }

        public void setBidId(Integer bidId) {
            this.bidId = bidId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Integer getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(Integer unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getBuyerPhone() {
            return buyerPhone;
        }

        public void setBuyerPhone(String buyerPhone) {
            this.buyerPhone = buyerPhone;
        }

        public String getSellerPhone() {
            return sellerPhone;
        }

        public void setSellerPhone(String sellerPhone) {
            this.sellerPhone = sellerPhone;
        }

        public String getBidDateTime() {
            return bidDateTime;
        }

        public void setBidDateTime(String bidDateTime) {
            this.bidDateTime = bidDateTime;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDisplayName() {
            return statusDisplayName;
        }

        public void setStatusDisplayName(String statusDisplayName) {
            this.statusDisplayName = statusDisplayName;
        }
    }

}
