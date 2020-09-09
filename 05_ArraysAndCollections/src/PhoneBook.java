import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneBook {
    private static final String REG_PHONE = "[\\d]{11,12}";
    private static final String REG_NAME = "[[\\p{Alpha}][а-я][А-Я]\\s]+";
    private HashMap<String, String> phoneBook = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private String consoleOperation() {
        String data;
        data = scanner.nextLine();
       /* try (Scanner scanner = new Scanner(System.in)) {
            data = scanner.nextLine();
        }*/
        return data;
    }

    public void run() {
        String command;
        do {
            System.out.println("Введите имя (только буквы и пробелы) или номер телефона (содержит 11 - 12 цифр в любом формате) " +
                    System.lineSeparator() +
                    "Для просмотра введите - list, " +
                    "Для выхода введите - exit");
            command = consoleOperation();
            processingCommands(command);
        } while (!command.equalsIgnoreCase("exit"));
        scanner.close();
    }

    private void processingCommands(String data) {
        data = data.trim().replaceAll("\\s+", " ");
        if (data.equalsIgnoreCase("list")) {
            if (phoneBook.size() > 0) {
                view();
            } else {
                System.out.println("Телефонная книга пуста");
            }
        } else if (data.equalsIgnoreCase("exit") || data.equalsIgnoreCase("учше")) {
            return;
        } else if (data.matches(REG_NAME)) {
            if (isContainsName(data)) {
                viewData(data, getPhone(data));
            } else {
                receivingPhoneVerification(data);
            }
        } else if ((data = phoneVerification(data)).length() > 0) {
            if (isContainsPhone(data)) {
                viewData(getName(data), data);
            } else {
                receivingNameVerification(data);
            }
        } else {
            System.out.println("Введите верные данные");
        }
    }

    private void view() {
        phoneBook.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach((entry) -> System.out.println("Имя: " + entry.getValue() + " - " + "телефон: " + entry.getKey()));
    }

    private void receivingNameVerification(String data) {
        System.out.println("Введите Ф.И.О.");
        String name = consoleOperation();
        if (name.equals("cancel")) {
            return;
        }
        if (name.matches(REG_NAME)) {
            addPhoneBook(data, name);
        } else {
            System.out.println("Только буквы английского, русского алфавитов и пробелы. Если передумали - " + "cancel");
            receivingNameVerification(data);
        }
    }

    private void receivingPhoneVerification(String data) {
        System.out.println("Введите номер телефона в любом формате 11 - 12 цифр");
        String phone;
        phone = consoleOperation();
        if (phone.equals("cancel")) {
            return;
        }
        if ((phone = phoneVerification(phone)).length() > 0) {
            addPhoneBook(phone, data);
        } else {
            System.out.println("Ошибка во вводе номера. Если передумали - " + "cancel");
            receivingPhoneVerification(data);
        }
    }

    private String phoneVerification(String phone) {
        phone = phone.replaceAll("[^\\d]+", "");
        if (phone.matches(REG_PHONE)) {
            return phone;
        } else {
            return "";
        }
    }


    private boolean isContainsName(String data) {
        return phoneBook.containsValue(data);
    }

    private boolean isContainsPhone(String data) {
        return phoneBook.containsKey(data);
    }

    private String getPhone(String name) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private String getName(String phone) {
        return phoneBook.get(phone);
    }

    private void addPhoneBook(String phone, String name) {
        phoneBook.put(phone, name);
        System.out.println("Запись добавлена");
    }

    private void viewData(String name, String phone) {
        System.out.println("Имя: " + name + " - телефон: " + phone);
    }
}
