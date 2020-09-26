import Exceptions.NameFormatException;

import java.util.Scanner;

public class Main {
    private static final String REMOVE = "remove";
    private static final String ADD = "add";
    private static final String MESSAGE = "Неверный формат команды %s, введите help";
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) {
        System.out.println("Поехали");
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for (; ; ) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            if (tokens[0].equals(ADD)) {
                try {
                    executor.addCustomer(tokens[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.printf(MESSAGE, ADD);
                } catch (NameFormatException e) {
                    System.out.print(e.getAdditionalMessage());
                }
            } else if (tokens[0].equals("list")) {
                executor.listCustomers();
            } else if (tokens[0].equals(REMOVE)) {
                try {
                    executor.removeCustomer(tokens[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.printf(MESSAGE, REMOVE);
                }
            } else if (tokens[0].equals("count")) {
                System.out.println("There are " + executor.getCount() + " customers");
            } else if (tokens[0].equals("help")) {
                System.out.println(helpText);
            } else {
                System.out.println(commandError);
            }
        }
    }
}
