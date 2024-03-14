module org.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires core;


    opens org.example.app to javafx.fxml;
    exports org.example.app;
}