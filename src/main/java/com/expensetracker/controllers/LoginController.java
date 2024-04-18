package com.expensetracker.controllers;

import com.expensetracker.controllers.client.DashboardController;
import com.expensetracker.controllers.client.ProfileController;
import com.expensetracker.models.AlertMessage;
import com.expensetracker.models.Model;
import com.expensetracker.models.entities.User;
import com.expensetracker.models.services.UserService;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

// controller for LoginController.fxml
public class LoginController implements Initializable {
    public Label username_label;
    public TextField username_field;
    public Label password_label;
    public TextField password_field;
    public Button login_button;
    public Label error_label;
    public Button signup_button;
    private final User user = new User();
    private final UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_button.setOnAction(event -> onLogin());
        signup_button.setOnAction(event -> onSignup());
    }

    private void onLogin() {
        if (validateInput() && validateUser()) {
            Stage stage = (Stage) error_label.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showClientWindow();
        } else {
            AlertMessage.throwAlert("ERROR", "Error Dialog", "Invalid Input. Please Check Your Details");
        }
    }

    private void onSignup() {
        Stage stage = (Stage) error_label.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignupWindow();
    }

    private boolean validateInput() {
        return !username_field.getText().isEmpty() &&
                !password_field.getText().isEmpty();
    }

    private boolean validateUser() {
        Document document = userService.getUser(username_field.getText(), password_field.getText());
        if (document != null) {
            user.setId(document.getObjectId("_id"));
            user.setUsername(document.getString("username"));
            user.setEmail(document.getString("email"));
            user.setPasskey(document.getString("passkey"));
            ProfileController.setCurrUser(user);
            return true;
        }
        return false;
    }
}
