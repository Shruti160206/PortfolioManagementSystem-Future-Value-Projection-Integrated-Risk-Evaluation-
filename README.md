# PortfolioManagementSystem-Future-Value-Projection-Integrated-Risk-Evaluation-
Quantitative Future Value Projection & Integrated Risk Analytics
Project Overview

The Portfolio Management System is a Java-based investment management application designed to simulate real-world portfolio analysis for clients investing in:
Stocks
Bonds
Gold
Real Estate

The system enables financial accountants to register clients, process investment orders, calculate projected future values, evaluate risk factors, and generate structured investment reports.

This project emphasizes core Data Structures & Algorithms (DSA) and Object-Oriented Programming (OOP) concepts without relying on Java built-in collections.

Key Features

* Client registration & portfolio creation
* BUY/SELL order processing using Circular Queue
* Future Value Projection (CAGR-based calculations)
* Risk Evaluation & Volatility Analysis
* Risk-based asset sorting using Binary Search Tree
* Portfolio storage using custom LinkedList
* Fast client-asset lookup using custom HashMap
* Transaction history tracking using Stack
* Custom exception handling for validation
* Interactive user input (Console-based system)

Core Concepts Applied

*  Object-Oriented Programming
  Abstraction : Asset abstract class
  Inheritance : Stock, Bond, Gold, RealEstate extend Asset
  Polymorphism : Overridden calculateFutureValue() & calculateRisk()
  Encapsulation : Private variables with controlled access

* Data Structures & Algorithms (Custom Implementation)
  LinkedList : Portfolio storage
  ClientHashMap : Client-to-asset mapping (Specific mapping)
  HashMap : Client-to-Object mapping (generic mapping, Here Object value can be Assets, Portfolios or Reports)
  Stack : Transaction history tracking
  Circular Queue : Order processing system
  Binary Search Tree (BST) : Risk-based asset sorting
