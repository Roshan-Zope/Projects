package com.expensetracker.models.entities;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
@Document(collection = "budget")
public class Budget {
    @Id
    private String id;
    private List<Category> categories;
    private LocalDate initDate;
    private LocalDate finalDate;
    @Autowired
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
