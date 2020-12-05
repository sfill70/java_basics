package main.exception;

public class EntityNotFoundException extends RuntimeException {
    String message = "Entity not found";
    String pageMessage = "Page not found";

    @Override
    public String getMessage() {
        return message;
    }

    public String getPageMessage() {
        return pageMessage;
    }
}
