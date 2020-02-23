package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class District  {

    private Integer id;
    private String name;
    private String bnName;
    private Integer divisionId;
    private Object division;
    private Object upazilas;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Object getDivision() {
        return division;
    }

    public void setDivision(Object division) {
        this.division = division;
    }

    public Object getUpazilas() {
        return upazilas;
    }

    public void setUpazilas(Object upazilas) {
        this.upazilas = upazilas;
    }
}
