import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

public class CountSizeFilesRecursion {
    int countFile = 0;
    int countDir = 0;
    long sizeFiles = 0;
    private final static Locale LOCALE = new Locale("ru");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
//    File file;

    public CountSizeFilesRecursion() {
        /*this.file = new File(path);
        countSizeFiles(file);*/
    }

    private void countSizeFiles(File file) {
        File[] arrayFiles = file.listFiles();
        if (arrayFiles == null) {
            System.out.println("Директории не существует");
            return;
        }
        try {
            for (File f : arrayFiles) {
                if (f.isDirectory()) {
                    countSizeFiles(f);
                    countDir++;
                } else {
                    sizeFiles = sizeFiles + f.length();
                    countFile++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printFiles(String path) {
        File file = new File(path);
        countSizeFiles(file);
        System.out.printf("Размер директории - " + FORMAT.format(sizeFiles)
                + " байт  Файлов - %s  Директорий - %s", countFile, countDir);
        System.out.println();
        countDir = 0;
        countFile = 0;
        sizeFiles = 0;
    }

    private static String parseDir(String st) {
        StringBuilder sb = new StringBuilder();
        String[] arrayPath = st.trim().split("\\|//|/|\\\\");
        for (int i = 0; i < arrayPath.length; i++) {
            if (i == arrayPath.length - 1) {
                sb.append(arrayPath[i]);
                continue;
            }
            sb.append(arrayPath[i]);
            sb.append(System.getProperty("file.separator"));
        }
        return sb.toString();
    }
}

