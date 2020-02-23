package com.example.amir.shetu.model;

public class Product
    {
        double Quantity;
        double Price;
        String Features;
        int UnitId;
        int ProductId;
        int SellerId;




        public Product(double quantity, double price, String features, int unitId, int productId, int sellerId) {
            Quantity = quantity;
            Price = price;
            Features = features;
            UnitId = unitId;
            ProductId = productId;
            SellerId = sellerId;
        }

        public double getQuantity() {
            return Quantity;
        }

        public void setQuantity(double quantity) {
            Quantity = quantity;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double price) {
            Price = price;
        }

        public String getFeatures() {
            return Features;
        }

        public void setFeatures(String features) {
            Features = features;
        }

        public int getUnitId() {
            return UnitId;
        }

        public void setUnitId(int unitId) {
            UnitId = unitId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int productId) {
            ProductId = productId;
        }

        public int getSellerId() {
            return SellerId;
        }

        public void setSellerId(int sellerId) {
            SellerId = sellerId;
        }
    }

