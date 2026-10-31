package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxNormalizationTest {

    @Test
    void normalizesValuesBetweenZeroAndOne() {
        MinMaxNormalization normalization = new MinMaxNormalization();

        double[] result = normalization.normalize(
                new double[]{10, 20, 30}
        );

        assertArrayEquals(new double[]{0, 0.5, 1}, result, 1e-9);
    }

    @Test
    void doesNotChangeOriginalArray() {
        MinMaxNormalization normalization = new MinMaxNormalization();

        double[] values = {10, 20, 30};
        double[] original = values.clone();

        normalization.normalize(values);

        assertArrayEquals(original, values);
    }

    @Test
    void returnsZerosForEqualValues() {
        MinMaxNormalization normalization = new MinMaxNormalization();

        double[] result = normalization.normalize(
                new double[]{5, 5, 5}
        );

        assertArrayEquals(new double[]{0, 0, 0}, result);
    }
}