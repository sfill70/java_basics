package util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


public class CreateMongoClient {

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        return mongoClient.getDatabase("local");
    }

    public static DB getDBDatabase() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        return mongoClient.getDB("local");
    }
}
