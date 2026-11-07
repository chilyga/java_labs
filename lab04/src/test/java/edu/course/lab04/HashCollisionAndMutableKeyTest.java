package edu.course.lab04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HashCollisionAndMutableKeyTest {

    @Test
    void unequalKeysWithSameHashCodeKeepDifferentValues() {
        CollidingKey a = new CollidingKey("a");
        CollidingKey b = new CollidingKey("b");

        assertEquals(a.hashCode(), b.hashCode()); // хеши совпадают
        assertNotEquals(a, b);                    // но ключи логически разные

        Map<CollidingKey, String> map = new HashMap<>();
        map.put(a, "value-a");
        map.put(b, "value-b");

        assertEquals(2, map.size());
        assertEquals("value-a", map.get(new CollidingKey("a")));
        assertEquals("value-b", map.get(new CollidingKey("b")));
    }

    @Test
    void mutatingKeyAfterInsertBreaksLookup() {
        Map<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey("before");
        map.put(key, "value");

        assertEquals("value", map.get(key)); // пока ключ не менялся — находится

        key.setName("after"); // меняем поле, участвующее в hashCode

        // старый хеш не совпадает с новым: ни по изменённому, ни по прежнему ключу не найти
        assertNull(map.get(key));
        assertNull(map.get(new MutableKey("before")));
        assertNull(map.get(new MutableKey("after")));

        // запись при этом осталась в карте
        assertEquals(1, map.size());
    }
}