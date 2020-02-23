package com.example.amir.shetu.model;

public class BidStatus {
    private Integer id;
    private String status;
    private String displayName;
    private String displayBnName;
    private Object bids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayBnName() {
        return displayBnName;
    }

    public void setDisplayBnName(String displayBnName) {
        this.displayBnName = displayBnName;
    }

    public Object getBids() {
        return bids;
    }

    public void setBids(Object bids) {
        this.bids = bids;
    }
}
