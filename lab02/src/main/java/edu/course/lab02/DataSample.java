package edu.course.lab02;

import java.util.Arrays;

public class DataSample {

    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status,
                      double[] features) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID не должен быть пустым");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Метка не должна быть пустой");
        }
        if (status == null) {
            throw new IllegalArgumentException("Статус не должен быть null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException(
                    "Массив признаков не должен быть пустым");
        }

        for (double feature : features) {
            if (!Double.isFinite(feature)) {
                throw new IllegalArgumentException(
                        "Признаки должны быть конечными числами");
            }
        }

        this.id = id;
        this.label = label;
        this.status = status;
        this.features = Arrays.copyOf(features, features.length);
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return Arrays.copyOf(features, features.length);
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException(
                    "Статус не должен быть null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double calculateAverage() {
        double sum = 0;

        for (double feature : features) {
            sum += feature;
        }

        return sum / features.length;
    }
}
