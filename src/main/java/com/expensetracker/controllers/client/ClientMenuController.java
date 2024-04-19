package com.expensetracker.controllers.client;

import com.expensetracker.models.AlertMessage;
import com.expensetracker.models.Model;
import com.expensetracker.models.services.FeedbackService;
import com.expensetracker.views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

// controller for ClientMenu.fxml
public class ClientMenuController implements Initializable  {
    public Button dashboard_button;
    public Button profile_button;
    public Button logout_button;
    public Button report_button;
    public Button budget_button;
    public Button expense_report_button;
    private final FeedbackService feedbackService = new FeedbackService();

    // initialize the fxml component
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        dashboard_button.setOnAction(event -> onDashboard());
        budget_button.setOnAction(event -> onBudget());
        expense_report_button.setOnAction(event -> onReport());
        profile_button.setOnAction(event -> onProfile());
        logout_button.setOnAction(event -> onLogout());
        report_button.setOnAction(event -> onFeedback());
    }

    private void onFeedback() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Feedback/Report");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your feedback/report any problem here: ");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> feedbackService.save(ProfileController.getCurrUser().getId(), input));
        AlertMessage.throwAlert("INFORMATION", "Information Dialog", "Thank you for your feedback");
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onBudget() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.BUDGET);
    }

    private void onReport() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.REPORT);
    }

    private void onProfile() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFILE);
    }

    private void onLogout() {
        ProfileController.setCurrUser(null);
        Stage stage = (Stage) report_button.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
