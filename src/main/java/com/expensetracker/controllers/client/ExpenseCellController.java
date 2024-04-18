package com.expensetracker.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ExpenseCellController implements Initializable {
    public Label expense_amount;
    public Label expense_category;
    public Label expense_date;
    public Label expenseid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setExpense_amount(String amount) {
        this.expense_amount.setText(amount);
    }

    public void setExpense_category(String category) {
        this.expense_category.setText(category);
    }

    public void setExpense_date(String date) {
        this.expense_date.setText(date);
    }

    public void setExpenseid(String id) {
        this.expenseid.setText(id);
    }
}
