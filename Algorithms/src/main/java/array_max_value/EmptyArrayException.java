package array_max_value;

public class EmptyArrayException extends Exception{
    String message = "Exception: Empty array";

    @Override
    public String toString() {
        return message;
    }
}
