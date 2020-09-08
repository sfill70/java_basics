import java.util.HashSet;
import java.util.Scanner;

public class SetEmail {

    // Я не помню почему я написал странное A-Za-z0-9 но наверно были причины, поэтому оставлю
    private final String REG_CHECK_EMAIL = "([\\w._+-]+[@][A-Za-z0-9._+-]+[.][a-z]{1,6})";
    private HashSet<String> setEmail = new HashSet<>();

    private void viewSetEmail() {
        if (setEmail.size() == 0) {
            System.out.println("Список E-mail пуст");
            return;
        }
        for (String email : setEmail
        ) {
            System.out.println(email);
        }
    }

    private void addSetEmail(String data) {
        setEmail.add(data);
    }

    private void processingCommands(String data) {
        data = data.trim().replaceAll("\\s+", " ");
        String command = data;
        if (data.contains(" ")) {
            command = data.substring(0, data.indexOf(" "));
        }
        if (command.equalsIgnoreCase("exit")) {
            return;
        } else if (command.equalsIgnoreCase("list")) {
            viewSetEmail();
        } else if (command.equalsIgnoreCase("add")) {
            if (data.trim().substring(data.indexOf(" ")+1).matches(REG_CHECK_EMAIL)) {
                addSetEmail(data.trim().substring(data.indexOf(" ")));
            } else {
                System.out.println("Неверный формат E-mail");
            }
        } else {
            System.out.println("Неверная команда");
        }
    }

    public void runSetEmail() {
        String command;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("Введите команду " +
                        "Для выхода введите - exit");
                command = scanner.nextLine();
                processingCommands(command);
            } while (!command.equalsIgnoreCase("exit"));
        }
    }
}
