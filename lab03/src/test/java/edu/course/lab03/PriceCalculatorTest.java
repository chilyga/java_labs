package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void usesProvidedDiscountPolicy() {
        PriceCalculator calculator =
                new PriceCalculator(new PercentDiscount(20));

        assertEquals(80, calculator.calculate(100), 1e-9);
    }

    @Test
    void replacesDiscountPolicy() {
        PriceCalculator calculator =
                new PriceCalculator(new NoDiscount());

        assertEquals(100, calculator.calculate(100));

        calculator = new PriceCalculator(new PercentDiscount(20));

        assertEquals(80, calculator.calculate(100), 1e-9);
    }
}