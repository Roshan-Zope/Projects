package com.expensetracker.models.Repository;

import com.expensetracker.controllers.client.ProfileController;
import com.expensetracker.models.DatabaseConnection;
import com.expensetracker.models.DatabaseProperties;
import com.expensetracker.models.entities.Budget;
import com.expensetracker.models.entities.Category;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class BudgetRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public ObjectId save(Budget budget) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("budget");
        Document document = new Document()
                .append("id", budget.getId())
                .append("initDate", budget.getInitDate())
                .append("finalDate", budget.getFinalDate())
                .append("categories", convertBudgetToDocument(budget.getCategories()))
                .append("userid", budget.getUser().getId());

        databaseProperties.getMongoCollection().insertOne(document);
        ObjectId id = document.getObjectId("_id");
        databaseProperties.getMongoClient().close();
        return id;
    }

    public Budget getBudget(String id) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("budget");
        Document filter = new Document("id", id);
        Document result = databaseProperties.getMongoCollection().find(filter).first();
        return convertDocumentToBudget(result);
    }

    private Budget convertDocumentToBudget(Document result) {
        Budget budget = new Budget();
        budget.setId(result.getString("id"));
        budget.setInitDate(result.getDate("initDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        budget.setFinalDate(result.getDate("finalDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        budget.setUser(ProfileController.getCurrUser());
        List<Document> categories = result.getList("categories", Document.class);
        List<Category> categoryList = new ArrayList<>();
        for (Document category: categories) {
            Category category1 = new Category();
            category1.setCategory(category.getString("category"));
            category1.setAmount(category.getString("amount"));
            categoryList.add(category1);
        }
        budget.setCategories(categoryList);
        return budget;
    }

    public boolean update(String id, List<Category> categories) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("budget");
        for (Category category: categories) {
            if (isCategoryPresent(id, category.getCategory())) {
                Document filter = new Document("id", id)
                        .append("categories.category", category.getCategory());
                Document update = new Document("$set", new Document("categories.$.amount", category.getAmount()));
                databaseProperties.getMongoCollection().updateOne(filter, update);
            } else {
                Document filter = new Document("id", id);
                Document newCategory = new Document("category", category.getCategory())
                        .append("amount", category.getAmount());
                Document update = new Document("$push", new Document("categories", newCategory));
                databaseProperties.getMongoCollection().updateOne(filter, update);
            }
        }
        databaseProperties.getMongoClient().close();
        return true;
    }

    private boolean isCategoryPresent(String id, String category) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("budget");
        Document filter = new Document("id", id)
                .append("categories.category", category);

        Document result = databaseProperties.getMongoCollection().find(filter).first();
        databaseProperties.getMongoClient().close();
        return result != null;
    }

    private List<Document> convertBudgetToDocument(List<Category> categories) {
        List<Document> documents = new ArrayList<>();

        for (Category category: categories) {
            Document document = new Document()
                    .append("category", category.getCategory())
                    .append("amount", category.getAmount());
            documents.add(document);
        }
        return documents;
    }

    public void delete(ObjectId id) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("budget");
        Document filter = new Document("userid",id);
        databaseProperties.getMongoCollection().deleteOne(filter);
        databaseProperties.getMongoClient().close();
    }
}
