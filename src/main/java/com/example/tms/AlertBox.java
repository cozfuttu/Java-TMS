package com.example.tms;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class AlertBox {

    public static void showAlertBox(String title, String message) {
        Stage window = new Stage();

        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        Button copyButton = new Button("Copy");
        copyButton.setOnAction(e -> clipboard.setContents(new StringSelection(message.substring(10)), null));

        VBox Vlayout = new VBox(10);
        HBox Hlayout = new HBox(20);
        Hlayout.getChildren().addAll(copyButton, closeButton);
        Hlayout.setAlignment(Pos.CENTER);
        Vlayout.getChildren().addAll(label, Hlayout);
        Vlayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(Vlayout);
        window.setScene(scene);
        window.show();
    }
}
