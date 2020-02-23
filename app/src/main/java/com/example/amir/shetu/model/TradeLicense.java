package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

public class TradeLicense {

    @SerializedName("Id")
    public int Id;

    @SerializedName("tradeLisenseNo")
    public String tradeLisenseNo;

    @SerializedName("issueDate")
    public String issueDate;

    @SerializedName("expiryDate")
    public String expiryDate;

    @SerializedName("fiscalYear")
    public String fiscalYear;

    @SerializedName("file")
    public String file;

    @SerializedName("organizationId")
    public int organizationId;

    @SerializedName("organization")
    public Organization Organization;

    public int getId() {
        return Id;
    }

    public String getTradeLisenseNo() {
        return tradeLisenseNo;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public String getFile() {
        return file;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public Organization getOrganization() {
        return Organization;
    }
}
