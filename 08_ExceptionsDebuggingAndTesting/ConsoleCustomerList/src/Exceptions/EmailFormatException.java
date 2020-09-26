package Exceptions;

public class EmailFormatException extends  NameFormatException {
    String additionalMessage;
    private static final String message = "Неверный формат email - ";

    public EmailFormatException(String additionalMessage) {
        super(message);
        this.additionalMessage = additionalMessage;
    }

    @Override
    public String getAdditionalMessage() {
        return message + additionalMessage;
    }
}
