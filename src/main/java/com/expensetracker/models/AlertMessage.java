package com.expensetracker.models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertMessage {

    public static boolean throwAlert(String type, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
