package org.example.app;
import javafx.scene.control.Label;
import  org.example.Book;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private Label addBookLabel;

    @FXML
    private TextField authorField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField publishDateField;

    @FXML
    protected void onAddButtonClick() {
        String author = authorField.getText();
        String title = titleField.getText();
        String publishDate = publishDateField.getText();

        Book book = new Book(author, title, publishDate);

        //Display a message to the user indicating successful creation
        System.out.println("Book created successfully: " + "\n" + book.getAuthor() + "\n" + book.getTitle() + "\n" + book.getPublishDate());
        addBookLabel.setText("Book Successfully Added!");
    }
}
