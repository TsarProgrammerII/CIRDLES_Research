package org.example;

import java.io.*;
import java.util.List;

public class BinaryBook {

    public static void serializeToBinary(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./Book.txt"))) {
            oos.writeObject(books);
            System.out.println("Serialization completed. Data saved to: Book.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> deserializeFromBinary() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./Book.txt"))) {
            return (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
