package com.example.ca1bookingfx;

import javafx.scene.control.ListView;

public class LinkedList<T> {
    public Node<T> head; // Head of the linked list
    private int size;     // Tracks the number of elements in the list

    // Constructor to initialize an empty list
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Add an element to the end of the list
    public void add(T data) {
        Node<T> newNode = (Node<T>) new Node<>(data); // Create a new node with the data

        if (head == null) {
            head = newNode; // If the list is empty, set the new node as the head
        } else {
            Node<T> current = head;
            while (current.nextNode != null) {
                current = current.nextNode; // Traverse to the end of the list
            }
            current.nextNode = newNode; // Link the last node to the new node
        }
        size++;
    }

    // Remove an element by value (returns true if successful)
    public boolean remove(T data) {
        if (head == null) return false; // List is empty

        if (head.contents.equals(data)) {
            head = head.nextNode; // Remove the head node
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.nextNode != null && !current.nextNode.contents.equals(data)) {
            current = current.nextNode; // Traverse the list to find the node
        }

        if (current.nextNode != null) {
            current.nextNode = current.nextNode.nextNode; // Remove the node
            size--;
            return true;
        }

        return false; // Data not found
    }

    // Get an element by index
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.nextNode;
        }
        return current.contents;
    }
    public Node<T> getHead() {
        return head;
    }
    // Check if the list contains a specific element
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.contents.equals(data)) return true;
            current = current.nextNode;
        }
        return false;
    }

    // Get the size of the linked list
    public int size() {
        return size;
    }

    // Display all elements in the list
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.contents);
            current = current.nextNode;
        }
    }
    public void viewList(ListView<Show> showList) {
        if (showList == null) {
            System.out.println("Error: The ListView is null!");
            return;  // Exit the method if show is null
        }

        // Clear the existing items in the ListView
        showList.getItems().clear();

        // Ensure you're iterating over the correct type (Node<Show>)
        Node<Show> temp = (Node<Show>) head;
        while (temp != null) {
            showList.getItems().add(temp.contents);  // Add the Show to the ListView
            temp = temp.nextNode;
        }
    }

    public void viewCustomerList(ListView<Customer> customerList) {
        if (customerList == null) {
            System.out.println("Error: The ListView is null!");
            return;  // Exit the method if show is null
        }

        // Clear the existing items in the ListView
        customerList.getItems().clear();

        // Ensure you're iterating over the correct type (Node<Show>)
        Node<Customer> current = (Node<Customer>) head;
        while (current != null) {
            customerList.getItems().add(current.contents);  // Add the Show to the ListView
            current = current.nextNode;
        }
    }
    public void viewBookingList(ListView<Booking> bookingList) {
        if (bookingList == null) {
            System.out.println("Error: The ListView is null!");
            return;
        }

        bookingList.getItems().clear(); // Clear existing items

        Node<Booking> current = (Node<Booking>) head; // Start from the head
        while (current != null) {
            bookingList.getItems().add(current.contents); // Add each booking to the list
            current = current.nextNode; // Move to the next node
        }
    }

    public boolean isEmpty() {
        return head == null;  // If head is null, the list is empty
    }

}