module com.example.tms {
    requires javafx.controls;
    requires javafx.fxml;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires com.google.auth;
    requires google.cloud.core;
    requires java.desktop;


    opens com.example.tms to javafx.fxml;
    exports com.example.tms;
}