package edu.course.lab04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SampleRegistryTest {

    private SampleRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new SampleRegistry();
    }

    private static DataSample sample(String label) {
        return new DataSample(label, new double[] {1.0, 2.0});
    }

    @Test
    void twoEqualKeyInstancesFindSameValue() {
        DataSample s = sample("cat");
        registry.put(new SampleId("iris", 1), s);

        assertSame(s, registry.get(new SampleId("iris", 1)));
    }

    @Test
    void repeatedPutWithEqualKeyReplacesValueAndKeepsSize() {
        DataSample first = sample("cat");
        DataSample second = sample("dog");

        assertNull(registry.put(new SampleId("iris", 1), first));
        assertSame(first, registry.put(new SampleId("iris", 1), second));

        assertEquals(1, registry.size());
        assertSame(second, registry.get(new SampleId("iris", 1)));
    }

    @Test
    void getReturnsNullForMissingKey() {
        assertNull(registry.get(new SampleId("iris", 99)));
    }

    @Test
    void removeDeletesValue() {
        DataSample s = sample("cat");
        registry.put(new SampleId("iris", 1), s);

        assertSame(s, registry.remove(new SampleId("iris", 1)));
        assertFalse(registry.containsKey(new SampleId("iris", 1)));
        assertEquals(0, registry.size());
    }

    @Test
    void removeMissingKeyReturnsNull() {
        assertNull(registry.remove(new SampleId("iris", 1)));
    }

    @Test
    void containsKeyWorksByLogicalEquality() {
        registry.put(new SampleId("iris", 1), sample("cat"));

        assertTrue(registry.containsKey(new SampleId("iris", 1)));
        assertFalse(registry.containsKey(new SampleId("iris", 2)));
        assertFalse(registry.containsKey(new SampleId("mnist", 1)));
    }

    @Test
    void sizeGrowsWithDistinctKeys() {
        assertEquals(0, registry.size());
        registry.put(new SampleId("iris", 1), sample("a"));
        registry.put(new SampleId("iris", 2), sample("b"));
        registry.put(new SampleId("mnist", 1), sample("c"));
        assertEquals(3, registry.size());
    }

    @Test
    void nullKeyOrValueIsRejected() {
        assertThrows(NullPointerException.class, () -> registry.put(null, sample("a")));
        assertThrows(NullPointerException.class, () -> registry.put(new SampleId("iris", 1), null));
    }
}