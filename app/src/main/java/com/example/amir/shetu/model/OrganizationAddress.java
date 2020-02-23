package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

public class OrganizationAddress {

    private Integer id;
    private Integer districtId;
    private District district;
    private String thana;
    private Integer upazilaId;
    private Upazila upazila;
    private String postCode;
    private String streetAddress;
    private Integer longitude;
    private Integer latitude;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public Integer getUpazilaId() {
        return upazilaId;
    }

    public void setUpazilaId(Integer upazilaId) {
        this.upazilaId = upazilaId;
    }

    public Upazila getUpazila() {
        return upazila;
    }

    public void setUpazila(Upazila upazila) {
        this.upazila = upazila;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }
}
