package org.example.comp288lab05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/comp288lab05/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);

        stage.setTitle("Database Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}