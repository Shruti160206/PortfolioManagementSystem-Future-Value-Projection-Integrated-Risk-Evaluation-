package com.PortfolioManagement.financialEntities;

public class Gold extends Asset {

    private double growthRate;

    public Gold(String name, double initialValue, double growthRate) {
        super(name, initialValue);
        this.growthRate = growthRate;
    }

    @Override
    public double calculateFutureValue(int years) {
        double value = initialValue;
        double rate = growthRate / 100.0;

        for (int i = 0; i < years; i++) {
            value = value * (1 + rate);
        }

        return value;
    }

    @Override
    public double calculateRisk() {
        return 0.3;
    }

    @Override
    public String toString() {
        return "Gold: " + name;
    }
}

