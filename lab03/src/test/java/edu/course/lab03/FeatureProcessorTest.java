package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeatureProcessorTest {

    @Test
    void usesMinMaxNormalization() {
        FeatureProcessor processor =
                new FeatureProcessor(new MinMaxNormalization());

        double[] result = processor.process(new double[]{10, 20, 30});

        assertArrayEquals(new double[]{0, 0.5, 1}, result, 1e-9);
    }

    @Test
    void replacesNormalizationStrategy() {
        FeatureProcessor processor =
                new FeatureProcessor(new MinMaxNormalization());

        double[] firstResult = processor.process(new double[]{10, 20, 30});

        processor = new FeatureProcessor(new MeanCenteringNormalization());

        double[] secondResult = processor.process(new double[]{10, 20, 30});

        assertArrayEquals(new double[]{0, 0.5, 1}, firstResult, 1e-9);
        assertArrayEquals(new double[]{-10, 0, 10}, secondResult, 1e-9);
    }
}