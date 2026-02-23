// STACK - Here, Stack is used to undo last transaction stored.
// Push helps to store/save the transaction, Pop is used to remove/undo last transaction, peek is used to view last transaction,
package com.PortfolioManagement.dsa_models;

public class Stack {

    private Node top;

    private class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }
    }

    public Stack() {
        top = null;
    }

    public void push(Object data) {

        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public Object pop() {

        if (top == null)
            return null;

        Object data = top.data;
        top = top.next;

        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}