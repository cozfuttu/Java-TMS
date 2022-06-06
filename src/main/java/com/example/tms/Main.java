package com.example.tms;

import com.example.tms.objects.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class Main extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        // Giriş yapma ekranı
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
        stg.getScene().setRoot(pane);
    }

    public void changeSceneToUserScreen(User userInfo, User[] topThree) throws IOException {
        // Giriş yapıldıktan sonra kullanıcı ekranını açar.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user-screen.fxml"));
        Parent tableViewParent = loader.load();

        AfterLogin afterLogin = loader.getController();
        afterLogin.setUserInfo(userInfo, topThree);
        stg.setTitle("User Screen");
        stg.getScene().setRoot(tableViewParent);
    }

    public static void main(String[] args) {
        launch();
    }
}