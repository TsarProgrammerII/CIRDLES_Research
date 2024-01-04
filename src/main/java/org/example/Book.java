package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Book implements Serializable
{
    private String author;
    private String title;
    private String publishDate;

    public Book() {
        // Default constructor for Jackson serialization/deserialization
    }

    public Book(String author, String title, String publishDate)
    {
        this.author = author;
        this.title = title;
        this.publishDate = publishDate;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getPublishDate()
    {
        return this.publishDate;
    }

    public static void prettyPrinter(Path path, List<Book> books) {
        for (Book book : books) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){ //Buffered Writer to write the strings to the .csv file
                writer.write(book.getAuthor() + "," + book.getTitle() + "," + book.getPublishDate());
                writer.newLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void serializeToCSV(List<Book> books)
    {
        Path path = Paths.get("./Book.csv");
        prettyPrinter(path, books);
    }

    public static List<Book> deserializeFromCSV()
    {
        List<Book> books = new ArrayList<Book>();
        Path path = Paths.get("./Book.csv");
        try {
           // String[] bookAttributes = Files.readAllLines(path).get(0).split(",");
            // return new Book(bookAttributes[0], bookAttributes[1], bookAttributes[2]);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] bookAttributes = line.split(",");
                books.add(new Book(bookAttributes[0], bookAttributes[1], bookAttributes[2]));
            }
            return books;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void serializeToXML(List<Book> books) {
        try {
            ObjectMapper mapper = new XmlMapper();
            Path path = Paths.get("./Books.xml");
            mapper.writeValue(path.toFile(), books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> deserializeFromXML() {
        try {
            ObjectMapper mapper = new XmlMapper();
            Path path = Paths.get("./Books.xml");
            return mapper.readValue(path.toFile(), mapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean equals(Book o)
    {
        if (o == null)
        {
            return false;
        }
        else if (o == this)
        {
            return true;
        }

        return o.getAuthor().equalsIgnoreCase(this.author) && o.getTitle().equalsIgnoreCase(this.title) && o.getPublishDate().equalsIgnoreCase(this.publishDate);
    }
}