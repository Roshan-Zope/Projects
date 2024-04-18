package com.expensetracker.models.Repository;

import com.expensetracker.models.DatabaseConnection;
import com.expensetracker.models.DatabaseProperties;
import com.expensetracker.models.entities.Expense;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public ObjectId save(Expense expense) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("expenses");
        Document document = new Document()
                .append("category", expense.getCategory())
                .append("amount", expense.getAmount())
                .append("date", expense.getDate())
                .append("userid", expense.getUser().getId());

        databaseProperties.getMongoCollection().insertOne(document);
        ObjectId id = document.getObjectId("_id");
        databaseProperties.getMongoClient().close();
        return id;
    }

    public Double getTotalAmount(ObjectId userid, LocalDate startDate, LocalDate endDate) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("expenses");
        Document filter = new Document("userid", userid)
                .append("date", new Document("$gte", startDate)
                        .append("$lte", endDate));

        List<Document> pipeline = Arrays.asList(
                new Document("$match", filter),
                new Document("$group", new Document("_id", null)
                        .append("totalAmount", new Document("$sum", "$amount")))
        );

        List<Document> result = databaseProperties.getMongoCollection().aggregate(pipeline).into(new ArrayList<>());
        if (!result.isEmpty()) return result.getFirst().get("totalAmount", Double.class);
        else return -1.0;
    }

    public ObservableList<Expense> getExpenseByCategory(ObjectId id, String category, LocalDate intiDate, LocalDate finalDate) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("expenses");
        Document filter = new Document("userid", id)
                .append("category", category)
                .append("date", new Document("$gte", intiDate)
                        .append("$lte", finalDate));

        MongoCursor<Document> cursor = databaseProperties.getMongoCollection().find(filter).iterator();
        ObservableList<Expense> expenses = FXCollections.observableArrayList();
        while (cursor.hasNext()) {
            Document expense = cursor.next();
            Expense expense1 = new Expense();
            expense1.setId(expense.getObjectId("_id"));
            expense1.setDate(expense.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            expense1.setCategory(expense.get("category", String.class));
            expense1.setAmount(expense.get("amount", Double.class));
            expenses.add(expense1);
        }
        return expenses;
    }

    public ObservableList<Expense> getLatestTransactions(ObjectId id, LocalDate initDate, LocalDate finalDate) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("expenses");
        Document filter = new Document("userid", id)
                .append("date", new Document("$gte", initDate)
                        .append("$lte", finalDate));

        MongoCursor<Document> cursor = databaseProperties.getMongoCollection().find(filter).iterator();
        ObservableList<Expense> transactions = FXCollections.observableArrayList();
        while (cursor.hasNext()) {
            Document transaction = cursor.next();
            Expense expense = new Expense();
            expense.setId(transaction.getObjectId("_id"));
            expense.setCategory(transaction.get("category", String.class));
            expense.setDate(transaction.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            expense.setAmount(transaction.get("amount", Double.class));
            transactions.add(expense);
        }
        return transactions;
    }

    public void delete(ObjectId id) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("expenses");
        Document filter = new Document("userid", id);
        databaseProperties.getMongoCollection().deleteOne(filter);
        databaseProperties.getMongoClient().close();
    }
}
