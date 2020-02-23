package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("districtId")
    private Integer districtId;
    @SerializedName("district")
    private District district;
    @SerializedName("upazilaId")
    private Integer upazilaId;
    @SerializedName("upazila")
    private Upazila upazila;
    @SerializedName("thana")
    private String thana;
    @SerializedName("postCode")
    private String postCode;
    @SerializedName("streetAddress")
    private String streetAddress;
    @SerializedName("longitude")
    private Integer longitude;
    @SerializedName("latitude")
    private Integer latitude;

    public Address(Integer districtId, Integer upazilaId, String postCode, String localAdd) {
    this.districtId=districtId;
    this.upazilaId=upazilaId;
    this.postCode=postCode;
    this.streetAddress=localAdd;
    }


    @SerializedName("id")
    public Integer getId() {
        return id;
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

    @SerializedName("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("districtId")
    public Integer getDistrictId() {
        return districtId;
    }

    @SerializedName("districtId")
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @SerializedName("district")
    public District getDistrict() {
        return district;
    }

    @SerializedName("district")
    public void setDistrict(District district) {
        this.district = district;
    }

    @SerializedName("thana")
    public String getThana() {
        return thana;
    }

    @SerializedName("thana")
    public void setThana(String thana) {
        this.thana = thana;
    }

    @SerializedName("postCode")
    public String getPostCode() {
        return postCode;
    }

    @SerializedName("postCode")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @SerializedName("streetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    @SerializedName("streetAddress")
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @SerializedName("longitude")
    public Integer getLongitude() {
        return longitude;
    }

    @SerializedName("longitude")
    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    @SerializedName("latitude")
    public Integer getLatitude() {
        return latitude;
    }

    @SerializedName("latitude")
    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }
    
}