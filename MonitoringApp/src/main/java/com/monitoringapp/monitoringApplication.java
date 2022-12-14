package com.monitoringapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class monitoringApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(monitoringApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 825);
        stage.setTitle("Monitoring");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}
