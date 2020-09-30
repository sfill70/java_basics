import java.io.File;
import java.text.NumberFormat;
import java.util.*;

public class CountSizeFilesQueue extends AbstractCountSizeFiles {
    private final static Locale LOCALE = new Locale("en");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);

    @Override
    public void countSizeFiles(File root) throws NullPointerException {
        Queue<File> queueFiles = new PriorityQueue<File>();
        queueFiles.add(root);
        while (!queueFiles.isEmpty()) {
            File file = queueFiles.poll();
            if (file.isDirectory()) {
                countDir++;
                if (file.listFiles() != null) {
                    queueFiles.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
                }

            } else if (file.isFile()) {
                sizeFiles = sizeFiles + file.length();
                countFile++;
            }
        }
    }

    public void print() {
        System.out.printf("Размер директории - %s Мбайт  Файлов - %s  Директорий - %s",
                FORMAT.format(sizeFiles / (1024 * 1024)), countFile, countDir - 1);
    }
}

