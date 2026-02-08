package com.example.ca1bookingfx;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    @Test
    void testShowConstructor() {
        Show show = new Show("Hamilton", 120, "2023-10-01", "2023-10-15", 50, 75, 100);
        assertEquals("Hamilton", show.getTitle());
        assertEquals(120, show.getRunningTime());
        assertEquals("2023-10-01", show.getStartDate());
        assertEquals("2023-10-15", show.getEndDate());
        assertEquals(50, show.getStallPrice());
        assertEquals(75, show.getCirclePrice());
        assertEquals(100, show.getBalconyPrice());
    }

    @Test
    void testGetAndSetTitle() {
        Show show = new Show("Old Title", 90, "2023-10-01", "2023-10-15", 50, 75, 100);
        show.setTitle("New Title");
        assertEquals("New Title", show.getTitle());
    }

    @Test
    void testGetAndSetRunningTime() {
        Show show = new Show("Hamilton", 90, "2023-10-01", "2023-10-15", 50, 75, 100);
        show.setRunningTime(150);
        assertEquals(150, show.getRunningTime());
    }

    @Test
    void testGetAndSetStartDate() {
        Show show = new Show("Hamilton", 90, "2023-10-01", "2023-10-15", 50, 75, 100);
        show.setStartDate("2023-11-01");
        assertEquals("2023-11-01", show.getStartDate());
    }

    @Test
    void testGetAndSetEndDate() {
        Show show = new Show("Hamilton", 90, "2023-10-01", "2023-10-15", 50, 75, 100);
        show.setEndDate("2023-11-15");
        assertEquals("2023-11-15", show.getEndDate());
    }

    @Test
    void testGetAndSetStallPrice() {
        Show show = new Show("Hamilton", 90, "2023-10-01", "2023-10-15", 50, 75, 100);
        show.setStallPrice(60);
        assertEquals(60, show.getStallPrice());
    }

    @Test
    void testGetAndSetCirclePrice() {
        Show show = new Show("Hamilton", 90, "2023-10-01", "2023-10-15", 50, 75, 100);
        show.setCirclePrice(85);
        assertEquals(85, show.getCirclePrice());
    }

    @Test
    void testGetAndSetBalconyPrice() {
        Show show = new Show("Hamilton", 90, "2023-10-01", "2023-10-15", 50, 75, 100);
        show.setBalconyPrice(110);
        assertEquals(110, show.getBalconyPrice());
    }


    @Test
    void testToString() {
        Show show = new Show("Hamilton", 120, "2023-10-01", "2023-10-15", 50, 75, 100);
        String expected = "Show{title='Hamilton', runningTime=120, startDate='2023-10-01', endDate='2023-10-15', stallPrice=50, circlePrice=75, balconyPrice=100}";
        assertEquals(expected, show.toString());
    }
}
