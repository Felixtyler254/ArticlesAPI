import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class codeApi {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void main(String[] args) {
        try {
            // Connect to MongoDB using MongoClients (newer version)
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("felix");

            if (database == null) {
                System.out.println("Database connection failed.");
                return;
            }

            System.out.println("Connected to the database: " + database.getName());

            // Access a collection
            MongoCollection<Document> collection = database.getCollection("articles");

            // Insert a document
            Document article = new Document("title", "Sample Article")
                    .append("content", "This is a sample article.")
                    .append("tags", List.of("example", "mongodb"))
                    .append("publishedDate", System.currentTimeMillis());
            collection.insertOne(article);
            System.out.println("Article inserted!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}
