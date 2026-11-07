package edu.course.lab04;

import java.util.Objects;

public class DataSample {
    private final String label;
    private final double[] features;

    public DataSample(String label, double[] features) {
        this.label = Objects.requireNonNull(label, "label");
        // защитная копия, чтобы внешний код не менял содержимое
        this.features = Objects.requireNonNull(features, "features").clone();
    }

    public String getLabel() {
        return label;
    }

    public double[] getFeatures() {
        return features.clone();
    }

    @Override
    public String toString() {
        return "DataSample{label='" + label + "', features=" + java.util.Arrays.toString(features) + "}";
    }
}