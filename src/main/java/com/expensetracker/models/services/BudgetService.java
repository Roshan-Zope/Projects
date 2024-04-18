package com.expensetracker.models.services;

import com.expensetracker.models.Repository.BudgetRepository;
import com.expensetracker.models.entities.Budget;
import com.expensetracker.models.entities.Category;
import com.expensetracker.models.entities.Expense;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository = new BudgetRepository();

    public ObjectId setBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public boolean update(String id, List<Category> categories) {
         return budgetRepository.update(id, categories);
    }

    public Budget getBudget(String id) {
        return budgetRepository.getBudget(id);
    }
}
