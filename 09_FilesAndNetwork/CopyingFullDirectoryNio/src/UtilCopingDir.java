import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class UtilCopingDir {

    //Переменная для создания папки в родительской директории копируемой папки если введено только имя папки
    private static String pathParentFrom = "";

    //Приводим данные к единому формату проверить Linux
    protected static String parseDir(String st) {
        String separator = File.separator;
        String os = System.getProperty("os.name").toLowerCase();
        StringBuilder sb = new StringBuilder();
        String[] arrayPath = st.trim().split("\\|//|/|\\\\");
        if (!os.startsWith("windows") && !String.valueOf(st.charAt(0)).equals(separator)) {
            sb.append(System.getProperty("file.separator"));
        }
        for (String s : arrayPath) {
            sb.append(s);
            sb.append(System.getProperty("file.separator"));
        }
        return sb.toString();
    }


    //Прверяем наличие директории, если заднно создание - создаем если возможно и возвращаем путь
    protected static String checkDirectory(String pathDirectory, Boolean makeDirectory) {
        File file = new File(pathDirectory);
        if (file.getAbsolutePath().startsWith(System.getProperty("user.dir"))) {
            System.out.println(pathParentFrom);
            if (pathParentFrom != null && pathParentFrom.length() > 0) {
                System.out.println(pathParentFrom);
                System.out.println(pathDirectory);
                pathDirectory = pathParentFrom + pathDirectory;
                file = new File(pathDirectory);
                System.out.println(pathDirectory);
            } else {
                System.out.println(pathDirectory + " Директории нет существует");
                pathParentFrom = "";
                return "";
            }
        }
        if (!file.exists() && !file.isDirectory()) {
            if (makeDirectory) {
                if (file.mkdirs()) {
                    System.out.println(file.getAbsolutePath() + " Директория создана");
                    pathParentFrom = "";
                    return pathDirectory;
                } else {
                    System.out.println(pathDirectory + " Директория не может быть создана");
                    pathParentFrom = "";
                    return "";
                }
            } else {
                System.out.println("Директории не существует");
                pathParentFrom = "";
                return "";
            }
        }
        pathParentFrom = file.getParent();
        return pathDirectory;
    }

    /*protected static String checkDirectoryNio(String pathDirectory, Boolean makeDirectory) {
        Path path = Paths.get(pathDirectory);
        if (!Files.exists(path) && !Files.isDirectory(path)) {
            if (makeDirectory) {
                try {
                    Files.createDirectories(path);
                    if (path.toAbsolutePath().startsWith(System.getProperty("user.dir"))){
                        System.out.println(path.toAbsolutePath() + " !!!!!!");
                    }
                    System.out.println(pathDirectory + " Директория создана");
                } catch (IOException e) {
                    System.out.println(pathDirectory + " Директория не может быть создана");
                    return "";
                }
            } else {
                System.out.println("Директории не существует");
                return "";
            }
        }
        return path.toString();
    }*/
}