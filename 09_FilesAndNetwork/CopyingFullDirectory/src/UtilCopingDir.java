import java.io.*;
public class UtilCopingDir {

    //Переменная для создания папки в родительской директории копируемой папки если введено только имя папки
    private static String pathParentFrom = "";

    //Приводим данные к единому формату проверить Linux
    protected static String parseDir(String st) {
        String os = System.getProperty("os.name").toLowerCase();
        StringBuilder sb = new StringBuilder();
        String[] arrayPath = st.trim().split("\\|//|/|\\\\");
        if (!os.startsWith("windows")) {
            sb.append(System.getProperty("file.separator"));
        }
        for (String s : arrayPath) {
            sb.append(s);
            sb.append(System.getProperty("file.separator"));
        }
        return sb.toString();
    }

    protected static String copyPath(String fromPath, String wherePath, String filePath) {
        return filePath.replace(fromPath, wherePath);
    }

    //Прверяем наличие директории, если заднно создание - создаем если возможно усли введено толко имя
    // создем в текущей директори кроме случая если копируется диск и возвращаем путь
    protected static String checkDirectory(String pathDirectory, Boolean makeDirectory) {
        File file = new File(pathDirectory);
        if (file.getAbsolutePath().startsWith(System.getProperty("user.dir"))) {
            if (pathParentFrom != null && pathParentFrom.length() > 0) {
                System.out.println(pathDirectory);
                pathDirectory = pathParentFrom + pathDirectory;
                file = new File(pathDirectory);
                System.out.println(pathDirectory);
            } else {
                System.out.println(pathDirectory + "Директории не существует или не может быть создана (при копировании раздела)");
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
}