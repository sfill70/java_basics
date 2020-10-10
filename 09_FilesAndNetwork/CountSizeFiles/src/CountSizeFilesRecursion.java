import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;

public class CountSizeFilesRecursion implements CountSizeFiles {
    protected int countFile = 0;
    protected int countDir = 0;
    protected double sizeFiles = 0;
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

    public void zeroingVariable() {
        countDir = 0;
        countFile = 0;
        sizeFiles = 0;
    }

    public void print() {
        System.out.printf("Размер директории - %s байт  Файлов - %s  Директорий - %s", FORMAT.format(sizeFiles), countFile, countDir);
    }
}

