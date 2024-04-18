package com.expensetracker.models.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
@Document(collection = "report")
public class Report {
    @Id
    private String id;
    private LocalDate intiDate;
    private LocalDate finalDate;
    private LocalDate date;
    private Double totalAmount;
    private User user;
    private List<Expense> expenses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getIntiDate() {
        return intiDate;
    }

    public void setIntiDate(LocalDate intiDate) {
        this.intiDate = intiDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
