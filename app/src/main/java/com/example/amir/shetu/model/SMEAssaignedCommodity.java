package com.example.amir.shetu.model;

import java.util.List;

public class SMEAssaignedCommodity
{
    public int id;

    public int commodityId;

    public Commodity commodity;

    public int gradeId;

    public CommodityGrade commodityGrade;

    public String description;

    public int userId;

    public User user;

    public List<CommodityPost> CommodityPosts;
}
