package edu.course.lab06;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Stack<T> {
    private final List<T> items = new ArrayList<>();

    public void push(T value) {
        Objects.requireNonNull(value, "null is not allowed");
        items.add(value);
    }

    public T pop() {
        if (items.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (items.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return items.get(items.size() - 1);
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}