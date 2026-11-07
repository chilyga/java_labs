package edu.course.lab04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SampleIdTest {

    @Test
    void equalIdsHaveEqualHashCodes() {
        SampleId a = new SampleId("iris", 4);
        SampleId b = new SampleId("iris", 4);

        assertNotSame(a, b);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void differentIdsAreNotEqual() {
        assertNotEquals(new SampleId("iris", 4), new SampleId("iris", 5));
        assertNotEquals(new SampleId("iris", 4), new SampleId("mnist", 4));
    }

    @Test
    void equalsReturnsFalseForNull() {
        SampleId id = new SampleId("iris", 4);
        assertFalse(id.equals(null));
    }

    @Test
    void equalsReturnsFalseForOtherType() {
        SampleId id = new SampleId("iris", 4);
        assertFalse(id.equals("iris#4"));
    }

    @Test
    void equalsIsReflexiveAndSymmetric() {
        SampleId a = new SampleId("iris", 4);
        SampleId b = new SampleId("iris", 4);

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(b, a);
    }
}