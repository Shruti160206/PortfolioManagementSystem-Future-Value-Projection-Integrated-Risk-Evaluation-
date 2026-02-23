package com.PortfolioManagement.service;

import com.PortfolioManagement.dsa_models.*;
import com.PortfolioManagement.financialEntities.Asset;
import com.PortfolioManagement.financialEntities.Order;
import com.PortfolioManagement.financialEntities.Stock;
import com.PortfolioManagement.user.Client;
import com.PortfolioManagement.exceptions.AssetNotFoundException;
import com.PortfolioManagement.exceptions.EmptyPortfolioException;
import com.PortfolioManagement.exceptions.InvalidAssetException;
import com.PortfolioManagement.dsa_models.ClientHashMap;

public class PortfolioManager {

    private LinkedList portfolio;
    private HashMap AssetMap;
    private Stack transactionHistory;
    private CircularQueue orderQueue;
    private BST riskTree;
    private ClientHashMap clientRegister;

    public PortfolioManager() {
        portfolio = new LinkedList();
        AssetMap = new HashMap();       // Generic DSA HashMap
        transactionHistory = new Stack();
        orderQueue = new CircularQueue(20);
        riskTree = new BST();
        clientRegister = new ClientHashMap();
    }


    // ADD CLIENT
    public void addClient(Client client) {
        try {
            if (client == null)
                throw new InvalidAssetException("Client cannot be null.");

            if (clientRegister.containsKey_Client(client.getClientId())) {
                System.out.println("Client already exists.");
                return;
            }

            clientRegister.putClient(client.getClientId(), client);
            System.out.println("Client added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    // GET CLIENT
    public Client getClient(String clientId) {
        return clientRegister.getClient(clientId);
    }


    // PLACE CLIENT ORDER
    public void clientOrderPlaced(String clientId, Asset asset) {
        try {
            Client client = clientRegister.getClient(clientId);
            if (client == null)
                throw new RuntimeException("Client not found. Please register first.");
            if (asset == null)
                throw new RuntimeException("Invalid asset selected.");

            // Adds the order to the CirclarQueue
            Order order = new Order("BUY", asset);
            orderQueue.enqueue(order);

            // Adds the order to the transaction history (Stack)
            transactionHistory.push("Order placed by " + client.getName() + " for asset " + asset.getName());

            // aDDing the asse to client's LinkedList
            LinkedList clientAssets = (LinkedList) AssetMap.get(clientId);
            if (clientAssets == null) {
                clientAssets = new LinkedList();
                AssetMap.put(clientId, clientAssets);
            }
            clientAssets.add(asset);

            // Add asset to portfolio
            portfolio.add(asset);

            System.out.println("Order successfully placed for client: " + client.getName());

        } catch (Exception e) {
            System.out.println("Error placing order: " + e.getMessage());
        }
    }

    // PROCESS ORDERS
    public void processOrders() {
        try {
            if (orderQueue.isEmpty())
                throw new EmptyPortfolioException("No pending orders.");

            System.out.println("\n--- Processing Orders ---");
            while (!orderQueue.isEmpty()) {
                Order order = (Order) orderQueue.dequeue();
                System.out.println("Processing Order: " + order.getType() + " : " + order.getAsset().getName());
            }

        } catch (EmptyPortfolioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // SORT ASSETS BY RISK
    public void sortAssetsByRisk() {
        try {
            if (portfolio.isEmpty())
                throw new EmptyPortfolioException("Portfolio is empty.");

            riskTree.clear();
            LinkedList.Node temp = portfolio.getHead();
            while (temp != null) {
                Asset asset = (Asset) temp.data;
                riskTree.insert(asset);
                temp = temp.next;
            }

            System.out.println("\n--- Assets Sorted By Risk ---");
            riskTree.inOrder();

        } catch (EmptyPortfolioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // GENERATE CLIENT INVESTMENT REPORT
    public void generateClientInvestmentReport(String clientId, int years) {
        try {
            Client client = clientRegister.getClient(clientId);
            if (client == null) {
                System.out.println("Client not found.");
                return;
            }

            LinkedList clientAssets = (LinkedList) AssetMap.get(clientId);
            if (clientAssets == null || clientAssets.isEmpty()) {
                System.out.println("No investment found for this client.");
                return;
            }

            System.out.println("\n========= INVESTMENT REPORT =========");
            System.out.println("Client: " + client.getName());
            System.out.println("Client ID: " + client.getClientId());

            LinkedList.Node temp = clientAssets.getHead();
            while (temp != null) {
                Asset asset = (Asset) temp.data;
                double futureValue = asset.calculateFutureValue(years);
                double risk = asset.calculateRisk();
                System.out.println("--------------------------------");
                System.out.println("Asset Type: " + asset.getName());
                System.out.println("Present Value: " + asset.getInitialValue());
                System.out.println("Projected Future Value (" + years + " years): " + futureValue);
                System.out.println("Risk Factor: " + risk);
                System.out.println("Decision: " + (risk <= client.getRiskTolerance() ? "SAFE Investment" : "RISKY Investment"));

                if (asset instanceof Stock) {
                    Stock stock = (Stock) asset;
                    System.out.println("Deviation Type: " + stock.getDeviationType());
                }
                temp = temp.next;
            }

            System.out.println("======================================\n");

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }


    // UNDO LAST TRANSACTION
    public void undoLastTransaction() {
        try {
            if (transactionHistory.isEmpty())
                throw new EmptyPortfolioException("No transaction to undo.");

            System.out.println("Undo: " + transactionHistory.pop());

        } catch (EmptyPortfolioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // DISPLAY ENTIRE PORTFOLIO
    public void displayPortfolio(int years) {
        try {
            if (portfolio.isEmpty())
                throw new EmptyPortfolioException("Portfolio is empty.");

            LinkedList.Node temp = portfolio.getHead();
            while (temp != null) {
                Asset asset = (Asset) temp.data;
                System.out.println("Asset: " + asset.getName());
                System.out.println("Future Value: " + asset.calculateFutureValue(years));
                System.out.println("Risk: " + asset.calculateRisk());
                System.out.println("--------------------------------");
                temp = temp.next;
            }

        } catch (EmptyPortfolioException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
