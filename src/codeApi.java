import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Class to represent an Article
class Article {
    private String id;
    private String title;
    private String content;
    private List<String> tags;
    private long publishedDate;

    public Article(String id, String title, String content, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.publishedDate = System.currentTimeMillis(); // Current time in milliseconds
    }

    // Getters for Article properties
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }

    public long getPublishedDate() {
        return publishedDate;
    }
}

// Class to manage Articles
class ArticleManager {
    private Map<String, Article> articles = new HashMap<>();

    // Method to create a new article
    public void createArticle(String id, String title, String content, List<String> tags) {
        articles.put(id, new Article(id, title, content, tags));
        System.out.println("Article created: " + title);
    }

    // Method to retrieve all articles
    public List<Article> getAllArticles() {
        return new ArrayList<>(articles.values());
    }

    // Method to retrieve a single article by ID
    public Optional<Article> getArticle(String id) {
        return Optional.ofNullable(articles.get(id));
    }

    // Method to update an article
    public void updateArticle(String id, String title, String content, List<String> tags) {
        Article article = articles.get(id);
        if (article != null) {
            articles.put(id, new Article(id, title, content, tags));
            System.out.println("Article updated: " + title);
        } else {
            System.out.println("Article not found!");
        }
    }

    // Method to delete an article
    public void deleteArticle(String id) {
        if (articles.remove(id) != null) {
            System.out.println("Article deleted: " + id);
        } else {
            System.out.println("Article not found!");
        }
    }
}

// Main class to simulate the API
public class codeApi {
    public static void main(String[] args) {
        ArticleManager manager = new ArticleManager();

        // Creating articles
        manager.createArticle("1", "First Article", "This is the content of the first article.", List.of("tag1", "tag2"));
        manager.createArticle("2", "Second Article", "This is the content of the second article.", List.of("tag2", "tag3"));

        // Retrieving all articles
        List<Article> articles = manager.getAllArticles();
        for (Article article : articles) {
            System.out.println("Article ID: " + article.getId() + ", Title: " + article.getTitle());
        }

        // Retrieving a single article
        Optional<Article> article = manager.getArticle("1");
        article.ifPresentOrElse(
                a -> System.out.println("Retrieved Article: " + a.getTitle()),
                () -> System.out.println("Article not found!")
        );

        // Updating an article
        manager.updateArticle("1", "Updated First Article", "Updated content.", List.of("tag1", "tag3"));

        // Deleting an article
        manager.deleteArticle("2");
    }
}
