package com.example.amir.shetu.model;

import java.util.List;

public class CommodityPost
{
    public int Id;
        
       
    public double Quantity;

    
    public double Price;

    
    public String UploadTime;

    public String Features;

    public int StatusId;

    public SellingStatus Status;

    public int UnitId;

    public MeasurmentUnit Unit;

    
    public int SMEAssaignedCommodityId;
    public SMEAssaignedCommodity SMEAssaignedCommodity;

       
    public int SellerId;
    public User Seller;

    public List<PostImage> Images;

    public List<Bid> bid2s;
}
