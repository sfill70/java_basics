import java.io.*;
import java.util.*;

public class CopingFullDirectoryQueue {

    private static final int BUFFER_SIZE = 1024/* * 1024*/;
    private int count = 0;

   protected void copyingDirectory(String fromPath, String wherePath) throws IOException {
        System.out.println("Идет копирование файлов");
        File from = new File(fromPath);
        File where = new File(wherePath);
        String copyPath;
        PriorityQueue<File> queueFiles = new PriorityQueue<>();
        queueFiles.add(from);
        while (!queueFiles.isEmpty()) {
            printPoint();
            //Защита от циклического копирования
            if (queueFiles.remove(where)) {
//                System.out.println("!!!!!!!!!! Удалил");
            }
            File file = queueFiles.poll();
            //Защита от циклического копирования на всякий случай
            if (file != null && (file.getAbsolutePath().equalsIgnoreCase(where.getAbsolutePath()) ||
                    file.getAbsolutePath().toLowerCase().startsWith(where.getAbsolutePath().toLowerCase()))) {
//                System.out.println("YES " + file.getAbsolutePath() + " " + where.getAbsolutePath());
                continue;
            }
            if (file != null && file.isDirectory()) {
                if (file.listFiles() != null) {
                    queueFiles.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
                    //Путь для директории
                    copyPath = UtilCopingDir.copyPath(fromPath, wherePath, file.getPath());
                    //Создать директорию
                    new File(copyPath).mkdir();
                }
            } else if (file != null && file.isFile()) {
                //Путь для файла
                copyPath = UtilCopingDir.copyPath(fromPath, wherePath, file.getPath());
                //Скопировать файл
                copyingFile(file, new File(copyPath));
            }
        }
        System.out.println();
    }

    private void printPoint() {
        if (count % 50 == 0) {
            String point = ".";
            System.out.print(point);
        }
        count++;
        if (count % 10000 == 0) {
            System.out.println();
        }
    }

    private static void copyingFile(File original, File copied)
            throws IOException {
        try (InputStream in = new BufferedInputStream(
                new FileInputStream(original));
             OutputStream out = new BufferedOutputStream(
                     new FileOutputStream(copied))) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }
}
