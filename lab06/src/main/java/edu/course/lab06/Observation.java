package edu.course.lab06;

public final class Observation {
    private final int id;
    private final String status;
    private final String source;
    private final int featureCount;

    public Observation(int id, String status, String source, int featureCount) {
        this.id = id;
        this.status = status;
        this.source = source;
        this.featureCount = featureCount;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public int getFeatureCount() {
        return featureCount;
    }

    @Override
    public String toString() {
        return "Observation{id=" + id + ", status=" + status
                + ", source=" + source + ", featureCount=" + featureCount + "}";
    }
}