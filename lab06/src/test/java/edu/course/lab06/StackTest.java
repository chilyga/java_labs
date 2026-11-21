package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void newStackIsEmpty() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushIncreasesSizeAndPeekReturnsLast() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertEquals("b", stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void popReturnsElementsInLifoOrder() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void worksWithObservation() {
        Stack<Observation> stack = new Stack<>();
        Observation o = new Observation(1, "NEW", "sensor-1", 5);
        stack.push(o);

        assertSame(o, stack.pop());
    }

    @Test
    void popOnEmptyThrows() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void peekOnEmptyThrows() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void pushNullIsForbidden() {
        Stack<String> stack = new Stack<>();
        assertThrows(NullPointerException.class, () -> stack.push(null));
    }
}