package com.expensetracker.controllers.client;

import com.expensetracker.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

// controller for ClientController.fxml
public class ClientController implements Initializable {
    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case BUDGET -> client_parent.setCenter(Model.getInstance().getViewFactory().getBudgetView());
                case REPORT -> client_parent.setCenter(Model.getInstance().getViewFactory().getReportView());
                case PROFILE -> client_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
