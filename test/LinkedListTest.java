package com.example.ca1bookingfx;

import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void testAdd() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Item 1");
        list.add("Item 2");
        assertEquals(2, list.size());
        assertEquals("Item 1", list.get(0));
        assertEquals("Item 2", list.get(1));
    }

    @Test
    void testRemove() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Item 1");
        list.add("Item 2");
        assertTrue(list.remove("Item 1"));
        assertFalse(list.contains("Item 1"));
        assertEquals(1, list.size());
    }

    @Test
    void testGet() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Item 1");
        list.add("Item 2");
        assertEquals("Item 1", list.get(0));
        assertEquals("Item 2", list.get(1));
    }

    @Test
    void testGetThrowsIndexOutOfBoundsException() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Item 1");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void testContains() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Item 1");
        assertTrue(list.contains("Item 1"));
        assertFalse(list.contains("Item 2"));
    }

    @Test
    void testSize() {
        LinkedList<String> list = new LinkedList<>();
        assertEquals(0, list.size());
        list.add("Item 1");
        list.add("Item 2");
        assertEquals(2, list.size());
    }

    @Test
    void testIsEmpty() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.add("Item 1");
        assertFalse(list.isEmpty());
    }

    @Test
    void testDisplay() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Item 1");
        list.add("Item 2");

        // Capture console output for validation if needed (optional)
        list.display(); // Manual verification for now
    }
}
