package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataSampleTest {

    @Test
    void createsValidSample() {
        DataSample sample = new DataSample(
                "S1", "cat", SampleStatus.NEW,
                new double[]{2.0, 4.0, 6.0}
        );

        assertEquals("S1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
        assertFalse(sample.isReady());
    }

    @Test
    void rejectsInvalidId() {
        assertThrows(IllegalArgumentException.class, () ->
                new DataSample("", "cat", SampleStatus.NEW,
                        new double[]{1.0}));
    }

    @Test
    void rejectsInvalidLabel() {
        assertThrows(IllegalArgumentException.class, () ->
                new DataSample("S1", " ", SampleStatus.NEW,
                        new double[]{1.0}));
    }

    @Test
    void rejectsNullStatus() {
        assertThrows(IllegalArgumentException.class, () ->
                new DataSample("S1", "cat", null,
                        new double[]{1.0}));
    }

    @Test
    void rejectsEmptyFeatures() {
        assertThrows(IllegalArgumentException.class, () ->
                new DataSample("S1", "cat", SampleStatus.NEW,
                        new double[]{}));
    }

    @Test
    void rejectsNonFiniteFeatures() {
        assertThrows(IllegalArgumentException.class, () ->
                new DataSample("S1", "cat", SampleStatus.NEW,
                        new double[]{Double.NaN}));
    }

    @Test
    void changesStatus() {
        DataSample sample = new DataSample(
                "S1", "cat", SampleStatus.NEW,
                new double[]{1.0}
        );

        sample.changeStatus(SampleStatus.READY);

        assertEquals(SampleStatus.READY, sample.getStatus());
        assertTrue(sample.isReady());
    }

    @Test
    void calculatesAverage() {
        DataSample sample = new DataSample(
                "S1", "cat", SampleStatus.NEW,
                new double[]{2.0, 4.0, 6.0}
        );

        assertEquals(4.0, sample.calculateAverage(), 0.0001);
    }

    @Test
    void protectsFeaturesFromExternalChanges() {
        double[] features = {1.0, 2.0};
        DataSample sample = new DataSample(
                "S1", "cat", SampleStatus.NEW, features
        );

        features[0] = 100.0;
        assertEquals(1.0, sample.getFeatures()[0]);

        double[] returned = sample.getFeatures();
        returned[0] = 200.0;
        assertEquals(1.0, sample.getFeatures()[0]);
    }
}
