package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SampleRegistry {
    private final Map<SampleId, DataSample> samples = new HashMap<>();

    /** Добавляет или заменяет значение. Возвращает прежнее значение или null. */
    public DataSample put(SampleId id, DataSample sample) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(sample, "sample");
        return samples.put(id, sample);
    }

    /** Возвращает значение или null, если ключа нет. */
    public DataSample get(SampleId id) {
        return samples.get(id);
    }

    /** Удаляет значение. Возвращает удалённое значение или null. */
    public DataSample remove(SampleId id) {
        return samples.remove(id);
    }

    public boolean containsKey(SampleId id) {
        return samples.containsKey(id);
    }

    public int size() {
        return samples.size();
    }
}