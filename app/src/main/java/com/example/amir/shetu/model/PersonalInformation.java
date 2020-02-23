package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

public class PersonalInformation {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;



    @SerializedName("bnName")
    private String bnName;



    @SerializedName("fatherName")
    private String fatherName;

    @SerializedName("motherName")
    private String motherName;

    @SerializedName("dathOfBirth")
    private String dateOfBirth;

    private String registrationDate;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("nid")
    private String nid;

    @SerializedName("nidAttachment")
    private String nidAttachment;

    @SerializedName("passport")
    private String passport;

    @SerializedName("passportAttachment")
    private String passportAttachment;

    @SerializedName("image")
    private String image;

    @SerializedName("characterCertificate")
    private String characterCertificate;

    @SerializedName("gender")
    private String gender;

    @SerializedName("education")
    private String education;

    @SerializedName("experience")
    private String experience;

    @SerializedName("signature")
    private String signature;

    @SerializedName("bankAccount")
    private String bankAccount;

    @SerializedName("bankName")
    private String bankName;

    @SerializedName("branchName")
    private String branchName;

    @SerializedName("presentAddressId")
    private Integer presentAddressId;

    @SerializedName("presentAddress")
    private PresentAddress presentAddress;

    @SerializedName("permanentAddressId")
    private Integer permanentAddressId;

    @SerializedName("permanentAddress")
    private Address permanentAddress;

    @SerializedName("routingNo")
    private String routingNo;

    @SerializedName("accountName")
    private String accountName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBnName() {
        return bnName;
    }

    public void setBnName(String bnName) {
        this.bnName = bnName;
    }

    public String getRoutingNo() {
        return routingNo;
    }

    public void setRoutingNo(String routingNo) {
        this.routingNo = routingNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNidAttachment() {
        return nidAttachment;
    }

    public void setNidAttachment(String nidAttachment) {
        this.nidAttachment = nidAttachment;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassportAttachment() {
        return passportAttachment;
    }

    public void setPassportAttachment(String passportAttachment) {
        this.passportAttachment = passportAttachment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCharacterCertificate() {
        return characterCertificate;
    }

    public void setCharacterCertificate(String characterCertificate) {
        this.characterCertificate = characterCertificate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getPresentAddressId() {
        return presentAddressId;
    }

    public void setPresentAddressId(Integer presentAddressId) {
        this.presentAddressId = presentAddressId;
    }

    public PresentAddress getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(PresentAddress presentAddress) {
        this.presentAddress = presentAddress;
    }

    public Integer getPermanentAddressId() {
        return permanentAddressId;
    }

    public void setPermanentAddressId(Integer permanentAddressId) {
        this.permanentAddressId = permanentAddressId;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
}
