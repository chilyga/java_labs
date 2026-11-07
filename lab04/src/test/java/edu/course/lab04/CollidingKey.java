package edu.course.lab04;

import java.util.Objects;

/** Учебный ключ: equals по name, но hashCode всегда одинаковый. */
final class CollidingKey {
    private final String name;

    CollidingKey(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return name.equals(((CollidingKey) o).name);
    }

    @Override
    public int hashCode() {
        return 42; // намеренная коллизия: контракт не нарушен, но все ключи в одной корзине
    }

    @Override
    public String toString() {
        return "CollidingKey{" + name + "}";
    }
}