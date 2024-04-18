package com.expensetracker.models;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DatabaseProperties {
    private MongoClient mongoClient;
    private MongoCollection<Document> mongoCollection;

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }

    public void setMongoCollection(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }
}
