package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    private Book testBook, testBook1, testBook2;
    private Set<Book> testBooks = new TreeSet<>();
    @BeforeEach
    void setUp() {
        testBook = new Book("Author", "Title", "2023");
        testBook1 = new Book("Writer", "Book Name", "2024");
        testBook2 = new Book("Creator", "Cover", "2025");
        testBooks.add(testBook);
        testBooks.add(testBook1);
        testBooks.add(testBook2);
    }

    @AfterEach
    void tearDown() {
        Path pathCSV = Paths.get("./Book.csv");
        Path pathXML = Paths.get("./Books.xml");
        Path pathBinary = Paths.get("./Book.ser");
        try {
            Files.deleteIfExists(pathCSV);
            Files.deleteIfExists(pathXML);
            Files.deleteIfExists(pathBinary);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Test
    void csvSerializationDeserializationTest() {
        Path path = Paths.get("./Book.csv");
        Book.serializeToCSV(path, testBooks);

        Set<Book> otherBooks = Book.deserializeFromCSV(path);

        System.out.println(testBooks.equals(otherBooks));

        assertEquals(testBooks, otherBooks);
    }

    @Test
    void xmlSerializationDeserializationTest() {
        Path path = Paths.get("./Books.xml");
        Book.serializeToXML(path, testBooks);

        Set<Book> otherBooks = Book.deserializeFromXML(path);

        assertEquals(testBooks, otherBooks);
    }

    @Test
    void binarySerializationDeserializationTest() {
        String path = "./Book.ser";
        Set<Object> bookObjects = (TreeSet<Object>) (Object) testBooks;
        BinarySerialization.serializeToBinary(path, bookObjects);

        Set<Book> otherBooks = (TreeSet<Book>) (Object) BinarySerialization.deserializeFromBinary(path);

        assertEquals(testBooks, otherBooks);
    }
}