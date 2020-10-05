import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TakeTreeFiles {
    static int countFile = 0;
    static int countDir = 0;
    private final static Locale LOCALE = new Locale("ru");
    static long sizeFiles = 0;

    public static List<Path> takeFilesList(File root) throws NullPointerException {
        List<Path>list = new ArrayList<>();
        Queue<File> queueFiles = new PriorityQueue<File>();
        queueFiles.add(root);
        while (!queueFiles.isEmpty()) {
            File file = queueFiles.poll();
            if (file.isDirectory()) {
                list.add(Paths.get(file.getAbsolutePath()));
                countDir++;
                if (file.listFiles() != null) {
                    queueFiles.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
                }

            } else if (file.isFile()) {
                list.add(Paths.get(file.getAbsolutePath()));
                sizeFiles = sizeFiles + file.length();
                countFile++;
            }
        }
        return list;
    }
}
