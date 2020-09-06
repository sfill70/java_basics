import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    static ArrayList<String> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        runToDoList();
    }

    static void viewToDoList() {
        if (toDoList.size() == 0) {
            System.out.println("Список дел пуст");
            return;
        }
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.print("Дело N" + (i + 1) + " - ");
            System.out.println(toDoList.get(i));
        }
    }

    static void addToDoList(String data) {
        System.out.println("ADD");
        String[] information = data.split("\\s");
        int index;
        if (information.length >= 3) {
            try {
                index = Integer.parseInt(information[1]);
                toDoList.add(index - 1, data.substring(data.indexOf(information[1]) + information[1].length()));
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат команды ADD - второй пареметр должен быть числом");
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Заданный номер выходит за границы списка, допустимый номер от 1 до " + (toDoList.size() + 1));
                return;
            }
        } else if (information.length == 2) {
            toDoList.add(data.substring(data.indexOf(" ") + 1));
            System.out.println("Добавлена запись - " + toDoList.size());
            return;
        } else {
            System.out.println("Неверный формат команды ADD");
            return;
        }
        System.out.println("Добавлена запись - " + (index));
    }

    static void editToDoList(String data) {
        String[] information = data.split("\\s");
        int index;
        if (information.length >= 3) {
            try {
                index = Integer.parseInt(information[1]);
                toDoList.set(index - 1, data.substring(data.indexOf(information[1]) + information[1].length()));
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат команды EDIT - второй пареметр должен быть числом");
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Заданный номер выходит за границы списка, допустимый номер от 1 до" + toDoList.size());
                return;
            }
        } else {
            System.out.println("Неверный формат команды EDIT");
            return;
        }
        System.out.println("Обновлена запись - " + (index));

    }

    static void deleteToDoList(String data) {
        String[] information = data.split("\\s");
        int index;
        if (information.length == 2) {
            try {
                index = Integer.parseInt(information[1]);
                toDoList.remove(index - 1);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат команды DELETE - второй пареметр должен быть числом");
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Заданный номер выходит за границы списка,  допустимый номер от 1 до" + toDoList.size());
                return;
            }

        } else {
            System.out.println("Неверный формат команды DELETE");
            return;
        }
        System.out.println("Удалена запись " + (index));
    }


    static void choice(String data) {
        data = data.trim().replaceAll("\\s+", " ");
        String command = data;
        if (data.contains(" ")) {
            command = data.substring(0, data.indexOf(" "));
        }
        // Я не люблю switch
        //не использовал startsWith что бы убрать зависимость от регистра
        if (command.equalsIgnoreCase("exit")) {
            return;
        }
        if (command.equalsIgnoreCase("LIST")) {
            viewToDoList();
            System.out.println(data);
            return;
        }
        if (command.equalsIgnoreCase("ADD")) {
            addToDoList(data);
            return;
        }
        if (command.equalsIgnoreCase("EDIT")) {
            editToDoList(data);
            return;
        }
        if (command.equalsIgnoreCase("DELETE")) {
            deleteToDoList(data);
            return;
        }
        System.out.println("Неверная команда");
    }

    static void runToDoList() {
        String command;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите команду " +
                    "Для выхода введите - exit");
            command = scanner.nextLine();
            choice(command);
        } while (!command.equalsIgnoreCase("exit"));
        scanner.close();
    }
}
