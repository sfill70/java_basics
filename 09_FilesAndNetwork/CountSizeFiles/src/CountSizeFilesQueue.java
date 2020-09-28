import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class CountSizeFilesQueue {
    int countFile = 0;
    int countDir = 0;
    long sizeFiles = 0;
    private final static Locale LOCALE = new Locale("ru");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    File file;
    List<File> fileList;

    public CountSizeFilesQueue(String path){
        this.file = new File(path);
        //В теории исключений не вызывает
        try {
            fileList = getFileTree(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Здась нужно void, но менять не стал.
    public List<File> getFileTree(File root){
        List<File> listFileTree = new ArrayList<>();
        Queue<File> queueFiles = new PriorityQueue<File>();
        queueFiles.add(root);
        while (!queueFiles.isEmpty()) {
            File file = queueFiles.poll();
            if (file.isDirectory()) {
                countDir++;
                for (File f : file.listFiles()) {
                    queueFiles.add(f);
                }
            }
            else if (file.isFile()) {
                listFileTree.add(file);
                sizeFiles = sizeFiles + file.length();
                countFile++;
            }
        }
        if(listFileTree.size() == 0){
            System.out.println("Нет такой директории");
        }
        return listFileTree;
    }

    public void printFiles() {
        System.out.printf("Размер директории - " + FORMAT.format(sizeFiles)
                + " байт  Файлов - %s  Директорий - %s", countFile, countDir - 1);
        System.out.println();
    }


}
