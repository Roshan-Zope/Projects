package com.expensetracker.models.Repository;

import com.expensetracker.models.DatabaseConnection;
import com.expensetracker.models.DatabaseProperties;
import org.bson.Document;
import org.bson.types.ObjectId;

public class FeedbackRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public void save(ObjectId id, String input) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("report_feedback");
        Document document = new Document("userid", id)
                .append("feedback-report", input);

        databaseProperties.getMongoCollection().insertOne(document);
        databaseProperties.getMongoClient().close();
    }
}
