package com.example.amir.shetu.model;

import java.io.Serializable;
import java.util.List;

public class ReceiveableList {
    private List<RcvProductList> data = null;
    private Integer count;
    private Boolean hasError;
    private Object message;
    private Object files;

    public List<RcvProductList> getData() {
        return data;
    }

    public void setData(List<RcvProductList> data) {
        this.data = data;
    }


    public class RcvProductList implements Serializable {

        private Integer bidId;
        private String commodityName;
        private Integer quantity;
        private Object unit;
        private Integer unitPrice;
        private String buyerName;
        private String sellerName;
        private String buyerPhone;
        private String sellerPhone;
        private String bidDateTime;
        private String shippingTime;
        private Object expiryDate;
        private Integer statusId;
        private String status;
        private String statusDisplayName;
        private Integer deliveryCodeTired;

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

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
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

        public String getShippingTime() {
            return shippingTime;
        }

        public void setShippingTime(String shippingTime) {
            this.shippingTime = shippingTime;
        }

        public Object getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(Object expiryDate) {
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

        public Integer getDeliveryCodeTired() {
            return deliveryCodeTired;
        }

        public void setDeliveryCodeTired(Integer deliveryCodeTired) {
            this.deliveryCodeTired = deliveryCodeTired;
        }

    }
}
