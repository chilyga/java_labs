package edu.course.lab04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    void valueIsFoundByAnotherInstanceWithSameIsbn() {
        Map<Book, String> shelf = new HashMap<>();
        shelf.put(new Book("978-5-17-090000-1", "Java"), "A-12");

        Book another = new Book("978-5-17-090000-1", "Другое название");

        assertEquals("A-12", shelf.get(another));
        assertEquals(new Book("978-5-17-090000-1", "x").hashCode(), another.hashCode());
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book a = new Book("111", "Same title");
        Book b = new Book("222", "Same title");

        assertNotEquals(a, b);
    }
}