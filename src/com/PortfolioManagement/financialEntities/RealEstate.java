package com.PortfolioManagement.financialEntities;

public class RealEstate extends Asset {

    private double landGrowthRate;
    private double buildingDepRate;

    public RealEstate(String name, double initialValue, double landGrowthRate, double buildingDepRate) {
        super(name, initialValue);
        this.landGrowthRate = landGrowthRate;
        this.buildingDepRate = buildingDepRate;
    }

    @Override
    public double calculateFutureValue(int years) {
        double value = initialValue;
        double netRate = (landGrowthRate - buildingDepRate) / 100.0;

        for (int i = 0; i < years; i++) {
            value = value * (1 + netRate);
        }

        return value;
    }

    @Override
    public double calculateRisk() {
        return 0.4;
    }

    @Override
    public String toString() {
        return "RealEstate: " + name;
    }
}