import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

public class CountSizeFilesRecursion extends AbstractCountSizeFiles {
    private final static Locale LOCALE = new Locale("ru");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);

    @Override
    public void countSizeFiles(File file) throws NullPointerException {
        File[] arrayFiles = file.listFiles();
        if (arrayFiles == null) {
            return;
        }
        try {
            for (File f : arrayFiles) {
                if (f.isDirectory()) {
                    countSizeFiles(f);
                    countDir++;
                } else if (f.isFile()) {
                    sizeFiles = sizeFiles + f.length();
                    countFile++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.printf("Размер директории - %s байт  Файлов - %s  Директорий - %s", FORMAT.format(sizeFiles), countFile, countDir);
    }
}

