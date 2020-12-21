import com.google.gson.Gson;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import model.Product;
import model.Shop;
import org.bson.Document;
import org.bson.conversions.Bson;
import util.CreateMongoClient;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.lt;

public class Actions {
    private static Gson gson = new Gson();
    private String dbName;
    private static MongoCollection<Document> products;
    private static MongoCollection<Document> shops;

    public Actions(CreateMongoClient createMongoClient, String dbName) {
        products = createMongoClient.getDatabase(dbName).getCollection("products");
        shops = createMongoClient.getDatabase(dbName).getCollection("shops");
    }

    public static MongoCollection<Document> getProducts() {
        return products;
    }

    public static MongoCollection<Document> getShops() {
        return shops;
    }

    protected static void actionAddShop(String data) {
        if (getShop(data) == null) {
            Document document = Document.parse(gson.toJson(new Shop(data)));
            shops.insertOne(document);
        } else {
            System.out.println("Такой магазин уже есть в списке");
        }
    }

    protected static void actionAddProduct(String data) {
        String[] array = data.split("\\s");
        if (getProduct(array[0]) == null) {
            int price;
            try {
                price = Integer.parseInt(array[1].trim());
            } catch (NumberFormatException e) {
                price = 0;
                e.printStackTrace();
            }
            Document document = Document.parse(gson.toJson(new Product(array[0], price)));
            products.insertOne(document);
        } else {
            System.out.println("Такой продукт уже есть в списке");
        }

    }

    protected static void actionShowProduct(String data) {
        System.out.println(data);
        String[] array = data.split("\\s");
        String shopName = array[1];
        boolean isShop = getShop(shopName) != null;
        boolean isProduct = getProduct(array[0]) != null;
        if (isShop && isProduct) {
            Objects.requireNonNull(shops.updateOne(getShop(shopName), new Document("$addToSet",
                    new Document("products", getProduct(array[0]).get("name")))));
        } else {
            if (!isShop) {
                System.out.println("Магазина нет в списке");
            }
            if (!isProduct) {
                System.out.println("Продукта нет в списке");
            }
        }
    }

    protected static void actionStatisticsProduct() {
        printReport("Основная информация", getBasicInfo());
        printReport("Средняя цена товаров: ", getAverageProductInEachShop());
        printReport("Количество товаров дешевле 100 руб.: ", getLowTargetPrice(100));

    }

    private static AggregateIterable<Document> getBasicInfo() {
        Bson unwind = Aggregates.unwind("$products");
        Bson sort = Aggregates.sort(Sorts.ascending("products.price"));
        Bson group = Aggregates.group(
                "$name",
                Accumulators.sum("Number of products at store, total", 1L),
                Accumulators.first("Min price product", "$products"),
                Accumulators.last("Max price product", "$products"));
        Bson sortById = Aggregates.sort(Sorts.ascending("_id"));
        return shops.aggregate(Arrays.asList(unwind, sort, group, sortById));
    }

    private static AggregateIterable<Document> getAverageProductInEachShop() {
        return shops.aggregate(Arrays.asList(Aggregates.lookup("products", "products", "name", "productsList"),
                Aggregates.unwind("$productsList"),
                Aggregates.group("$name", Accumulators.avg("avgPrice", "$productsList.price"))));
    }

    private static AggregateIterable<Document> getLowTargetPrice(int target) {
        return shops.aggregate(Arrays.asList(Aggregates.lookup("products", "products", "name", "productsList"),
                Aggregates.unwind("$productsList"),
                Aggregates.match(lt("productsList.price", target)),
                Aggregates.group("$name", Accumulators.sum("productsList", 1L))));
    }


    private static void printReport(String title, AggregateIterable<Document> data) {
        System.out.println(title);
        data.forEach((Consumer<Document>) System.out::println);
    }

    private static Document getShop(String name) {
        return shops.find(new Document("name", name)).first();
    }

    private static Document getProduct(String name) {
        return products.find(new Document("name", name)).first();
    }
}
