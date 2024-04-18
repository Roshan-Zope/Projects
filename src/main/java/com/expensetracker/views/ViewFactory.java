package com.expensetracker.views;

import com.expensetracker.controllers.client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    // client view
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane budgetView;
    private AnchorPane reportView;
    private AnchorPane profileView;

    public ViewFactory() {
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getProfileView() {
        if (profileView == null) {
            try {
                profileView = new FXMLLoader(getClass().getResource("/fxmls/client/Profile.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profileView;
    }

    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/fxmls/client/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getBudgetView() {
        if (budgetView == null) {
            try {
                budgetView = new FXMLLoader(getClass().getResource("/fxmls/client/Budget.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return budgetView;
    }

    public AnchorPane getReportView() {
        if (reportView == null) {
            try {
                reportView = new FXMLLoader(getClass().getResource("/fxmls/client/Report.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reportView;
    }

    public void showLoginWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/login.fxml"));
        createStage(fxmlLoader);
    }

    public void showSignupWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/signup.fxml"));
        createStage(fxmlLoader);
    }

    public void showClientWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/client/Client.fxml"));
        ClientController clientController = new ClientController();
        fxmlLoader.setController(clientController);
        createStage(fxmlLoader);
    }

    private void createStage(FXMLLoader fxmlLoader) {
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setResizable(false);
        stage.setTitle("Expense Tracker");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
