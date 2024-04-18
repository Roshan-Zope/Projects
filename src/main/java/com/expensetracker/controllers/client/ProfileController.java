package com.expensetracker.controllers.client;

import com.expensetracker.controllers.LoginController;
import com.expensetracker.models.AlertMessage;
import com.expensetracker.models.Model;
import com.expensetracker.models.entities.User;
import com.expensetracker.models.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public Label userid_label;
    public Label username_label;
    public Label email_label;
    public TextField oldPassword_textField;
    public TextField newPassword_textField;
    public Button change_password_send_otp_button;
    public TextField change_password_otp_textField;
    public Button change_password_button;
    public TextField delete_account_password_textField;
    public Button delete_account_send_otp_button;
    public TextField delete_account_otp_text_field;
    public Button delete_account_button;
    private static User currUser = new User();
    private final UserService userService = new UserService();

    public static void setCurrUser(User currUser) {
        ProfileController.currUser = currUser;
    }

    public static User getCurrUser() {
        return currUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        change_password_send_otp_button.setOnAction(event -> onSendOtp());
        change_password_button.setOnAction(event -> onChangePassword());
        delete_account_send_otp_button.setOnAction(event -> onSendOtp());
        delete_account_button.setOnAction(event -> onDeleteAccount());
        displayUserProfile();
    }

    private void onSendOtp() {
        AlertMessage.throwAlert("INFORMATION", "Information Dialog", "OTP is send successfully on email address: sampleemail@gmail.com");
    }

    private void onChangePassword() {
        if (validateUser(oldPassword_textField.getText())) {
            userService.update(newPassword_textField.getText());
            AlertMessage.throwAlert("INFORMATION", "Information Dialog", "Password is updated successfully");
        } else {
            AlertMessage.throwAlert("ERROR", "Error Dialog", "Check your details");
        }
    }

    private boolean validateUser(String password) {
        return currUser.getPasskey().equals(password);
    }

    private boolean confirmDelete() {
        return AlertMessage.throwAlert("CONFIRMATION", "Confirmation", "Do you want to delete account! \nWARNING: Your all data will be lost");
    }

    private void onDeleteAccount() {
        if (validateUser(delete_account_password_textField.getText())) {
            if (confirmDelete()) {
                userService.delete(ProfileController.getCurrUser().getId());
                AlertMessage.throwAlert("INFORMATION", "INFORMATION Dialog", "Account removed successfully");
                delete_account_password_textField.setText("");
                ProfileController.setCurrUser(null);
                Stage stage = (Stage) delete_account_button.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showLoginWindow();
            }
        } else {
            AlertMessage.throwAlert("ERROR", "Error Dialog", "Check your details");
        }
    }

    public void displayUserProfile() {
        userid_label.setText(currUser.getId().toString());
        username_label.setText(currUser.getUsername());
        email_label.setText(currUser.getEmail());
    }
}
