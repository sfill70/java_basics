import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    public static void main(String[] args) {
        try {
            runCountSizeFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*CountSizeFilesRecursion countSizeFilesRecursion = new CountSizeFilesRecursion();
        countSizeFilesRecursion.printFiles(System.getProperty("user.dir"));
        // Не вызывает исключентй
        CountSizeFilesQueue countSizeFilesQueue = new CountSizeFilesQueue();
        countSizeFilesQueue.printFiles(System.getProperty("user.dir"));*/

    }

    static void runCountSizeFiles() throws IOException {
        String path;
        CountSizeFilesRecursion countSizeFilesRecursion = new CountSizeFilesRecursion();
        CountSizeFilesQueue countSizeFilesQueue = new CountSizeFilesQueue();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.println("путь к директории " +
                        "Для выхода введите - exit");
                path = reader.readLine();
                if (path.equalsIgnoreCase("exit")) {
                    continue;
                }
                System.out.println("Метод - countSizeFilesRecursion");
                countSizeFilesRecursion.printFiles(path);
                System.out.println("Метод -  countSizeFilesQueue");
                countSizeFilesQueue.printFiles(path);
            } while (!path.equalsIgnoreCase("exit"));
        }
    }
}
