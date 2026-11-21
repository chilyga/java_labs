package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyticsTest {

    private List<Observation> sample() {
        List<Observation> list = new ArrayList<>();
        list.add(new Observation(3, "NEW", "sensor-1", 10));
        list.add(new Observation(1, "DONE", "camera", 5));
        list.add(new Observation(2, "NEW", "sensor-1", 5));
        list.add(new Observation(4, "NEW", "api", 20));
        return list;
    }

    @Test
    void countsByStatus() {
        Map<String, Integer> result = Analytics.countByStatus(sample());
        assertEquals(2, result.size());
        assertEquals(3, result.get("NEW"));
        assertEquals(1, result.get("DONE"));
    }

    @Test
    void returnsUniqueSources() {
        Set<String> result = Analytics.uniqueSources(sample());
        assertEquals(3, result.size());
        assertEquals(Set.of("sensor-1", "camera", "api"), result);
    }

    @Test
    void sortsByFeatureCountThenId() {
        List<Observation> sorted = Analytics.sortedByFeaturesAndId(sample());
        assertEquals(1, sorted.get(0).getId());
        assertEquals(2, sorted.get(1).getId());
        assertEquals(3, sorted.get(2).getId());
        assertEquals(4, sorted.get(3).getId());
    }

    @Test
    void sortingDoesNotChangeOriginalList() {
        List<Observation> original = sample();
        Analytics.sortedByFeaturesAndId(original);
        assertEquals(3, original.get(0).getId());
        assertEquals(1, original.get(1).getId());
        assertEquals(2, original.get(2).getId());
        assertEquals(4, original.get(3).getId());
    }

    @Test
    void emptyListGivesEmptyResults() {
        List<Observation> empty = new ArrayList<>();
        assertEquals(0, Analytics.countByStatus(empty).size());
        assertEquals(0, Analytics.uniqueSources(empty).size());
        assertEquals(0, Analytics.sortedByFeaturesAndId(empty).size());
    }
}