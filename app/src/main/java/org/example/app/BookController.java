package org.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookController {

    @FXML
    protected void onAddBookButtonClick() {
        try {
            Stage addBookStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(BookApplication.class.getResource("AddBook.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            addBookStage.setTitle("Bookshelf Application");
            addBookStage.setScene(scene);
            addBookStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSerializeDeserializeBookButtonClick() {
        try {
            Stage addBookStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(BookApplication.class.getResource("SerializeDeserializeBook.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            addBookStage.setTitle("Bookshelf Application");
            addBookStage.setScene(scene);
            addBookStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}