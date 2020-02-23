package com.example.amir.shetu.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProfileSME  {

    private PersonalInformation personalInformation;
    @SerializedName("organization")
    private Organization organization;
    @SerializedName("agent")
    private Agent agent;
    @SerializedName("businessCategories")
    private List<Category> businessCategories = null;

    @SerializedName("personalInformation")
    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    @SerializedName("personalInformation")
    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    @SerializedName("organization")
    public Organization getOrganization() {
        return organization;
    }

    @SerializedName("organization")
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @SerializedName("agent")
    public Agent getAgent() {
        return agent;
    }

    @SerializedName("agent")
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @SerializedName("businessCategories")
    public List<Category> getBusinessCategories() {
        return businessCategories;
    }

    @SerializedName("businessCategories")
    public void setBusinessCategories(List<Category> businessCategories) {
        this.businessCategories = businessCategories;
    }




}
