package com.example.tms;

import com.example.tms.exceptions.UserExistsException;
import com.example.tms.exceptions.UserNotFoundException;
import com.example.tms.exceptions.WrongPasswordException;
import com.example.tms.objects.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.api.core.ApiFuture;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseControls {

    public void initialize() throws FileNotFoundException {
        try {
            InputStream serviceAccount = new FileInputStream("src/main/java/com/example/tms/key.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) throws ExecutionException, InterruptedException, UserExistsException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(user.getTCKN());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if(document.exists()) {
            // Throwing a custom error.
            System.out.println("User exists!");
            throw new UserExistsException();
        }
        else {
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("TCKN", user.getTCKN());
            userInfo.put("password", user.getSifre());
            userInfo.put("odenmemisVergiMiktari", user.getOdenmemisVergiMiktari());
            userInfo.put("odenmisVergiMiktari", user.getOdenmisVergiMiktari());
            System.out.println("Adding user to database...");
            docRef.set(userInfo);
            System.out.println("User added to database!");
        }
    }

    public Map<String, Object> getUserByTCKN(String TCKN) throws ExecutionException, InterruptedException, UserNotFoundException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("users").document(TCKN);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.getData();
        }
        else {
            System.out.println("User not found!");
            // Throwing a custom error.
            throw new UserNotFoundException();
        }
    }

    @FXML
    private Button LoginButton;
    @FXML
    private Button GetPasswordButton;
    @FXML
    private Label ErrorLabel;
    @FXML
    private TextField TCKNField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private ImageView LockImage;

    public void userLogIn() {
        ErrorLabel.setVisible(false);
        String TCKN = TCKNField.getText();
        try {
            Map<String, Object> userInfo = getUserByTCKN(TCKN);
            String sifre = PasswordField.getText();
            if (sifre.equals(userInfo.get("password"))) {
                Main m = new Main();
                m.changeSceneToUserScreen(userInfo);
            }
            else {
                throw new WrongPasswordException();
            }
        } catch (UserNotFoundException | ExecutionException | InterruptedException | WrongPasswordException | IOException e) {
            if(e.getClass() == UserNotFoundException.class || e.getClass() == WrongPasswordException.class) {
                ErrorLabel.setText(e.toString());
                ErrorLabel.setVisible(true);
            }
            else {
                ErrorLabel.setText("Bilinmeyen bir hata olustu.");
                ErrorLabel.setVisible(true);
                System.out.println(e);
            }
        }
    }

    public void userGetPassword() throws UserExistsException, ExecutionException, InterruptedException {
        ErrorLabel.setVisible(false);
        String TCKN = TCKNField.getText();
        try {
            if (TCKN.length() != 11) throw new IOException();
            Map<String, Object> userInfo = getUserByTCKN(TCKN);
            String sifre = userInfo.get("password").toString();
            AlertBox.showAlertBox("Başarılı!", "Sifreniz: " + sifre);
        } catch (Exception e) {
            if (e.getClass() == UserNotFoundException.class) {
                final int PASSWORD_LENGTH = 6;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < PASSWORD_LENGTH; i++) {
                    int randomInt = (int) (Math.random() * (122 - 48)) + 48;
                    while ((randomInt > 57 && randomInt < 65) || (randomInt > 90 && randomInt < 97)) {
                        randomInt = (int) (Math.random() * (122 - 48)) + 48;
                    }
                    sb.append((char) randomInt);
                }
                int odenmemisVergiMiktari = (int) (Math.random() * 10000);
                String sifre = sb.toString();
                User newUser = new User(TCKN, sifre, odenmemisVergiMiktari, 0);
                addUser(newUser);
                AlertBox.showAlertBox("Başarılı!", "Sifreniz: " + sifre);
            }
            else if (e.getClass() == IOException.class) {
                System.out.println(e);
                ErrorLabel.setVisible(true);
                ErrorLabel.setText("Lütfen geçerli bir TCKN giriniz.");
            }
            else{
                System.out.println(e);
                ErrorLabel.setVisible(true);
                ErrorLabel.setText("Hata oluştu.");
            }
        }
    }

    // From here, the controls are for the user screen.


    public void openGelirVergisiWindow() throws IOException {
        Main m = new Main();
        m.changeScene("gelir-vergisi.fxml");
    }

    public void openOTVWindow() throws IOException {
        Main m = new Main();
        m.changeScene("otv.fxml");
    }

    public void payTax() {

    }

}
