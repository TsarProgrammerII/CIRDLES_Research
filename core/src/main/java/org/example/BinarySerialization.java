package org.example;

import java.io.*;
import java.util.*;

public class BinarySerialization {

    public static void serializeToBinary(String path, Set<Object> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(books);
            System.out.println("Serialization completed. Data saved to: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Set<Object> deserializeFromBinary(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (TreeSet<Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
