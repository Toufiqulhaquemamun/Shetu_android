package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommodityPostList {

        @SerializedName("pageconfig")
        private PageConfig pageConfig;

        @SerializedName("data")
        private List<Data> dataList;

        @SerializedName("count")
        private Integer count;

        @SerializedName("hasError")
        private Boolean hasError;

        @SerializedName("message")
        private String message;

        @SerializedName("files")
        private String files;

        @SerializedName("pageConfig")
        public PageConfig getPageConfig() {
            return pageConfig;
        }

        @SerializedName("pageConfig")
        public void setPageConfig(PageConfig pageConfig) {
            this.pageConfig = pageConfig;
        }

        @SerializedName("data")
        public List<Data> getData() {
            return dataList;
        }

        @SerializedName("data")
        public void setData(List<Data> data) {
            this.dataList = data;
        }

        @SerializedName("count")
        public Integer getCount() {
            return count;
        }

        @SerializedName("count")
        public void setCount(Integer count) {
            this.count = count;
        }


        @SerializedName("message")
        public Object getMessage() {
            return message;
        }

        @SerializedName("message")
        public void setMessage(String message) {
            this.message = message;
        }

        @SerializedName("files")
        public Object getFiles() {
            return files;
        }

        @SerializedName("files")
        public void setFiles(String files) {
            this.files = files;
        }



    public class Data {

        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        @SerializedName("image")
        private String image;

        @SerializedName("grade")
        private String grade;

        @SerializedName("quantity")
        private double quantity;

        @SerializedName("unit")
        private String unit;

        @SerializedName("price")
        private double price;

        @SerializedName("features")
        private String features;

        @SerializedName("uploadTime")
        private String uploadTime;

        @SerializedName("sellingStatus")
        private SellingStatus sellingStatus;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getGrade() {
            return grade;
        }

        public double getQuantity() {
            return quantity;
        }

        public String getUnit() {
            return unit;
        }

        public double getPrice() {
            return price;
        }

        public String getFeatures() {
            return features;
        }

        public String getUploadTime() {
            return uploadTime;
        }

        public SellingStatus getSellingStatus() {
            return sellingStatus;
        }
    }


    public class PageConfig {

        @SerializedName("totalItems")
        private Integer totalItems;

        @SerializedName("currentPage")
        private Integer currentPage;

        @SerializedName("pageSize")
        private Integer pageSize;

        @SerializedName("totalPages")
        private Integer totalPages;

        @SerializedName("startPage")
        private Integer startPage;

        @SerializedName("endPage")
        private Integer endPage;

        @SerializedName("totalItems")
        public Integer getTotalItems() {
            return totalItems;
        }

        @SerializedName("totalItems")
        public void setTotalItems(Integer totalItems) {
            this.totalItems = totalItems;
        }

        @SerializedName("currentPage")
        public Integer getCurrentPage() {
            return currentPage;
        }

        @SerializedName("currentPage")
        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        @SerializedName("pageSize")
        public Integer getPageSize() {
            return pageSize;
        }

        @SerializedName("pageSize")
        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        @SerializedName("totalPages")
        public Integer getTotalPages() {
            return totalPages;
        }

        @SerializedName("totalPages")
        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        @SerializedName("startPage")
        public Integer getStartPage() {
            return startPage;
        }

        @SerializedName("startPage")
        public void setStartPage(Integer startPage) {
            this.startPage = startPage;
        }

        @SerializedName("endPage")
        public Integer getEndPage() {
            return endPage;
        }

        @SerializedName("endPage")
        public void setEndPage(Integer endPage) {
            this.endPage = endPage;
        }

        }

    }
