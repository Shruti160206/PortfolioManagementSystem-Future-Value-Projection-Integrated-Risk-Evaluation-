// ClientHashMap : Maps String Key to Client object

package com.PortfolioManagement.dsa_models;

import com.PortfolioManagement.user.Client;

public class ClientHashMap {

    private static class Node {
        String key;
        Client value;
        Node next;

        Node(String key, Client value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] buckets;
    private int capacity;
    private int size;


    public ClientHashMap() {
        capacity = 10;
        buckets = new Node[capacity];
        size = 0;
    }


    private int hashClient(String key) {

        int sum = 0;

        for (int i = 0; i < key.length(); i++) {

            char ch = key.charAt(i);
            int ascii = (int) ch;
            sum = sum + (ascii * (i + 1));
        }
        if (sum < 0) {
            sum = sum * -1;
        }

        return sum % capacity;
    }

    public void putClient(String key, Client value) {

        int index = hashClient(key);

        Node head = buckets[index];

        while (head != null) {

            if (head.key.equals(key)) {
                head.value = value;
                return;
            }

            head = head.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;

        size++;
    }

    public Client getClient(String key) {

        int index = hashClient(key);
        Node head = buckets[index];

        while (head != null) {

            if (head.key.equals(key)) {
                return head.value;
            }

            head = head.next;
        }

        return null;
    }

    public void removeClient(String key) {

        int index = hashClient(key);
        Node head = buckets[index];
        Node prev = null;

        while (head != null) {

            if (head.key.equals(key)) {

                if (prev == null) {
                    buckets[index] = head.next;
                } else {
                    prev.next = head.next;
                }

                size--;
                return;
            }

            prev = head;
            head = head.next;
        }
    }

    public boolean containsKey_Client(String key) {
        return getClient(key) != null;
    }

    public int size() {
        return size;
    }

    public void displayClient() {

        System.out.println("---- Registered Clients ----");

        for (int i = 0; i < capacity; i++) {

            Node head = buckets[i];

            while (head != null) {
                System.out.println("Client ID: " + head.key +
                        " | Name: " + head.value.getName());
                head = head.next;
            }
        }

        System.out.println("-----------------------------");
    }
}