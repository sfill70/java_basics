import java.io.File;
import java.text.NumberFormat;
import java.util.*;

public class CountSizeFilesQueue implements CountSizeFiles  {
    private final static Locale LOCALE = new Locale("en");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    protected int countFile = 0;
    protected int countDir = 0;
    protected double sizeFiles = 0;

    @Override
    public void countSizeFiles(File root) throws NullPointerException {
        List<String>list = new ArrayList<>();
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
                list.add(file.getAbsolutePath());
                sizeFiles = sizeFiles + file.length();
                countFile++;
            }
        }
        System.out.println(list);
    }

    public double getSizeFiles() {
        return sizeFiles;
    }

    public void zeroingVariable() {
        countDir = 0;
        countFile = 0;
        sizeFiles = 0;
    }

    public void print() {
        System.out.printf("Размер директории - %s Мбайт  Файлов - %s  Директорий - %s",
                FORMAT.format(sizeFiles / (1024 * 1024)), countFile, countDir - 1);
    }
}

