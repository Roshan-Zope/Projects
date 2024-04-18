package com.expensetracker.models.services;

import com.expensetracker.models.Repository.ExpenseRepository;
import com.expensetracker.models.entities.Expense;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.time.LocalDate;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository = new ExpenseRepository();

    public ObjectId createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Double getTotalAmount(ObjectId userid, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.getTotalAmount(userid, startDate, endDate);
    }

    public ObservableList<Expense> getExpenseByCategory(ObjectId id, String category, LocalDate initDate, LocalDate finalDate) {
        return expenseRepository.getExpenseByCategory(id, category, initDate, finalDate);
    }

    public ObservableList<Expense> getLatestTransaction(ObjectId id, LocalDate initDate, LocalDate finalDate) {
        return expenseRepository.getLatestTransactions(id, initDate, finalDate);
    }

//    public Optional<Expense> getExpenseById(Integer id) {
//        return expenseRepository.findById(id);
//    }
//
//    public Expense updateExpense(Expense expense) {
//        return expenseRepository.save(expense);
//    }
//
//    public void deleteExpense(Integer id) {
//        expenseRepository.deleteById(id);
//    }
}
