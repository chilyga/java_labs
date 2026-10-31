package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoDiscountTest {

    @Test
    void returnsOriginalPrice() {
        NoDiscount discount = new NoDiscount();

        assertEquals(100, discount.apply(100));
    }

    @Test
    void rejectsNegativePrice() {
        NoDiscount discount = new NoDiscount();

        assertThrows(IllegalArgumentException.class,
                () -> discount.apply(-10));
    }
}