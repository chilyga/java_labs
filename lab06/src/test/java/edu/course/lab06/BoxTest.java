package edu.course.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxTest {

    @Test
    void stringBoxStoresAndReturnsString() {
        Box<String> box = new Box<>("hello");
        assertEquals("hello", box.get());

        box.set("world");
        assertEquals("world", box.get());

        // Не компилируется: Box<String> не принимает int
        // box.set(42);
    }

    @Test
    void integerBoxStoresAndReturnsInteger() {
        Box<Integer> box = new Box<>(10);
        assertEquals(10, box.get());

        box.set(20);
        assertEquals(20, box.get());
    }
}