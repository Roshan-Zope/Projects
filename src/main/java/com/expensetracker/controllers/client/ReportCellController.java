package com.expensetracker.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportCellController implements Initializable {
    public Label report_cell_amount;
    public Label report_cell_category;
    public Label report_cell_date;
    public Label report_cell_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setReport_cell_amount(String amount) {
        this.report_cell_amount.setText(amount);
    }

    public void setReport_cell_category(String category) {
        this.report_cell_category.setText(category);
    }

    public void setReport_cell_date(String date) {
        this.report_cell_date.setText(date);
    }

    public void setReport_cell_id(String id) {
        this.report_cell_id.setText(id);
    }
}
