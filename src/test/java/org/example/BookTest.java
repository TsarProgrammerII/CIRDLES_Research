package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book testBook, testBook1, testBook2;
    private List<Book> books = new ArrayList<>();
    @BeforeEach
    void setUp() {
        testBook = new Book("Author", "Title", "2023");
        testBook1 = new Book("Writer", "Book Name", "2024");
        testBook2 = new Book("Creator", "Cover", "2025");
        books.add(testBook);
        books.add(testBook1);
        books.add(testBook2);
    }

    @AfterEach
    void tearDown() {
        Path pathCSV = Paths.get("./Book.csv");
        Path pathXML = Paths.get("./Books.xml");
        Path pathBinary = Paths.get("./Book.txt");
        try {
            Files.deleteIfExists(pathCSV);
            Files.deleteIfExists(pathXML);
            Files.deleteIfExists(pathBinary);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Test
    void mainTest() {

        Book.serializeToCSV(books);

        List<Book> otherBooks = Book.deserializeFromCSV();

        for (int i = 0; i < otherBooks.size(); i++) {
            if (books.get(i).equals(otherBooks.get(i)))
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

        //if (testBook.equals(otherBook))
        //{
        //    System.out.println("The two books are equal");
        //    assert(true);
        //}
        //else
        //{
        //    System.out.println("The two books are not equal");
        //    assert(false);
        //}

    }

    @Test
    void xmlSerializationDeserializationTest() {
        Book.serializeToXML(books);

        List<Book> otherBooks = Book.deserializeFromXML();

        assertNotNull(otherBooks);

        for (int i = 0; i < otherBooks.size(); i++) {
            if (books.get(i).equals(otherBooks.get(i)))
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

    @Test
    void binarySerializationDeserializationTest() {
        BinaryBook.serializeToBinary(books);

        List<Book> otherBooks = BinaryBook.deserializeFromBinary();

        assertNotNull(otherBooks);

        for (int i = 0; i < otherBooks.size(); i++) {
            if (books.get(i).equals(otherBooks.get(i)))
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
}