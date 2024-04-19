package com.expensetracker.controllers;

import com.expensetracker.models.AlertMessage;
import com.expensetracker.models.Model;
import com.expensetracker.models.entities.User;
import com.expensetracker.models.services.UserService;
import com.expensetracker.utilities.OTPGenerator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    public TextField signup_username;
    public TextField signup_password;
    public TextField signup_email;
    public Button send_otp_button;
    public TextField otp_field;
    public Button signup_button;
    public Button login_button;
    private final UserService userService = new UserService();
    private final User user = new User();
    private final EmailController emailController = new EmailController();
    private String otp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signup_button.setOnAction(event -> onSignup());
        login_button.setOnAction(event -> onLogin());
        send_otp_button.setOnAction(event -> onSendOtp());
    }

    private void onSignup() {
        if (validateInput()) {
            if (validateOtp()) {
                setUserDetails();
                user.setId(userService.createUser(user));
                AlertMessage.throwAlert("INFORMATION", "Information Dialog", "User Created with UserId: " + user.getId());
                Stage stage = (Stage) otp_field.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showLoginWindow();
            } else {
                AlertMessage.throwAlert("ERROR", "Error Dialog", "Invalid otp. Please tyr again");
            }
        } else {
            AlertMessage.throwAlert("ERROR", "Error Dialog", "Invalid Input. Please Check Your Details");
        }
    }

    private boolean validateOtp() {
        return otp_field.getText().equals(otp);
    }

    private void onLogin() {
        Stage stage = (Stage) otp_field.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    private void onSendOtp() {
        otp = OTPGenerator.generateOTP();
        emailController.sendEmail(signup_email.getText(),
                "OTP Verification",
                "The OTP for your expense tracker account verification is: " + otp);
        AlertMessage.throwAlert("INFORMATION", "Information Dialog", "OTP is sent successfully on email address: " + signup_email.getText());
    }

    private boolean validateInput() {
        return !signup_username.getText().isEmpty() &&
                !signup_password.getText().isEmpty() &&
                !signup_email.getText().isEmpty();
    }

    private void setUserDetails() {
        user.setUsername(signup_username.getText());
        user.setPasskey(signup_password.getText());
        user.setEmail(signup_email.getText());
    }
}
