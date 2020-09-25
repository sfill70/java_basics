import Exceptions.*;

import java.util.HashMap;

public class CustomerStorage {
    private HashMap<String, Customer> storage;
    private static final String NAME = "[[\\p{Alpha}][а-я][А-Я]]+";
    private static final String PHONE = "[\\d]{11,12}";
    private static final String EMAIL = "((?i)[\\w._+-]+[@][A-Za-z0-9._+-]+[.][a-z]{1,6})";

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws ArrayIndexOutOfBoundsException, NameFormatException, EmailFormatException, PhoneFormatException {
        String[] components = data.split("\\s+");

        checkName(components);
        String name = components[0] + " " + components[1];
        checkEmail(components);
        checkPhone(components);
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    private void checkName(String[] components) throws NameFormatException {
        if (components.length < 2 || !components[0].matches(NAME) || !components[1].matches(NAME)) {
            if (components.length < 2) {
                throw new NameFormatException("нет имени и фамилии");
            }
            throw new NameFormatException(components[0] + " " + components[1]);
        }
    }

    private void checkEmail(String[] components) throws EmailFormatException {
        if (components.length < 3 || !components[2].matches(EMAIL)) {
            if (components.length < 3) {
                throw new EmailFormatException("нет email");
            }
            throw new EmailFormatException(components[2]);
        }
    }

    private void checkPhone(String[] components) throws PhoneFormatException {
        if (components.length < 4 || !components[3].trim().replaceAll("[^0-9]", "").matches(PHONE)) {
            if (components.length < 4) {
                throw new PhoneFormatException("нет телефона");
            }
            throw new PhoneFormatException(components[3]);
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}