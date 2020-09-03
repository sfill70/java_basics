import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    static ArrayList<String>toDoList = new ArrayList<>();
    public static void main(String[] args) {
        String command;
        do {
            System.out.println("Введите команду " +
                    "Для выхода введите - exit");
            command = (new Scanner(System.in)).nextLine();

            System.out.println(command);
        } while (!command.equalsIgnoreCase("exit"));


    }
    static void viewToDoList(){
        for (String toDo:toDoList
        ) {
            System.out.println(toDo);
        }
    }
    static void addToDoList(String data){
        for (String toDo:toDoList
        ) {
            System.out.println(toDo);
        }
    }
    static void editToDoList(String data){
        for (String toDo:toDoList
        ) {
            System.out.println(toDo);
        }
    }
    static void deleteToDoList(String data){
        for (String toDo:toDoList
        ) {
            System.out.println(toDo);
        }
    }
    static void choice(String data){

    }


}
