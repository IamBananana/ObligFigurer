package com.example.obligfigur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        //test
    }

    public static void main(String[] args) {
        launch();
    }
}