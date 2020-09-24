package Exceptions;

public class EmailFormatException extends Exception {
    String email;
    private static final String message = "Неверный формат email - ";

    public EmailFormatException(String email) {
        super(message);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
