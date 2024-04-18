package com.expensetracker.views;

import com.expensetracker.controllers.client.ExpenseCellController;
import com.expensetracker.models.entities.Expense;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class TransactionCellFactory implements Callback<ListView<Expense>, ListCell<Expense>> {
    @Override
    public ListCell<Expense> call(ListView<Expense> expenseListView) {
        return new ListCell<Expense>() {
            @Override
            protected void updateItem(Expense expense, boolean b) {
                super.updateItem(expense, b);
                if (b || expense == null) {
                    setText(null);
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/client/ExpenseCell.fxml"));
                        Node customCell = fxmlLoader.load();
                        ExpenseCellController expenseCellController = fxmlLoader.getController();
                        // Customize the layout to display multiple details
                        expenseCellController.setExpenseid(expense.getId().toString());
                        expenseCellController.setExpense_date(expense.getDate().toString());
                        expenseCellController.setExpense_category(expense.getCategory());
                        expenseCellController.setExpense_amount("₹"+expense.getAmount().toString());
                        setGraphic(customCell);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
