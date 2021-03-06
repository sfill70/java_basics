import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;
import SizeDirectory.*;


public class Main {
    private final static Locale LOCALE = new Locale("en");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    public static void main(String[] args) {
        CountSizeFilesQueue countSizeFiles = new CountSizeFilesQueue();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String pathFrom = takeDirectory(reader, "Введите копируемую дерикторию", false);
                if (pathFrom.equalsIgnoreCase("exit")) {
                    break;
                }

                String pathWhere = takeDirectory(reader, "Введите директорию куда копировать", true);
                if (pathWhere.equalsIgnoreCase("exit")) {
                    break;
                }
                Number[] arrayFrom = countSizeFiles.getSizeFiles(pathFrom);
                CopingFullDirectoryNio.copyingDirectoryFull(pathFrom, pathWhere);
                Number[] arrayWhere = countSizeFiles.getSizeFiles(pathWhere);
                if (arrayFrom[0].equals(arrayWhere[0])) {
                    System.out.println("Копирование успешно завершено, скопировано - " + FORMAT.format(arrayFrom[0]) + "Кбайт");
                }
                countSizeFiles.printFiles(pathWhere);
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }


    private static String takeDirectory(BufferedReader reader, String message, boolean makeDir) throws IOException {
        String path = "";
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
            System.out.println(path);
            //Прверяем наличие директории если заднно создание - создаем если возможно
            path = UtilCopingDir.checkDirectory(path, makeDir);
            if (path.length() > 0) {
                break;
            }
        }
        return path;
    }
}
