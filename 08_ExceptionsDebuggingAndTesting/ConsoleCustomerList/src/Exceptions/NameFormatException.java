package Exceptions;

public class NameFormatException extends RuntimeException {
    private static final String message = "Неверный формат имени (только буквы русского или английского алфавита)- ";
    protected String additionalMessage;

    public NameFormatException(String additionalName) {
        super(message);
        this.additionalMessage = additionalName;

    }

    public String getAdditionalMessage() {
        return message + additionalMessage;
    }
}
