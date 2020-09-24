package Exceptions;

public class PhoneFormatException extends Exception {
    private static final String message = "Неверный формат телефона - ";
    String phone;

    public PhoneFormatException(String phone) {
        super(message);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
