package Exceptions;

public class NameFormatException extends Exception {
    private static final String message = "Неверный формат имени - ";
    String name;

    public NameFormatException(String name) {
        super(message);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
