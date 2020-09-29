import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class CountSizeFilesQueue {
    int countFile = 0;
    int countDir = 0;
    double sizeFiles = 0;
    private final static Locale LOCALE = new Locale("en");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    List<File> fileList;

    public CountSizeFilesQueue() {
    }

    //Здась нужно void, но менять не стал.
    public List<File> getFileTree(File root) throws NullPointerException {
        List<File> listFileTree = new ArrayList<>();
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
                listFileTree.add(file);
                sizeFiles = sizeFiles + file.length();
                countFile++;
            }
        }
        return listFileTree;
    }

    public void printFiles(String path) throws NullPointerException {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("Директории не существует");
            return;
        }
        getFileTree(file);
        System.out.printf("Размер директории - %s Мбайт  Файлов - %s  Директорий - %s", FORMAT.format(sizeFiles / (1024 * 1024)), countFile, countDir - 1);
        System.out.println();
        countDir = 0;
        countFile = 0;
        sizeFiles = 0;
    }
}

