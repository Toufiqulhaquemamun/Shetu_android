package com.example.amir.shetu.model;

import java.util.List;

public class Agent {

    private String firstName;
    private String lastName;
    private String bnFirstName;
    private String bnLastName;
    private String gender;
    private String email;
    private String phone;
    private String image;
    private String organizationName;
    private List<Category> typeofBusiness = null;
    private PresentAddress presentAddress;
    private OrganizationAddress organizationAddress;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBnFirstName() {
        return bnFirstName;
    }

    public void setBnFirstName(String bnFirstName) {
        this.bnFirstName = bnFirstName;
    }

    public String getBnLastName() {
        return bnLastName;
    }

    public void setBnLastName(String bnLastName) {
        this.bnLastName = bnLastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<Category> getTypeofBusiness() {
        return typeofBusiness;
    }

    public void setTypeofBusiness(List<Category> typeofBusiness) {
        this.typeofBusiness = typeofBusiness;
    }

    public PresentAddress getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(PresentAddress presentAddress) {
        this.presentAddress = presentAddress;
    }

    public OrganizationAddress getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(OrganizationAddress organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

}
