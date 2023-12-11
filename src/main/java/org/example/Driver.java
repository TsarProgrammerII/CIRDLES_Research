package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Driver {
    public static void main(String[] args) {

        Book book = new Book("Edvard Radzinksky", "The Last Tsar", "1992");

        Book.serialize(book);

        Book novel = Book.deserialize();

        if (book.equals(novel))
        {
            System.out.println("The two books are equal");
        }
        else
        {
            System.out.println("The two books are not equal");
        }

    }
}
