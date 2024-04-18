package com.expensetracker.models.Repository;

import com.expensetracker.controllers.client.ProfileController;
import com.expensetracker.models.DatabaseConnection;
import com.expensetracker.models.DatabaseProperties;
import com.expensetracker.models.entities.User;
import org.bson.Document;
import org.bson.types.ObjectId;

public class UserRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final BudgetRepository budgetRepository = new BudgetRepository();
    private  final ExpenseRepository expenseRepository = new ExpenseRepository();
    private final ReportRepository reportRepository = new ReportRepository();

    public ObjectId save(User user) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("users");
        Document document = new Document()
                .append("username", user.getUsername())
                .append("passkey", user.getPasskey())
                .append("email", user.getEmail());

        databaseProperties.getMongoCollection().insertOne(document);
        ObjectId id = document.getObjectId("_id");
        databaseProperties.getMongoClient().close();
        return id;
    }

    public Document find(String username, String passkey) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("users");
        Document filter = new Document()
                .append("username", username)
                .append("passkey", passkey);

        Document document = databaseProperties.getMongoCollection().find(filter).first();
        databaseProperties.getMongoClient().close();
        return document;
    }

    public void update(String newPassword) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("users");
        Document filter = new Document("_id", ProfileController.getCurrUser().getId());
        Document update = new Document("$set", new Document("passkey", newPassword));
        databaseProperties.getMongoCollection().updateOne(filter, update);
        databaseProperties.getMongoClient().close();
    }

    public void delete(ObjectId id) {
        budgetRepository.delete(id);
        expenseRepository.delete(id);
        reportRepository.delete(id);
        DatabaseProperties databaseProperties = databaseConnection.getCollection("users");
        Document filter = new Document("_id", id);
        databaseProperties.getMongoCollection().deleteOne(filter);
        databaseProperties.getMongoClient().close();
    }
}
