package com.example.tms;

import com.example.tms.objects.Vergi;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OTV extends Vergi {
    Map<String, Object> userInfo = new HashMap<>();

    @FXML
    public Label TaxAmountLabel;
    @FXML
    public Button CalculateButton;
    @FXML
    public TextField PriceField;
    @FXML
    public ComboBox CapacityComboBox;

    public void initialize() {
        CapacityComboBox.setItems(FXCollections.observableArrayList(
                "1600 cm³ veya daha az",
                "1601 cm³ - 1800 cm³",
                "1801 cm³ - 2000 cm³",
                "2001 cm³ - 2500 cm³",
                "2501 cm³ veya daha çok"
        ));
        TaxAmountLabel.setText("0 TL");
    }

    @Override
    public void calculate() {
        String hacim = CapacityComboBox.getValue().toString();
        int fiyat = Integer.parseInt(PriceField.getText());
        int otvYuzde = 0;
        System.out.println(fiyat + hacim);
        switch(hacim){
            case "1600 cm³ veya daha az":
                otvYuzde = 45;
                break;
            case "1601 cm³ - 1800 cm³":
            case "1801 cm³ - 2000 cm³":
                otvYuzde = 130;
                break;
            case "2001 cm³ - 2500 cm³":
            case "2501 cm³ veya daha çok":
                otvYuzde = 220;
                break;
        }
        int vergisizFiyat = (100 * fiyat) / (100 + otvYuzde);
        setVergi(fiyat - vergisizFiyat);
        TaxAmountLabel.setText(getVergi() + " TL");
    }
}
