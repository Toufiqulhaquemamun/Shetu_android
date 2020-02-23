package com.example.amir.shetu.model;

import java.util.List;

public class AgentBuyerList {

    private Pageconfig pageconfig;
    private List<Datum> data = null;
    private Integer count;
    private Boolean hasError;
    private String message;
    private Object files;

    public Pageconfig getPageconfig() {
        return pageconfig;
    }

    public void setPageconfig(Pageconfig pageconfig) {
        this.pageconfig = pageconfig;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getFiles() {
        return files;
    }

    public void setFiles(Object files) {
        this.files = files;
    }
    public class Datum {

        private String sellerName;
        private String bnSellerName;
        private String buyerName;
        private String bnBuyerName;
        private String productName;
        private String bnProductName;
        private Float quantity;
        private Object unit;
        private Object bnUnit;
        private Float price;
        private Float total;
        private String soldDate;
        private String shippingDate;
        private Float agentAmount;

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getBnSellerName() {
            return bnSellerName;
        }

        public void setBnSellerName(String bnSellerName) {
            this.bnSellerName = bnSellerName;
        }

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBnBuyerName() {
            return bnBuyerName;
        }

        public void setBnBuyerName(String bnBuyerName) {
            this.bnBuyerName = bnBuyerName;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getBnProductName() {
            return bnProductName;
        }

        public void setBnProductName(String bnProductName) {
            this.bnProductName = bnProductName;
        }

        public Float getQuantity() {
            return quantity;
        }

        public void setQuantity(Float quantity) {
            this.quantity = quantity;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public Object getBnUnit() {
            return bnUnit;
        }

        public void setBnUnit(Object bnUnit) {
            this.bnUnit = bnUnit;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }

        public String getSoldDate() {
            return soldDate;
        }

        public void setSoldDate(String soldDate) {
            this.soldDate = soldDate;
        }

        public String getShippingDate() {
            return shippingDate;
        }

        public void setShippingDate(String shippingDate) {
            this.shippingDate = shippingDate;
        }

        public Float getAgentAmount() {
            return agentAmount;
        }

        public void setAgentAmount(Float agentAmount) {
            this.agentAmount = agentAmount;
        }

    }

    public class Pageconfig {

        private Integer totalItems;
        private Integer currentPage;
        private Integer pageSize;
        private Integer totalPages;
        private Integer startPage;
        private Integer endPage;
        private Object link;

        public Integer getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(Integer totalItems) {
            this.totalItems = totalItems;
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public Integer getStartPage() {
            return startPage;
        }

        public void setStartPage(Integer startPage) {
            this.startPage = startPage;
        }

        public Integer getEndPage() {
            return endPage;
        }

        public void setEndPage(Integer endPage) {
            this.endPage = endPage;
        }

        public Object getLink() {
            return link;
        }

        public void setLink(Object link) {
            this.link = link;
        }

    }
}


