import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CopingFullDirectoryNio copingFullDirectoryNio = new CopingFullDirectoryNio();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String pathFrom = takeDirectory(reader, "Введите копируемую дерикторию", false);
                if (pathFrom.equalsIgnoreCase("exit")) {
                    break;
                }
                List<Path> pathList = TakeTreeFiles.takeFilesList(new File(pathFrom));

                String pathWhere = takeDirectory(reader, "Введите директорию куда копировать", true);
                if (pathWhere.equalsIgnoreCase("exit")) {
                    break;
                }
                copingFullDirectoryNio.copyingDirectory(pathFrom, pathWhere, pathList);
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
