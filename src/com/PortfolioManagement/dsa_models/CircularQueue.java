// Circular Queue : Helps to store the asset information in the queue
package com.PortfolioManagement.dsa_models;

public class CircularQueue {

    private Object[] queue;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(Object data) {

        if (isFull()) {
            System.out.println("Queue is full.");
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = data;
        size++;
    }

    public Object dequeue() {

        if (isEmpty()) {
            return null;
        }

        Object data = queue[front];
        front = (front + 1) % capacity;
        size--;

        return data;
    }
}