import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import model.Book;
import model.Student;
import org.bson.BsonDocument;
import org.bson.Document;
import util.CreateMongoClient;
import util.ParserCsv;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    static String root = System.getProperty("user.dir");
    static String sDirSeparator = File.separator;
    private static final String PATH_FILE = String.join(sDirSeparator, root, "src", "main", "resources", "mongo.csv");
    private final static char CSV_SEPARATOR = ',';
    private final static char QUOTE = '"';

    public static void main(String[] args) {
        System.out.println(PATH_FILE);
        List<Book> bookList = new ArrayList<>();
        Gson gson = new Gson();     
        MongoCollection<Document> books = CreateMongoClient.getDatabase().getCollection("books");
        books.drop();
        fillingDBaseBooks(bookList, gson, books);
        printAllCollection(books);
        printBooksQuery(books, gson);

        MongoCollection<Document> students = CreateMongoClient.getDatabase().getCollection("students");
        students.drop();
        List<String[]> listArray = ParserCsv.getArraysDataList(PATH_FILE, CSV_SEPARATOR, QUOTE);
        fillingDBaseStudents(students, listArray);
        printDocumentQuery(students);
    }

    private static void fillingDBaseBooks(List<Book> bookList, Gson gson, MongoCollection<Document> books) {
        bookList.add(new Book("Поколение П","Пелевин В.О.",  1999));
        bookList.add(new Book( "S.N.U.F.F.","Пелевин В.О.", 2012));
        bookList.add(new Book( "Чужак в чужой стране","Хайнлайн Р.Э.", 1962));
        bookList.add(new Book("Конец вечности","Азимов А.",  1955));
        bookList.add(new Book("Восход Эндимиона","Симонс Д.",  1997));

        for (Book book : bookList
        ) {
            Document document = Document.parse(gson.toJson(book));
            books.insertOne(document);
        }
    }

    private static void printBooksQuery(MongoCollection<Document> books, Gson gson) {
        System.out.println("Самая старая книга: " +
                Objects.requireNonNull(books.find().sort(BsonDocument.parse("{year: 1}")).first()));

        books.find(new Document("name", new Document("$regex", "Пелевин")))
                .forEach((Consumer<Document>) document -> {
            System.out.println("Книги любимого автора - " + gson.fromJson(document.toJson(), Book.class));
        });

        books.find(BsonDocument.parse("{name: {$eq: \"Хайнлайн Р.Э.\"}}")).forEach((Consumer<Document>) document -> {
            System.out.println("Книги любимого автора" + document);
        });
    }

    private static void fillingDBaseStudents(MongoCollection<Document> students, List<String[]> listArray) {
        for (String[] arr : listArray
        ) {
            if (arr.length == 3) {
                List<String> courses = Arrays.asList(arr[2].split(","));
                int age;
                try {
                    age = Integer.parseInt(arr[1]);
                } catch (NumberFormatException e) {
                    age = 0;
                    e.printStackTrace();
                }
                Document document = new Document().append("name", arr[0])
                        .append("age", age)
                        .append("courses", courses);
                students.insertOne(document);
            }

        }
    }

    private static void printDocumentQuery(MongoCollection<Document> collection) {
        Gson gson1 = new Gson();
        //Общее число студентов
        System.out.println("Студентов: " +
                collection.countDocuments());
        //Число студентов старше 40 лет
        System.out.println("Студентов старше 40: " +
                collection.countDocuments(Document.parse("{age: {$gt: 40}}")));
        //Имя самого молодого студента
        System.out.println("Самый молодой студент: " +
                Objects.requireNonNull(collection.find().sort(BsonDocument.parse("{age: 1}")).first()).get("name"));
        //Список курсов самого старого студента
        System.out.println("Курсы самого старого студента: " +
                Objects.requireNonNull(collection.find().sort(BsonDocument.parse("{age: -1}")).first()).get("courses"));
        Document doc =  Objects.requireNonNull(collection.find().sort(BsonDocument.parse("{age: -1}")).first());
        Student student =gson1.fromJson(doc.toJson(), Student.class);
        System.out.println(student);
        System.out.println(student.getClass().getName());
    }

    private static void printAllCollection(MongoCollection<Document> collection) {
        collection.find()
                .forEach((Consumer<Document>) System.out::println);
    }

    private static void printDBQuery() {
        DBCollection students = CreateMongoClient.getDBDatabase().getCollection("students");
        System.out.println("Количество студентов: " + students.count() + " человек.");
        int count = students.find(new BasicDBObject("age", new BasicDBObject("$gt", 40))).count();
        System.out.println("Количество студентов старше 40 лет : " + count + " человек.");
        DBCursor youngStudent = students.find().sort(new BasicDBObject("age", 1)).limit(1);
        System.out.println("Имя самого молодого студента: " + youngStudent.one().get("name"));
        DBCursor oldStudent = students.find().sort(new BasicDBObject("age", -1)).limit(1);
        System.out.println("Список куров самого старого студента " + oldStudent.one().get("courses"));
    }


}
