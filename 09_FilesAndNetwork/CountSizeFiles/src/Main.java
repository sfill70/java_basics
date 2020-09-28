import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    public static void main(String[] args){


        System.out.println(System.getProperty("user.dir"));
        try {
            runCountSizeFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        CountSizeFilesRecursion countSizeFilesRecursion = new CountSizeFilesRecursion(System.getProperty("user.dir"));
//        countSizeFilesRecursion.printFiles();
//        // Не вызывает исключентй
//        CountSizeFilesQueue countSizeFilesQueue = new CountSizeFilesQueue(System.getProperty("user.dir"));
//        countSizeFilesQueue.printFiles();

    }

    static void runCountSizeFiles() throws IOException {
        String path;
        CountSizeFilesRecursion countSizeFilesRecursion;
        CountSizeFilesQueue countSizeFilesQueue;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.println("путь к директории " +
                        "Для выхода введите - exit");
                path = reader.readLine();
                countSizeFilesRecursion = new CountSizeFilesRecursion(path);
                countSizeFilesQueue = new CountSizeFilesQueue(path);
                System.out.println("Метод - countSizeFilesRecursion");
                countSizeFilesRecursion.printFiles();
                System.out.println("Метод -  countSizeFilesQueue");
                countSizeFilesQueue.printFiles();
            } while (!path.equalsIgnoreCase("exit"));
        }
    }
}
