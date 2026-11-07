package edu.course.lab04;

import java.util.Objects;

public final class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        this.isbn = Objects.requireNonNull(isbn, "isbn");
        this.title = Objects.requireNonNull(title, "title");
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    // Логическое равенство определяется только ISBN
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book other = (Book) o;
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return "Book{isbn='" + isbn + "', title='" + title + "'}";
    }
}