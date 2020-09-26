package Exceptions;

public class PhoneFormatException extends  NameFormatException {
    private static final String message = "Неверный формат телефона - ";
    String additionalMessage;

    public PhoneFormatException(String additionalMessage) {
        super(message);
        this.additionalMessage = additionalMessage;
    }

    @Override
    public String getAdditionalMessage() {
        return message + additionalMessage;
    }
}
