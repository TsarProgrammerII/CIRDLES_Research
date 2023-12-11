package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book testBook;
    @BeforeEach
    void setUp() {
        testBook = new Book("Author", "Title", "2023");
    }

    @AfterEach
    void tearDown() {
        Path path = Paths.get("./Book.csv");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAuthor() {
        assertEquals("Author", testBook.getAuthor());
    }

    @Test
    void getTitle() {
        assertEquals("Title", testBook.getTitle());
    }

    @Test
    void getPublishDate() {
        assertEquals("2023", testBook.getPublishDate());
    }

    @Test
    void mainTest() {

        Book.serialize(testBook);

        Book otherBook = Book.deserialize();

        if (testBook.equals(otherBook))
        {
            System.out.println("The two books are equal");
            assert(true);
        }
        else
        {
            System.out.println("The two books are not equal");
            assert(false);
        }

    }
}