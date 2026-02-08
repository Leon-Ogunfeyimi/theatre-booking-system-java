package com.example.ca1bookingfx;
public class Seat {
    private String seatNumber;  // Seat identifier (e.g., "S1", "C2")

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return seatNumber;  // This will return the seat number when Seat is printed
    }
}

