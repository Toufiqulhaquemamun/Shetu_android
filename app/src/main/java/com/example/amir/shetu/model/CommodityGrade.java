package com.example.amir.shetu.model;

import java.util.List;

public class CommodityGrade
{
    public int id;

    public int commodityId;

    public Commodity commodity;

    public String gradeName;

 
    public String bnGradeName;

   
    public String criteria;
  
    public String bnCriteria;
     
    public double perUnitProductionCost;

    
    public String productionProcess;
      
    public String bnProductionProcess;

    public List<SMEAssaignedCommodity> SMEAssaignedCommodities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getBnGradeName() {
        return bnGradeName;
    }

    public void setBnGradeName(String bnGradeName) {
        this.bnGradeName = bnGradeName;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getBnCriteria() {
        return bnCriteria;
    }

    public void setBnCriteria(String bnCriteria) {
        this.bnCriteria = bnCriteria;
    }

    public double getPerUnitProductionCost() {
        return perUnitProductionCost;
    }

    public void setPerUnitProductionCost(double perUnitProductionCost) {
        this.perUnitProductionCost = perUnitProductionCost;
    }

    public String getProductionProcess() {
        return productionProcess;
    }

    public void setProductionProcess(String productionProcess) {
        this.productionProcess = productionProcess;
    }

    public String getBnProductionProcess() {
        return bnProductionProcess;
    }

    public void setBnProductionProcess(String bnProductionProcess) {
        this.bnProductionProcess = bnProductionProcess;
    }

    public List<SMEAssaignedCommodity> getSMEAssaignedCommodities() {
        return SMEAssaignedCommodities;
    }

    public void setSMEAssaignedCommodities(List<SMEAssaignedCommodity> SMEAssaignedCommodities) {
        this.SMEAssaignedCommodities = SMEAssaignedCommodities;
    }
}
