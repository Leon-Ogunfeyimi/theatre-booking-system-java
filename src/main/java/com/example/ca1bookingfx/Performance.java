package com.example.ca1bookingfx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Performance {

    private Show show;          // Reference to the associated show
    private LocalDate date;     // Date of the performance
    private String time;        // Either "Afternoon" or "Evening"
    private List<String> availableSeats; // List of available seats (e.g., "A1", "B1", "C1")

    public Performance(Show show, LocalDate date, String time) {
        if (!time.equalsIgnoreCase("Afternoon") && !time.equalsIgnoreCase("Evening")) {
            throw new IllegalArgumentException("Time must be 'Afternoon' or 'Evening'.");
        }
        this.show = show;
        this.date = date;
        this.time = time;
        this.availableSeats = new ArrayList<>();

        // Assume that rows A to E and seats 1 to 10 are available by default
        for (char row = 'A'; row <= 'E'; row++) {
            for (int seat = 1; seat <= 10; seat++) {
                availableSeats.add(row + String.valueOf(seat));
            }
        }
    }

    public Show getShow() {
        return show;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<String> getAvailableSeats() {
        return availableSeats;
    }

    // Method to book seats and remove them from available list
    public boolean bookSeats(List<String> seats) {
        if (availableSeats.containsAll(seats)) {
            availableSeats.removeAll(seats);  // Remove booked seats from available list
            return true;
        }
        return false;  // Not enough available seats
    }

    @Override
    public String toString() {
        return "Performance{" +
                "Show=" + show.toString() + ", " +
                "Date=" + date + ", " +
                "Time='" + time + "'}";
    }

    public void releaseSeats(List<String> seats) {
        for (String seat : seats) {
            if (!availableSeats.contains(seat)) {
                availableSeats.add(seat); // Add the seat back to the list of available seats
            }
        }
    }

}

