package com.PortfolioManagement.financialEntities;

public class Bond extends Asset {

    private double interestRate;

    public Bond(String name, double initialValue, double interestRate) {
        super(name, initialValue);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateFutureValue(int years) {
        double value = initialValue;
        double rate = interestRate / 100.0; // convert percentage to decimal

        for (int i = 0; i < years; i++) {
            value = value * (1 + rate);
        }

        return value;
    }

    @Override
    public double calculateRisk() {
        return 0.2;
    }

    @Override
    public String toString() {
        return "Bond: " + name;
    }
}