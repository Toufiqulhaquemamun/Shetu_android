package com.example.amir.shetu.model;

public class ShippingCostByCommodityId {

        private Integer id;
        private Integer commodityId;
        private Object commodity;
        private Integer costPerUnit;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(Integer commodityId) {
            this.commodityId = commodityId;
        }

        public Object getCommodity() {
            return commodity;
        }

        public void setCommodity(Object commodity) {
            this.commodity = commodity;
        }

        public Integer getCostPerUnit() {
            return costPerUnit;
        }

        public void setCostPerUnit(Integer costPerUnit) {
            this.costPerUnit = costPerUnit;
        }


}
