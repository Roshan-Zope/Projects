package com.expensetracker.models.Repository;

import com.expensetracker.models.DatabaseConnection;
import com.expensetracker.models.DatabaseProperties;
import com.expensetracker.models.entities.Expense;
import com.expensetracker.models.entities.Report;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public void generate(Report report) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("report");
        Document document = new Document()
                .append("iniDate", report.getIntiDate())
                .append("finalDate", report.getFinalDate())
                .append("date", report.getDate())
                .append("expenses", convertExpenseToDocument(report.getExpenses()))
                .append("totalAmount", report.getTotalAmount())
                .append("userid", report.getUser().getId());

        databaseProperties.getMongoCollection().insertOne(document);
        report.setId(document.getObjectId("_id").toString());
        databaseProperties.getMongoClient().close();
    }

    private List<Document> convertExpenseToDocument(List<Expense> expenses) {
        List<Document> documents = new ArrayList<>();

        for (Expense expense : expenses) {
            Document document = new Document()
                    .append("category", expense.getCategory())
                    .append("amount", expense.getAmount());
            documents.add(document);
        }
        return documents;
    }

    public void delete(ObjectId id) {
        DatabaseProperties databaseProperties = databaseConnection.getCollection("report");
        Document filter = new Document("userid", id);
        databaseProperties.getMongoCollection().deleteOne(filter);
        databaseProperties.getMongoClient().close();
    }
}
