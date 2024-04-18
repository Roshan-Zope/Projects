package com.expensetracker.views;

import com.expensetracker.controllers.client.ReportCellController;
import com.expensetracker.models.entities.Expense;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ReportCellFactory implements Callback<ListView<Expense>, ListCell<Expense>> {
    @Override
    public ListCell<Expense> call(ListView<Expense> reportListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Expense expense, boolean empty) {
                super.updateItem(expense, empty);
                if (empty) {
                    setText(null);
                } else {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/client/ReportCell.fxml"));
                        Node customCell = fxmlLoader.load();
                        ReportCellController reportCellController = fxmlLoader.getController();
                        reportCellController.setReport_cell_amount("₹"+expense.getAmount().toString());
                        reportCellController.setReport_cell_category(expense.getCategory());
                        reportCellController.setReport_cell_date(expense.getDate().toString());
                        reportCellController.setReport_cell_id(expense.getId().toString());
                        setGraphic(customCell);
                    } catch (Exception e)  {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
