package com.example.tms;

import com.example.tms.objects.Vergi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GelirVergisi extends Vergi {

    @FXML
    public TextField TaxField;
    @FXML
    public Label TaxAmountLabel;
    @FXML
    public Button CalculateButton;

    public void initialize() {
        TaxField.setText("1000");
        TaxAmountLabel.setText("0 TL");
    }

    @Override
    public void calculate() {
        int gelir = Integer.parseInt(TaxField.getText());
        if (gelir < 32000) {
            setVergi(gelir * 15 / 100);
        }
        else if (gelir < 70000) {
            setVergi(4800 + ((gelir - 32000) * 20 / 100));
        }
        else if (gelir < 170000) {
            setVergi(12400 + ((gelir - 70000) * 27 / 100));
        }
        else if (gelir < 880000) {
            setVergi(39400 + ((gelir - 170000) * 35 / 100));
        }
        else {
            setVergi(287900 + ((gelir - 880000) * 40 / 100));
        }
        TaxAmountLabel.setText(getVergi() + " TL");
    }
}
