package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void zeroIsEven() {
        boolean result = CourseToolkit.isEven(0);

        assertTrue(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

    @Test
    void isPrimeReturnsFalseForZero() {
        assertFalse(CourseToolkit.isPrime(0));
    }

    @Test
    void isPrimeReturnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrimeReturnsFalseForComposite() {
        assertFalse(CourseToolkit.isPrime(4));
    }

    @Test
    void isPrimeReturnsFalseForSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49)); // 49 = 7 × 7
    }

    @Test
    void isPrimeReturnsTrueForLargePrime() {
        assertTrue(CourseToolkit.isPrime(97));
    }

    @Test
    void isPalindromeReturnsTrueForSimplePalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void isPalindromeReturnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("hello"));
    }

    @Test
    void isPalindromeReturnsFalseForDifferentCase() {
        assertFalse(CourseToolkit.isPalindrome("Level")); // L != l, регистр значим
    }

    @Test
    void isPalindromeReturnsFalseForSpaces() {
        assertFalse(CourseToolkit.isPalindrome("ab a")); // пробелы значимы
    }

    @Test
    void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageReturnsCorrectValue() {
        int[] values = {2, 4, 6};

        double result = CourseToolkit.average(values);

        assertEquals(4.0, result);
    }

    @Test
    void averageHandlesNegativeNumbers() {
        int[] values = {-2, -4, -6};

        double result = CourseToolkit.average(values);

        assertEquals(-4.0, result);
    }

    @Test
    void averageThrowsForEmptyArray() {
        int[] values = {};

        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(values));
    }

    @Test
    void averageThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(null));
    }

    @Test
    void averageDoesNotChangeInputArray() {
        int[] values = {1, 2, 3, 4};
        int[] copy = {1, 2, 3, 4};

        CourseToolkit.average(values);

        assertArrayEquals(copy, values);
    }
}
