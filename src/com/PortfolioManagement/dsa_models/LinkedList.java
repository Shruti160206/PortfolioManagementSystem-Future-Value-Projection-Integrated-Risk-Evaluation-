// LINKED LIST: LinkedList used for adding new asset for storage. The portfolio size keeps changing dynamically as we keep adding asset
// Single Linked List

package com.PortfolioManagement.dsa_models;

public class LinkedList {

    public class Node {
        public Object data;
        public Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public LinkedList() {
        head = null;
    }

    public void add(Object data) {

        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    public Node getHead() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void remove(Object data) {

        if (head == null)
            return;

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node temp = head;

        while (temp.next != null) {

            if (temp.next.data == data) {
                temp.next = temp.next.next;
                return;
            }

            temp = temp.next;
        }
    }
}