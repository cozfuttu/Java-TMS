package com.example.tms;

import com.example.tms.objects.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AfterLogin {
    User userInfo;
    User[] topThree = new User[3];

    @FXML
    public Button PayTaxButton;
    @FXML
    public Button GetTaxButton;
    @FXML
    public Label UsernameLabel;
    @FXML
    public Label TaxAmountLabel;
    @FXML
    public Label TaxStatus;
    @FXML
    public Label HighestTaxFirst;
    @FXML
    public Label HighestTaxSecond;
    @FXML
    public Label HighestTaxThird;
    @FXML
    public Label PalindromText;
    @FXML
    public Label TaxPaidLabel;
    @FXML
    public TextField TaxField;


    public void setUserInfo(User userInfo, User[] topThree) {
        this.userInfo = userInfo;
        this.topThree = topThree;
        boolean palindromMu = userInfo.palindromMu();
        // Eger kullanicinin tckn'si palindrom ise vergide %5 indirim yapılır.
        int vergiMiktari = palindromMu ? (int) (userInfo.getOdenmemisVergiMiktari() * 0.95) : userInfo.getOdenmemisVergiMiktari();
        UsernameLabel.setText("Hoşgeldiniz, " + userInfo.getTCKN());
        TaxAmountLabel.setText(vergiMiktari + " TL" + (palindromMu ? " -%5" : ""));
        HighestTaxThird.setText("3) " + topThree[2].getTCKN() + " - " + topThree[2].getOdenmemisVergiMiktari());
        HighestTaxSecond.setText("2) " + topThree[1].getTCKN() + " - " + topThree[1].getOdenmemisVergiMiktari());
        HighestTaxFirst.setText("1) " + topThree[0].getTCKN() + " - " + topThree[0].getOdenmemisVergiMiktari());
        PalindromText.setText(palindromMu ? "TEBRİKLER! KİMLİK NUMARANIZ BİR PALİNDROM! (Vergi indirimi %5)" : "Maalesef Kimlik numaranız palindrom degil, %5 indirim alamadiniz.");
        if (Integer.toString(userInfo.getOdenmemisVergiMiktari()).equals("0")) {
            TaxStatus.setText("ODENMEMIŞ VERGİNİZ BULUNMAMAKTADIR!");
            PayTaxButton.setVisible(false);
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

    // Vergiyi Öde tuşuna basınca çalışan fonksiyon
    public void payTax() throws ExecutionException, InterruptedException {
        Map<String, Object> updates = new HashMap<>();
        updates.put("odenmisVergiMiktari", Integer.toString(userInfo.getOdenmemisVergiMiktari()));
        // Vergi odenecegi için vergi miktarı sıfırlanır.
        updates.put("odenmemisVergiMiktari", "0");
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference docRef = dbFirestore.collection("users").document(userInfo.getTCKN());
            docRef.update(updates);
            User user = new User(docRef.get().get().getData().get("TCKN").toString(), docRef.get().get().getData().get("password").toString(), Integer.parseInt(docRef.get().get().getData().get("odenmemisVergiMiktari").toString()), Integer.parseInt(docRef.get().get().getData().get("odenmisVergiMiktari").toString()));
            setUserInfo(user, this.topThree);
        } catch (Exception e) {
            System.out.println("An error occured: " + e);
        }
    }

    // Vergisini öğren tuşuna basınca çalışan fonksiyon
    public void getTax() throws ExecutionException, InterruptedException {
        // Aranan tckn'ye sahip kullanicinin sahip olduğu vergi miktarı döndürülür.
        String tckn = TaxField.getText();
        try {
            FirebaseControls controls = new FirebaseControls();
            LinkedList list = controls.getAllUsers();
            User user = list.linearSearch(tckn);
            int odenmemisVergiMiktari = user.getOdenmemisVergiMiktari();
            TaxPaidLabel.setText(odenmemisVergiMiktari == 0 ? "Bu kullanıcının ödenmemiş vergisi bulunmamaktadır." : "Bu kullanicinin " + odenmemisVergiMiktari + " TL vergisi bulunmaktadır.");
            TaxPaidLabel.setVisible(true);
        }
        catch(Exception e) {
            TaxPaidLabel.setTextFill(Color.color(1, 0, 0));
            TaxPaidLabel.setText("Kullanici bulunamadi.");
            TaxPaidLabel.setVisible(true);
        }
    }
}
