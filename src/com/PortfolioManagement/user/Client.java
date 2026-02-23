package com.PortfolioManagement.user;

public class Client {

    private String clientId;
    private String name;
    private double riskTolerance;  // (0.1 to 0.5) - low (Conservative), medium(Balanced), High(Aggressive)

    public Client(String clientId, String name, double riskTolerance) {
        this.clientId = clientId;
        this.name = name;
        this.riskTolerance = riskTolerance;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public double getRiskTolerance() {
        return riskTolerance;
    }
}