package model;

public class Book {
    String title;
    String name;
    Integer year;

    public Book(String title, String name, Integer year) {
        this.title = title;
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        return "model.Book{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }

}
