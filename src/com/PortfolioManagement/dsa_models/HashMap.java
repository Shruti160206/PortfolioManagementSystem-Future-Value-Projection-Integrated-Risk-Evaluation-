// HASH MAP : Maps String Key to Any Object value

package com.PortfolioManagement.dsa_models;

public class HashMap {

    private class Entry {
        String key;
        Object value;
        Entry next;

        Entry(String key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry[] table;
    private int capacity = 10;

    public HashMap() {
        table = new Entry[capacity];
    }

    private int hash(String key) {

        int sum = 0;

        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }

        if (sum < 0) {
            sum = -sum;
        }

        return sum % capacity;
    }

    public void put(String key, Object value) {

        int index = hash(key);

        Entry head = table[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;  // Update value
                return;
            }
            head = head.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
    }

    public Object get(String key) {

        int index = hash(key);

        Entry temp = table[index];

        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }

        return null;
    }

    public void remove(String key) {

        int index = hash(key);

        Entry temp = table[index];
        Entry prev = null;

        while (temp != null) {

            if (temp.key.equals(key)) {

                if (prev == null) {
                    table[index] = temp.next; // removing first node
                } else {
                    prev.next = temp.next;    // removing middle/last node
                }

                return;
            }

            prev = temp;
            temp = temp.next;
        }
    }
}
