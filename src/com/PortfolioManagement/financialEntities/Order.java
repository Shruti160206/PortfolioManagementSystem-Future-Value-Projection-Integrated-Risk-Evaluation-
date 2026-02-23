package com.PortfolioManagement.financialEntities;

public class Order {

    private String type;
    private Asset asset;

    public Order(String type, Asset asset) {
        this.type = type;
        this.asset = asset;
    }

    public String getType() {
        return type;
    }

    public Asset getAsset() {
        return asset;
    }
}
