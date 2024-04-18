package com.expensetracker.controllers.client;

import com.expensetracker.models.AlertMessage;
import com.expensetracker.models.entities.Expense;
import com.expensetracker.models.services.ExpenseService;
import com.expensetracker.views.TransactionCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

// controller for Dashboard.fxml
public class DashboardController implements Initializable {
    public Text username;
    public Label login_date;
    public TextField category_expenseByCategory;
    public DatePicker initDate_expenseByCategory;
    public DatePicker finalDate_expenseByCategory;
    public TextField category_addExpense;
    public TextField amount_addExpense;
    public DatePicker date_addExpense;
    public DatePicker initDate_summary;
    public DatePicker finalDate_summary;
    public Label total_expense;
    public Button view_button;
    public Button add_expense_button;
    public Button show_summary_button;
    public ListView<Expense> expense_listview;
    public ListView<Expense> latest_transaction_listview;
    public Button show_transaction_button;
    private final Expense expense = new Expense();
    private final ExpenseService expenseService = new ExpenseService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText("HI, " + ProfileController.getCurrUser().getUsername().toUpperCase());
        login_date.setText(LocalDate.now().toString());
        add_expense_button.setOnAction(event -> onAddExpense());
        show_summary_button.setOnAction(event -> onShowSummary());
        view_button.setOnAction(event -> onViewExpense());
        show_transaction_button.setOnAction(event -> showLatestTransaction());
        expense_listview.setCellFactory(new TransactionCellFactory());
        latest_transaction_listview.setCellFactory(new TransactionCellFactory());
    }

    private void onViewExpense() {
        ObservableList<Expense> expenses = expenseService.getExpenseByCategory(
                ProfileController.getCurrUser().getId(),
                category_expenseByCategory.getText(),
                initDate_expenseByCategory.getValue(),
                finalDate_expenseByCategory.getValue()
        );
        expense_listview.setItems(expenses);
    }

    public void showLatestTransaction() {
        ObservableList<Expense> expenses = expenseService.getLatestTransaction(
                ProfileController.getCurrUser().getId(),
                LocalDate.now().minusMonths(1),
                LocalDate.now()
        );
        latest_transaction_listview.setItems(expenses);
    }

    private void onAddExpense() {
        setExpense();
        expense.setId(expenseService.createExpense(expense));
        AlertMessage.throwAlert("INFORMATION", "Information Dialog", "Expense added with ExpenseId: " + expense.getId());
    }

    private void onShowSummary() {
        Double amount = expenseService.getTotalAmount(
                ProfileController.getCurrUser().getId(),
                initDate_summary.getValue(),
                finalDate_summary.getValue()
        );
        if (amount >= 0) total_expense.setText(amount.toString());
        else AlertMessage.throwAlert("INFORMATION", "Information Dialog", "No transactions found ");
    }

    private void setExpense() {
        expense.setCategory(category_addExpense.getText());
        expense.setAmount(Double.parseDouble(amount_addExpense.getText()));
        expense.setDate(date_addExpense.getValue());
        expense.setUser(ProfileController.getCurrUser());
    }
}
