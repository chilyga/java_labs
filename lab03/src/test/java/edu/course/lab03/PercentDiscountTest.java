package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentDiscountTest {

    @Test
    void appliesTwentyPercentDiscount() {
        PercentDiscount discount = new PercentDiscount(20);

        assertEquals(80, discount.apply(100), 1e-9);
    }

    @Test
    void rejectsInvalidPercent() {
        assertThrows(IllegalArgumentException.class,
                () -> new PercentDiscount(120));
    }

    @Test
    void rejectsNegativePrice() {
        PercentDiscount discount = new PercentDiscount(20);

        assertThrows(IllegalArgumentException.class,
                () -> discount.apply(-10));
    }
}