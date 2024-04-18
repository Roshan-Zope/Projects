package com.expensetracker.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BudgetCellController implements Initializable {
    public Label budget_cell_amount;
    public Label budget_cell_category;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setBudget_cell_amount(String amount) {
        this.budget_cell_amount.setText(amount);
    }

    public void setBudget_cell_category(String category) {
        this.budget_cell_category.setText(category);
    }
}
