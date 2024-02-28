package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Book implements Comparable, Serializable {
    private String author;
    private String title;
    private String publishDate;

    public Book() {
        // Default constructor for Jackson serialization/deserialization
    }

    public Book(String author, String title, String publishDate) {
        this.author = author;
        this.title = title;
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public static void prettyPrinter(Path path, TreeSet<Book> books) {
        for (Book book : books) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) { //Buffered Writer to write the strings to the .csv file
                writer.write(book.getAuthor() + "," + book.getTitle() + "," + book.getPublishDate());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void serializeToCSV(Path path, TreeSet<Book> books) {
        prettyPrinter(path, books);
    }

    public static TreeSet<Book> deserializeFromCSV(Path path) {
        TreeSet<Book> books = new TreeSet<Book>();
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] bookAttributes = line.split(",");
                //Add a check to make sure Book Attributes is long enough
                books.add(new Book(bookAttributes[0], bookAttributes[1], bookAttributes[2]));
            }
            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void serializeToXML(Path path, TreeSet<Book> books) {
        try {
            ObjectMapper mapper = new XmlMapper();
            mapper.writeValue(path.toFile(), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TreeSet<Book> deserializeFromXML(Path path) {
        try {
            ObjectMapper mapper = new XmlMapper();
            return mapper.readValue(path.toFile(), mapper.getTypeFactory().constructCollectionType(TreeSet.class, Book.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int compareTo(Object o) { //0 = equal, -1 = Null, 1= unequal
        int comparison = 1;
        if (o == null) {
            comparison = -1;
        } else if (o == this) {
            comparison = 0;
        }
        else if (((Book) o).getAuthor().equalsIgnoreCase(this.author)) {
            if (((Book) o).getTitle().equalsIgnoreCase(this.title)) {
                if (((Book) o).getPublishDate().equalsIgnoreCase(this.publishDate)) {
                    comparison = 0;
                }
            }
        }
        return comparison;
    }

    public static boolean compareBookSets(TreeSet<Book> set1, TreeSet<Book> set2) {
        boolean result = true;
        if (set1.size() != set2.size()){
            result = false;
        }

        // Iterate over the sets and compare each element
        Iterator<Book> iter1 = set1.iterator();
        Iterator<Book> iter2 = set2.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            Book book1 = iter1.next();
            Book book2 = iter2.next();
            if (book1.compareTo(book2) != 0) {
                result = false; // If any elements are not equal, sets are not equal
            }
        }
        return result; // All elements are equal
    }
}
