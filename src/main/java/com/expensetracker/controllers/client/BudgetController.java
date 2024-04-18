package com.expensetracker.controllers.client;

import com.expensetracker.models.AlertMessage;
import com.expensetracker.models.entities.Budget;
import com.expensetracker.models.entities.Category;
import com.expensetracker.models.services.BudgetService;
import com.expensetracker.views.BudgetCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.bson.types.ObjectId;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BudgetController implements Initializable {
    public DatePicker set_budget_initDate;
    public TextField set_budget_category;
    public TextField set_budget_amount;
    public Button set_budget_add_to_budget;
    public Button set_budget_button;
    public TextField update_budget_budgetId;
    public TextField update_budget_new_Category;
    public TextField update_budget_amount;
    public Button update_budget_add_to_budget;
    public TextField update_budget_existing_category;
    public TextField update_budget_new_amount;
    public Button update_budget_add_to_budget2;
    public Button update_budget_button;
    public Label view_budget_budgetid_label;
    public Label view_budget_initDate_label;
    public Label view_budget_finalDate_label;
    public Label view_budget_userid_label;
    public TextField view_budget_budgetid;
    public Button view_budget;
    public ListView<Category> view_budget_listView;
    private List<Category> categories = new ArrayList<>();
    private final BudgetService budgetService = new BudgetService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update_budget_button.setOnAction(event -> onUpdateBudget());
        set_budget_button.setOnAction(event -> onSetBudget());
        set_budget_add_to_budget.setOnAction(event -> onAddToBudget(set_budget_category, set_budget_amount));
        update_budget_add_to_budget.setOnAction(event -> onAddToBudget(update_budget_new_Category, update_budget_amount));
        update_budget_add_to_budget2.setOnAction(event -> onAddToBudget(update_budget_existing_category, update_budget_new_amount));
        view_budget.setOnAction(event -> onViewBudget());
        view_budget_listView.setCellFactory(new BudgetCellFactory());
    }

    private void onViewBudget() {
        Budget budget = budgetService.getBudget(view_budget_budgetid.getText());
        view_budget_budgetid_label.setText(budget.getId());
        view_budget_initDate_label.setText(budget.getInitDate().toString());
        view_budget_finalDate_label.setText(budget.getFinalDate().toString());
        view_budget_userid_label.setText(budget.getUser().getId().toString());
        view_budget_listView.setItems(convertListToObservableList(budget.getCategories()));
    }

    private ObservableList<Category> convertListToObservableList(List<Category> categories) {
        return FXCollections.observableArrayList(categories);
    }

    private void onAddToBudget(TextField _category, TextField amount) {
        Category category = new Category();
        category.setCategory(_category.getText());
        category.setAmount(amount.getText());
        categories.add(category);
        _category.setText("");
        amount.setText("");
    }

    private void onUpdateBudget() {
        if (budgetService.update(update_budget_budgetId.getText(), categories)) {
            AlertMessage.throwAlert("INFORMATION", "Information Dialog", "Budget Updated Successfully");
            categories.clear();
        } else {
            AlertMessage.throwAlert("ERROR", "Error Dialog", "Budget not set successfully");
        }
    }

    private void onSetBudget() {
        if (validateInput()) {
            Budget budget = new Budget();
            setBudgetDetails(budget);
            ObjectId id = budgetService.setBudget(budget);
            if (id != null) {
                AlertMessage.throwAlert("INFORMATION", "Information Dialog", "Budget Set with BudgetId: " + budget.getId());
                categories.clear();
            }
            else {
                AlertMessage.throwAlert("ERROR", "Error Dialog", "Budget not set successfully");
            }
        } else {
            AlertMessage.throwAlert("ERROR", "Error Dialog", "Invalid Input. Please Check Your Details");
        }
    }

    private void setBudgetDetails(Budget budget) {
        budget.setUser(ProfileController.getCurrUser());
        budget.setInitDate(set_budget_initDate.getValue());
        budget.setFinalDate(set_budget_initDate.getValue().plusMonths(1));
        budget.setCategories(categories);
        budget.setId(
                set_budget_initDate.getValue().getMonth().toString().toUpperCase() +
                        "_" +
                        set_budget_initDate.getValue().getYear()
        );
    }

    private boolean validateInput() {
        return !set_budget_initDate.getValue().toString().isEmpty() &&
                categories != null;
    }
}
