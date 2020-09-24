import Exceptions.EmailFormatException;
import Exceptions.NameFormatException;
import Exceptions.PhoneFormatException;

import java.util.Scanner;

public class Main {
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
            if (tokens[0].equals("add")) {

                try {
                    executor.addCustomer(tokens[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Не верный формат команды add, надо ввести данные");
                } catch (NameFormatException e) {
                    System.out.print(e.getLocalizedMessage());
                    System.out.println(e.getName());

                } catch (EmailFormatException e) {
                    System.out.print(e.getLocalizedMessage());
                    System.out.println(e.getEmail());

                } catch (PhoneFormatException e) {
                    System.out.print(e.getLocalizedMessage());
                    System.out.println(e.getPhone());
                }

            } else if (tokens[0].equals("list")) {
                executor.listCustomers();
            } else if (tokens[0].equals("remove")) {
                try {
                    executor.removeCustomer(tokens[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Не верный формат команды remove, введите имя фамилия");
                    e.printStackTrace();
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
