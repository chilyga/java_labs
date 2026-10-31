package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeanCenteringNormalizationTest {

    @Test
    void subtractsMeanFromValues() {
        MeanCenteringNormalization normalization =
                new MeanCenteringNormalization();

        double[] result = normalization.normalize(
                new double[]{10, 20, 30}
        );

        assertArrayEquals(new double[]{-10, 0, 10}, result, 1e-9);
    }

    @Test
    void doesNotChangeOriginalArray() {
        MeanCenteringNormalization normalization =
                new MeanCenteringNormalization();

        double[] values = {2, 4, 6};
        double[] original = values.clone();

        normalization.normalize(values);

        assertArrayEquals(original, values);
    }

    @Test
    void returnsEmptyArrayForEmptyInput() {
        MeanCenteringNormalization normalization =
                new MeanCenteringNormalization();

        double[] result = normalization.normalize(new double[]{});

        assertArrayEquals(new double[]{}, result);
    }
}