package com.example.tms;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AfterLogin {
    Map<String, Object> userInfo = new HashMap<>();

    @FXML
    public Button PayTaxButton;
    @FXML
    public Label UsernameLabel;
    @FXML
    public Label TaxAmountLabel;
    @FXML
    public Label TaxStatus;

    public void setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
        UsernameLabel.setText("Hoşgeldiniz, " + userInfo.get("TCKN").toString());
        TaxAmountLabel.setText(userInfo.get("odenmemisVergiMiktari").toString() + " TL");
        if (userInfo.get("odenmemisVergiMiktari").toString().equals("0")) {
            TaxStatus.setText("ODENMEMIŞ VERGİNİZ BULUNMAMAKTADIR!");
        }
        else {
            TaxStatus.setText("ODENMEMIŞ VERGİNİZ BULUNMAKTADIR!");
        }
    }

    public void openGelirVergisiWindow() throws IOException {
        Main m = new Main();
        m.changeScene("gelir-vergisi.fxml");
    }

    public void openOTVWindow() throws IOException {
        Main m = new Main();
        m.changeScene("otv.fxml");
    }

    public void payTax() throws ExecutionException, InterruptedException {
        Map<String, Object> updates = new HashMap<>();
        updates.put("odenmisVergiMiktari", userInfo.get("odenmemisVergiMiktari").toString());
        updates.put("odenmemisVergiMiktari", "0");
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference docRef = dbFirestore.collection("users").document(userInfo.get("TCKN").toString());
            docRef.update(updates);
            setUserInfo(docRef.get().get().getData());
        } catch (Exception e) {
            System.out.println("An error occured: " + e);
        }
    }
}
