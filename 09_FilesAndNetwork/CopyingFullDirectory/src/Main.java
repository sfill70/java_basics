import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;
import CountSizeDirectory.*;

public class Main {
    private final static Locale LOCALE = new Locale("en");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    static String pathFrom;
    static String pathWhere;

    public static void main(String[] args) {

        CountSizeFilesQueue countSizeFiles = new CountSizeFilesQueue();
        CopingFullDirectoryQueue copingFullDirectoryQueue = new CopingFullDirectoryQueue();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                pathFrom = takeDirectory(reader, "Введите копируемую дерикторию", false);
                if (pathFrom.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Копируемая директория - " + pathFrom);
                pathWhere = takeDirectory(reader, "Введите директорию куда копировать", true);
                if (pathWhere.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Файлы будут скопированы в директорию - " + pathWhere);
                Number[] arrayFrom = countSizeFiles.getSizeFiles(pathFrom);
                copingFullDirectoryQueue.copyingDirectory(pathFrom, pathWhere);
                Number[] arrayWhere = countSizeFiles.getSizeFiles(pathWhere);
                if (arrayFrom[0].equals(arrayWhere[0])) {
                    System.out.println("Копирование успешно завершено, скопировано - " + FORMAT.format(arrayFrom[0]) + "Кбайт");
                }
                countSizeFiles.printFiles(pathWhere);
                pathFrom = "";
                pathWhere = "";

            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    private static String takeDirectory(BufferedReader reader, String message, boolean makeDir) throws IOException {
        String path;
        while (true) {
            System.out.println(message +
                    " Для выхода введите - exit");
            path = reader.readLine();
            if (path.length() == 0) {
                System.out.println("Введите данные");
                continue;
            }
            if (path.equalsIgnoreCase("exit")) {
                path = "exit";
                break;
            }
            //Приводим данные к единому формату
            path = UtilCopingDir.parseDir(path);
            if (pathFrom != null) {
                if (pathFrom.equalsIgnoreCase(path)) {
                    System.out.println("Директории совпадают");
                    continue;
                }
            }
            //Прверяем наличие директории если заднно создание - создаем если возможно
            path = UtilCopingDir.checkDirectory(path, makeDir);
            if (path.length() > 0) {
                break;
            }

        }
        return path;
    }

}
