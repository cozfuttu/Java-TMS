package com.example.tms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class Main extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setTitle("Tax");
        stg.getScene().setRoot(pane);
    }

    public void changeSceneToUserScreen(Map<String, Object> userInfo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user-screen.fxml"));
        Parent tableViewParent = loader.load();

        AfterLogin afterLogin = loader.getController();
        afterLogin.setUserInfo(userInfo);
        stg.setTitle("User Screen");
        stg.getScene().setRoot(tableViewParent);
    }

    public static void main(String[] args) {
        launch();
    }
}