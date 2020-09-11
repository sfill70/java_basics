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
        boolean isRun = true;
        while (isRun) {
            System.out.println("Введите имя (только буквы и пробелы) или номер телефона (содержит 11 - 12 цифр в любом формате) " +
                    System.lineSeparator() +
                    "Для просмотра введите - list, " +
                    "Для выхода введите - exit");
            command = consoleOperation();
            if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("учше")) {
                isRun = false;
                continue;
            }
            processingCommands(command);
        }
        scanner.close();
    }

    private void processingCommands(String data) {
        data = data.trim().replaceAll("\\s+", " ");
        if (data.equalsIgnoreCase("list")) {
            view();
        } else if (data.matches(REG_NAME)) {
            if (isContainsName(data)) {
                printSubscriber(data, getPhone(data));
            } else {
                requestPhoneVerification(data);
            }
        } else if ((data = phoneVerification(data)).length() > 0) {
            if (isContainsPhone(data)) {
                printSubscriber(getName(data), data);
            } else {
                addPhoneBook(data, requestNameFromConsole());
            }
        } else {
            System.out.println("Введите верные данные");
        }
    }

    private void view() {
        if (phoneBook.size() == 0) {
            System.out.println("Телефонная книга пуста");
        }
        phoneBook.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach((entry) -> System.out.printf("Имя:%s - телефон: %s ", entry.getValue(), entry.getKey()));
    }

    private String requestNameFromConsole() {
        System.out.println("Введите Ф.И.О.");
        while (true) {
            String name = consoleOperation();
            if (name.matches(REG_NAME)) {
                return name;
            }
            System.out.println("Только буквы английского, русского алфавитов и пробелы. Если передумали - " + "cancel");
        }
    }

    //Рекрусивный метод хуже.
    private void requestPhoneVerification(String data) {
        System.out.println("Введите номер телефона в любом формате 11 - 12 цифр");
        String phone = consoleOperation();
        if (phone.equals("cancel")) {
            return;
        }
        if ((phone = phoneVerification(phone)).length() > 0) {
            addPhoneBook(phone, data);
        } else {
            System.out.println("Ошибка во вводе номера. Если передумали - " + "cancel");
            requestPhoneVerification(data);
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
        return "Абонент с таким номером не существует, а должен";
    }

    private String getName(String phone) {
        return phoneBook.get(phone);
    }

    private void addPhoneBook(String phone, String name) {
        if (name.equalsIgnoreCase("cancel")) {
            return;
        }
        phoneBook.put(phone, name);
        System.out.println("Запись добавлена");
    }

    private void printSubscriber(String name, String phone) {
        System.out.println("Имя: " + name + " - телефон: " + phone);
    }
}
