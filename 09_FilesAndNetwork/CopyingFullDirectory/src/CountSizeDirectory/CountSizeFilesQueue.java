package CountSizeDirectory;

import java.io.File;
import java.text.NumberFormat;
import java.util.*;

public class CountSizeFilesQueue {
    private final static Locale LOCALE = new Locale("en");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    protected int countFile = 0;
    protected int countDir = 0;
    protected double sizeFiles = 0;


    public Number[] countSizeFiles(File root) throws NullPointerException {
        List<String> list = new ArrayList<>();
        Queue<File> queueFiles = new PriorityQueue<File>();
        queueFiles.add(root);
        while (!queueFiles.isEmpty()) {
            File file = queueFiles.poll();
            if (file.isDirectory()) {
//                list.add(file.getAbsolutePath());
                countDir++;
                if (file.listFiles() != null) {
                    queueFiles.addAll(Arrays.asList(Objects.requireNonNull(file.listFiles())));
                }

            } else if (file.isFile()) {
//                list.add(file.getAbsolutePath());
                sizeFiles = sizeFiles + file.length();
                countFile++;
            }
        }
        return new Number[]{sizeFiles / 1024, countFile, countDir - 1};
//        System.out.println(list);
    }

    public Number[] getSizeFiles(String path) {
        path = parseDir(path);
        File file = new File(path);
        Number[] array = countSizeFiles(file);
        zeroingVariable();
        return array;
    }

    public void printFiles(String path) {
        path = parseDir(path);
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("Директории не существует");
            return;
        }
        countSizeFiles(file);
        print();
        System.out.println();
        zeroingVariable();
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

    static String parseDir(String st) {
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
}

