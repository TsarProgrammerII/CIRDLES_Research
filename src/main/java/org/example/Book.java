package org.example;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
public class Book implements Serializable
{
    private String author;
    private String title;
    private String publishDate;

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

    public static void serialize(Book book)
    {
        Path path = Paths.get("./Book.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){ //Buffered Writer to write the strings to the .csv file
            writer.write(book.getAuthor() + "," + book.getTitle() + "," + book.getPublishDate());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Book deserialize()
    {
        Path path = Paths.get("./Book.csv");
        try {
            String[] bookAttributes = Files.readAllLines(path).get(0).split(",");
            return new Book(bookAttributes[0], bookAttributes[1], bookAttributes[2]);
        }
        catch (IOException e)
        {
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