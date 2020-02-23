package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Unit  {
    public int id ;

    @SerializedName("bnName")
    public String name ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
