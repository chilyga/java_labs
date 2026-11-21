package edu.course.lab06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Analytics {

    private Analytics() {
    }

    public static Map<String, Integer> countByStatus(List<Observation> observations) {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Observation o : observations) {
            String status = o.getStatus();
            if (result.containsKey(status)) {
                result.put(status, result.get(status) + 1);
            } else {
                result.put(status, 1);
            }
        }
        return result;
    }

    public static Set<String> uniqueSources(List<Observation> observations) {
        Set<String> result = new LinkedHashSet<>();
        for (Observation o : observations) {
            result.add(o.getSource());
        }
        return result;
    }

    public static List<Observation> sortedByFeaturesAndId(List<Observation> observations) {
        List<Observation> copy = new ArrayList<>(observations);
        copy.sort(Comparator.comparingInt(Observation::getFeatureCount)
                .thenComparingInt(Observation::getId));
        return copy;
    }
}