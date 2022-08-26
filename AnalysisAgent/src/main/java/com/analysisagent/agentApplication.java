package com.analysisagent;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class agentApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(agentApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 445);
        stage.setTitle("Analysis Agent");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {

        launch();
    }
}
