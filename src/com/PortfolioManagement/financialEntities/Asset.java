package com.PortfolioManagement.financialEntities;

public abstract class Asset {

    protected String name;
    protected double initialValue;

    public Asset(String name, double initialValue) {

        if (initialValue <= 0)
            throw new IllegalArgumentException("Initial value must be positive");

        this.name = name;
        this.initialValue = initialValue;
    }

    public String getName() {
        return name;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public abstract double calculateFutureValue(int years);
    public abstract double calculateRisk();
}
