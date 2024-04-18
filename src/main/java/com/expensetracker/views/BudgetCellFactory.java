package com.expensetracker.views;

import com.expensetracker.controllers.client.BudgetCellController;
import com.expensetracker.models.entities.Category;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class BudgetCellFactory implements Callback<ListView<Category>, ListCell<Category>> {
    @Override
    public ListCell<Category> call(ListView<Category> budgetListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (empty || category == null) {
                    setText(null);
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/client/BudgetCell.fxml"));
                        Node customCell = fxmlLoader.load();
                        BudgetCellController budgetCellController = fxmlLoader.getController();
                        budgetCellController.setBudget_cell_category(category.getCategory());
                        budgetCellController.setBudget_cell_amount("₹"+category.getAmount());
                        setGraphic(customCell);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
