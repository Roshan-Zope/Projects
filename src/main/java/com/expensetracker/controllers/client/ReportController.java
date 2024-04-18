package com.expensetracker.controllers.client;

import com.expensetracker.models.Repository.ExpenseRepository;
import com.expensetracker.models.entities.Expense;
import com.expensetracker.models.entities.Report;
import com.expensetracker.models.services.ReportService;
import com.expensetracker.views.ReportCellFactory;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    public DatePicker report_initialDate;
    public DatePicker report_finalDate;
    public Button generate_report_button;
    public Label reportid_label;
    public Label userid_label;
    public Label reportDate_label;
    public Label report_range_label;
    public Text currDate;
    public Label total_expenses_label;
    public ListView<Expense> report_listView;
    private final ReportService reportService = new ReportService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generate_report_button.setOnAction(event -> onGenerateReport());
        report_listView.setCellFactory(new ReportCellFactory());
    }

    private void onGenerateReport() {
        Report report = setReportDetails();
        reportService.generateReport(report);
        displayReport(report);
    }

    private void displayReport(Report report) {
        reportid_label.setText(report.getId());
        userid_label.setText(report.getUser().getId().toString());
        reportDate_label.setText(report.getDate().toString());
        report_range_label.setText(report.getIntiDate().toString()+" to "+report.getFinalDate().toString());
        currDate.setText(LocalDate.now().toString());
        total_expenses_label.setText(report.getTotalAmount().toString());
        report_listView.setItems(FXCollections.observableList(report.getExpenses()));
    }

    private Report setReportDetails() {
        Report report = new Report();
        report.setUser(ProfileController.getCurrUser());
        report.setIntiDate(report_initialDate.getValue());
        report.setFinalDate(report_finalDate.getValue());
        report.setDate(LocalDate.now());
        report.setTotalAmount(
                new ExpenseRepository().getTotalAmount(
                        ProfileController.getCurrUser().getId(),
                        report_initialDate.getValue(),
                        report_finalDate.getValue()
                )
        );
        report.setExpenses(new ExpenseRepository().getLatestTransactions(
                ProfileController.getCurrUser().getId(),
                report_initialDate.getValue(),
                report_finalDate.getValue()
        ));
        return report;
    }
}
