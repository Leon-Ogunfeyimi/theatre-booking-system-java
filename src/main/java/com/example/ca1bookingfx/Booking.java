package com.example.ca1bookingfx;

import java.util.List;

public class Booking {
    private static int nextBookingId = 1; // Static to generate unique booking IDs
    private int bookingId;
    private Customer customer;
    private Performance performance;
    private List<String> seats;  // List of booked seats

    public Booking(Customer customer, Performance performance, List<String> seats) {
        this.bookingId = nextBookingId++; // Generate unique ID for each booking
        this.customer = customer;
        this.performance = performance;
        this.seats = seats;
    }

    public Performance getPerformance() {
        return performance;
    }

    public List<String> getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "Booking ID=" + bookingId + ", " +
                "Customer=" + customer.getName() + ", " +
                "Performance=" + performance.toString() + ", " +
                "Seats=" + seats + '}';
    }

}