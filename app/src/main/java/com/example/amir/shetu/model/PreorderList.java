package com.example.amir.shetu.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreorderList {
    @SerializedName("pageconfig")
    private Pageconfig pageconfig;
    @SerializedName("data")
    private List<PreOrder> data;
    @SerializedName("count")
    private Integer count;
    @SerializedName("hasError")
    private Boolean hasError;
    @SerializedName("message")
    private Object message;
    @SerializedName("files")
    private Object files;

    public Pageconfig getPageconfig() {
        return pageconfig;
    }

    public void setPageconfig(Pageconfig pageconfig) {
        this.pageconfig = pageconfig;
    }

    public List<PreOrder> getData() {
        return data;
    }

    public void setData(List<PreOrder> data) {
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getFiles() {
        return files;
    }

    public void setFiles(Object files) {
        this.files = files;
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
