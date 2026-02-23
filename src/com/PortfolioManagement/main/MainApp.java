package com.PortfolioManagement.main;


import com.PortfolioManagement.financialEntities.*;
import com.PortfolioManagement.service.PortfolioManager;
import com.PortfolioManagement.user.Client;
import java.util.Scanner;

public class MainApp {

    private static Scanner sc = new Scanner(System.in);
    private static PortfolioManager manager = new PortfolioManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====== UAE Portfolio Management System ======");
            System.out.println("1. Register Client");
            System.out.println("2. Add Asset Investment");
            System.out.println("3. Process Orders");
            System.out.println("4. Show Client Portfolio Report");
            System.out.println("5. Show Risk Sorted Assets");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerClient();
                    break;
                case 2:
                    addInvestment();
                    break;
                case 3:
                    manager.processOrders();
                    break;
                case 4:
                    showReport();
                    break;
                case 5:
                    manager.sortAssetsByRisk();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    private static void registerClient() {
        System.out.print("Enter Client ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Client Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Client Risk Tolerance - 0.1 to 0.5 (Conservative, Balanced, Aggressive): ");
        double riskTolerance = sc.nextDouble();
        sc.nextLine();

        Client client = new Client(id, name, riskTolerance);
        manager.addClient(client);

        System.out.println("Client Registered Successfully.");
    }

    private static void addInvestment() {

        System.out.print("Enter Client ID: ");
        String clientId = sc.nextLine();

        Client client = manager.getClient(clientId);
        if (client == null) {
            System.out.println("Client not found! Please register first.");
            return;
        }

        System.out.println("Select Asset Type:");
        System.out.println("1. Stock");
        System.out.println("2. Bond");
        System.out.println("3. Gold");
        System.out.println("4. Real Estate");

        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Asset Name: ");
        String name = sc.nextLine();

        Asset asset = null;

        switch (type) {
            case 1:
                System.out.println("\n--- STOCK INVESTMENT ---");
                System.out.print("Enter stock price per share (AED): ");
                double iv = sc.nextDouble();
                System.out.print("Enter expected future price per share (AED): ");
                double cp = sc.nextDouble();
                sc.nextLine();
                asset = new Stock(name, iv, cp);
                break;

            case 2:
                System.out.println("\n--- BOND INVESTMENT ---");
                System.out.print("Enter initial value (AED): ");
                double init_value = sc.nextDouble();
                System.out.print("Enter Interest Rate (% per year): ");
                double rate = sc.nextDouble();
                sc.nextLine();
                asset = new Bond(name, init_value, rate);
                break;

            case 3:
                System.out.println("\n--- GOLD INVESTMENT ---");
                System.out.print("Enter initial value (AED): ");
                double IV = sc.nextDouble();
                System.out.print("Enter expected annual appreciation rate (%): ");
                double goldRate = sc.nextDouble();
                sc.nextLine();
                asset = new Gold(name, IV, goldRate);
                break;

            case 4:
                System.out.println("\n--- REAL ESTATE INVESTMENT ---");
                System.out.print("Enter initial value (AED) of property (land + building): ");
                double init_v = sc.nextDouble();
                System.out.print("Enter expected annual land growth rate (%): ");
                double landRate = sc.nextDouble();
                System.out.print("Enter expected annual building depreciation/growth rate (%): ");
                double bdr = sc.nextDouble();
                sc.nextLine();
                asset = new RealEstate(name, init_v, landRate, bdr);
                break;

            default:
                System.out.println("Invalid Asset Type");
                return;
        }

        System.out.print("Enter Order Type (BUY/SELL): ");
        String orderType = sc.nextLine().toUpperCase();

        if (!orderType.equals("BUY") && !orderType.equals("SELL")) {
            System.out.println("Invalid order type. Defaulting it to BUY.");
            orderType = "BUY";
        }

        manager.clientOrderPlaced(clientId, asset);
        System.out.println("Investment order (" + orderType + ") placed successfully.");
    }

    private static void showReport() {

        System.out.print("Enter Client ID: ");
        String clientId = sc.nextLine();

        Client client = manager.getClient(clientId);
        if (client == null) {
            System.out.println("Client not found!");
            return;
        }

        System.out.print("Enter Years for Future Projection: ");
        int years = sc.nextInt();
        sc.nextLine();

        manager.generateClientInvestmentReport(clientId, years);
    }

}