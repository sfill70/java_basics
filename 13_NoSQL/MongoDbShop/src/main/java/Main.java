import com.mongodb.client.MongoCollection;
import org.bson.Document;
import util.CreateMongoClient;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static final String REG_SHOP_ADD = "(?i)add_shop\\s+.+";
    public static final String REG_PRODUCT_ADD = "(?i)add_product\\s+.+\\s+\\d+";
    public static final String REG_SHOW_PRODUCT = "(?i)show_product\\s+.+\\s+.+";
    public static final String REG_STATISTICS_PRODUCT = "(?i)statistics_product";
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 27017;
    private static final String DB_NAME = "local";
    private static final CreateMongoClient CREATE_MONGO_CLIENT = new CreateMongoClient(HOST, PORT);
    private static final Actions ACTIONS = new Actions(CREATE_MONGO_CLIENT, DB_NAME);

    public static void main(String[] args) {

        run();
    }

    private static void run() {
        String command;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Введите команду " +
                        "Для выхода введите - exit");
                command = scanner.nextLine();
                if (command.isEmpty() || command.equalsIgnoreCase("exit")) {
                    continue;
                }
                processingCommands(command);/*
                printAllCollection(Actions.getProducts());
                printAllCollection(Actions.getShops());*/
            } while (!command.equalsIgnoreCase("exit"));
        }
    }


    private static void processingCommands(String command) {
        command = command.trim().replaceAll("\\s+", " ");
        String data = command;
        if (command.contains(" ")) {
            data = command.substring(command.indexOf(" ") + 1);
            if (command.matches(REG_SHOP_ADD)) {
                System.out.println("Добавить магазин");
                Actions.actionAddShop(data);
            } else if (command.matches(REG_PRODUCT_ADD)) {
                System.out.println("Добавить товар");
                Actions.actionAddProduct(data);
            } else if (command.matches(REG_SHOW_PRODUCT)) {
                System.out.println("Выставить товар");
                Actions.actionShowProduct(data);
            } else {
                System.out.println("Неверная команда");
            }
        } else if (command.matches(REG_STATISTICS_PRODUCT)) {
            Actions.actionStatisticsProduct();
        } else {
            System.out.println("Неверная команда");
        }
    }


    private static void printAllCollection(MongoCollection<Document> collection) {
        collection.find()
                .forEach((Consumer<Document>) System.out::println);
    }

}
