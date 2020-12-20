package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class CreateMongoClient implements AutoCloseable {
    MongoClient mongoClient;
    String host;
    int port;

    public CreateMongoClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.mongoClient = new MongoClient(host,port);
    }

    public MongoDatabase getDatabase(String dbName) {
       return mongoClient.getDatabase(dbName);
    }

    @Override
    public void close() {
        mongoClient.close();
    }
}
