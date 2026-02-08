package com.example.ca1bookingfx;

class Node<T> {
    public T contents;      // Data stored in the node
    public Node<T> nextNode; // Pointer to the next node

    public Node(T contents) {
        this.contents = contents;
        this.nextNode = null;
    }
}
