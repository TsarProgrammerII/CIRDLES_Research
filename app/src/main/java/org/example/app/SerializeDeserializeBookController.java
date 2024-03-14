package org.example.app;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.example.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

public class SerializeDeserializeBookController implements Initializable {

    @FXML
    private TextField filePathText; //Holds the custom File Path

    @FXML
    public ListView<Book> listView; //Displays the books

    Book book1 = new Book("W. Fitzhugh Brundage", "A New History of the American South", "2023");
    Book book2 = new Book("Mary Beard", "Laughter In Ancient Rome", "2014");
    Book book3 = new Book("Ernest Hemingway", "For Whom the Bell Tolls", "1940");
    ObservableList<Book> books = FXCollections.observableArrayList(book1,book2,book3);
    //Temporary arraylist for testing until Database is up
    @Override
    public void initialize(URL rg0, ResourceBundle arg1) {
        listView.setItems(books);
    }

    public void onSerializeButtonClick() {
        String path = filePathText.getText();
        if (path.isEmpty()) {
            path = "./Books.csv";
        }
        ObservableList<Book> serializableBooks;
        serializableBooks = listView.getSelectionModel().getSelectedItems();

        Set<Book> serializableBookTree = new TreeSet<>();

        for (Book book: serializableBooks) {
            serializableBookTree.add(book);
            books.remove(book);
        }

        Book.serializeToCSV(Paths.get(path), serializableBookTree);
    }

    public void onDeserializeButtonClick() {
        String path = filePathText.getText();
        if (path.isEmpty()) {
            path = "./Books.csv";
        }

        Set<Book> deserializedBooks = Book.deserializeFromCSV(Paths.get(path));

        books.addAll(deserializedBooks);
    }

}
