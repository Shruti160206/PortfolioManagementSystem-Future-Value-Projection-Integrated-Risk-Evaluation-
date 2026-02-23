package com.PortfolioManagement.financialEntities;

public class Stock extends Asset {

    private double currentPrice;

    public Stock(String name, double initialValue, double currentPrice) {
        super(name, initialValue);
        this.currentPrice = currentPrice;
    }

    @Override
    public double calculateFutureValue(int years) {
        double growthFactor = currentPrice / initialValue;
        double value = initialValue;

        for (int i = 0; i < years; i++) {
            value = value * growthFactor; // compound annually
        }

        return value;
    }

    @Override
    public double calculateRisk() {
        if (initialValue == 0) return 0;
        return Math.abs((currentPrice - initialValue) / initialValue);
    }

    public String getDeviationType() {
        double change = currentPrice - initialValue;
        if (change > 0) return "Positive Deviation (Profit)";
        if (change < 0) return "Negative Deviation (Loss)";
        return "No Change";
    }

    @Override
    public String toString() {
        return "Stock: " + name + " Value: " + currentPrice;
    }
}