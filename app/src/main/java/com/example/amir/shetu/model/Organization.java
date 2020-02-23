package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Organization {



    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;


    @SerializedName("estDate")
    private String estDate;

    @SerializedName("ownership")
    private String ownership;


    @SerializedName("tinNo")
    private String tinNo;

    @SerializedName("tinFile")
    private String tinFile;

    @SerializedName("vatRegistrationNO")
    private String vatRegistrationNO;

    @SerializedName("vatRegistrationFile")
    private String vatRegistrationFile;

    @SerializedName("mou")
    private Boolean mou;

    @SerializedName("mouFile")
    private String mouFile;

    @SerializedName("incorporateCertificate")
    private Boolean incorporateCertificate;

    @SerializedName("incorporateCertificateFile")
    private String incorporateCertificateFile;

    @SerializedName("other")
    private String other;

    @SerializedName("otherFile")
    private String otherFile;

    @SerializedName("revenue")
    private Integer revenue;

    @SerializedName("expense")
    private Integer expense;

    @SerializedName("netProfit")
    private Integer netProfit;

    @SerializedName("totalAsset")
    private Integer totalAsset;

    @SerializedName("permanentAsset")
    private Integer permanentAsset;

    @SerializedName("employeeNo")
    private Integer employeeNo;

    @SerializedName("yearlyTurnover")
    private Integer yearlyTurnover;

    @SerializedName("registeredTo")
    private String registeredTo;

    @SerializedName("OrganizationCategories")
    public OrganizationCategory OrganizationCategories;

    @SerializedName("addressId")
    private Integer addressId;

    @SerializedName("address")
    private Address address;

    @SerializedName("tradeLicenseNo")
    private String tradeLicenseNo;

    @SerializedName("tradeLicenseFile")
    private String tradeLicenseFile;

    @SerializedName("tradeLicenses")
    private List<TradeLicense> tradeLicenses;



    public String getEstDate() {
        return estDate;
    }

    public void setEstDate(String estDate) {
        this.estDate = estDate;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getTinFile() {
        return tinFile;
    }

    public void setTinFile(String tinFile) {
        this.tinFile = tinFile;
    }

    public String getVatRegistrationNO() {
        return vatRegistrationNO;
    }

    public void setVatRegistrationNO(String vatRegistrationNO) {
        this.vatRegistrationNO = vatRegistrationNO;
    }

    public String getVatRegistrationFile() {
        return vatRegistrationFile;
    }

    public void setVatRegistrationFile(String vatRegistrationFile) {
        this.vatRegistrationFile = vatRegistrationFile;
    }

    public Boolean getMou() {
        return mou;
    }

    public void setMou(Boolean mou) {
        this.mou = mou;
    }

    public String getMouFile() {
        return mouFile;
    }

    public void setMouFile(String mouFile) {
        this.mouFile = mouFile;
    }

    public Boolean getIncorporateCertificate() {
        return incorporateCertificate;
    }

    public void setIncorporateCertificate(Boolean incorporateCertificate) {
        this.incorporateCertificate = incorporateCertificate;
    }

    public String getIncorporateCertificateFile() {
        return incorporateCertificateFile;
    }

    public void setIncorporateCertificateFile(String incorporateCertificateFile) {
        this.incorporateCertificateFile = incorporateCertificateFile;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOtherFile() {
        return otherFile;
    }

    public void setOtherFile(String otherFile) {
        this.otherFile = otherFile;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public Integer getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Integer netProfit) {
        this.netProfit = netProfit;
    }

    public Integer getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(Integer totalAsset) {
        this.totalAsset = totalAsset;
    }

    public Integer getPermanentAsset() {
        return permanentAsset;
    }

    public void setPermanentAsset(Integer permanentAsset) {
        this.permanentAsset = permanentAsset;
    }

    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Integer employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getYearlyTurnover() {
        return yearlyTurnover;
    }

    public void setYearlyTurnover(Integer yearlyTurnover) {
        this.yearlyTurnover = yearlyTurnover;
    }

    public String getRegisteredTo() {
        return registeredTo;
    }

    public void setRegisteredTo(String registeredTo) {
        this.registeredTo = registeredTo;
    }

    public OrganizationCategory getOrganizationCategories() {
        return OrganizationCategories;
    }

    public void setOrganizationCategories(OrganizationCategory organizationCategories) {
        OrganizationCategories = organizationCategories;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTradeLicenseNo() {
        return tradeLicenseNo;
    }

    public void setTradeLicenseNo(String tradeLicenseNo) {
        this.tradeLicenseNo = tradeLicenseNo;
    }

    public String getTradeLicenseFile() {
        return tradeLicenseFile;
    }

    public void setTradeLicenseFile(String tradeLicenseFile) {
        this.tradeLicenseFile = tradeLicenseFile;
    }

    public List<TradeLicense> getTradeLicenses() {
        return tradeLicenses;
    }

    public void setTradeLicenses(List<TradeLicense> tradeLicenses) {
        this.tradeLicenses = tradeLicenses;
    }

}
