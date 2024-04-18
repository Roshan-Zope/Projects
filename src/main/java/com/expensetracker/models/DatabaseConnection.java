package com.expensetracker.models;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnection {
    public DatabaseProperties getCollection(String collection) {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatabaseProperties databaseProperties = new DatabaseProperties();
        databaseProperties.setMongoClient(MongoClients.create(properties.getProperty("spring.data.mongodb.uri")));
        MongoDatabase mongoDatabase = databaseProperties.getMongoClient().getDatabase(properties.getProperty("spring.data.mongodb.database"));
        databaseProperties.setMongoCollection(mongoDatabase.getCollection(collection));
        return databaseProperties;
    }
}
