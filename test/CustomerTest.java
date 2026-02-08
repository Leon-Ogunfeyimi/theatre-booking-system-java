package com.example.ca1bookingfx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void validCustomerCreation() {
        Customer customer = new Customer("Jimmy Boy", "jimjime@gmail.com", "3531234676");
        assertEquals("Jimmy Boy", customer.getName());
        assertEquals("jimjime@gmail.com", customer.getEmail());
        assertEquals("3531234676", customer.getPhone());
    }

    @Test
    void invalidNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("", "jimjime@gmail.com", "3531234676");
        });
        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    void invalidEmailThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Jimmy Boy", "invalidemail", "3531234676");
        });
        assertEquals("Invalid email address.", exception.getMessage());
    }

    @Test
    void invalidPhoneThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Jimmy Boy", "jimjime@gmail.com", "35312");
        });
        assertEquals("Phone number must be 10 digits.", exception.getMessage());
    }

    @Test
    void toStringMethod() {
        Customer customer = new Customer("Jimmy Boy", "jimjime@gmail.com", "3531234676");
        String expected = "Customer{Name='Jimmy Boy', Email='jimjime@gmail.com', Phone='3531234676'}";
        assertEquals(expected, customer.toString());
    }
}
