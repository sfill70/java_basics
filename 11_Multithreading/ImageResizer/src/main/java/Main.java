import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;


public class Main {
    private static final String IMAGES_PATH = String.join(File.separator, System.getProperty("user.dir"),/*"11_Multithreading", "ImageResizer",*/ "images");
    private static int numberCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        try {
            String srcFolder = IMAGES_PATH + File.separator + "src";
            System.out.println(srcFolder);
            String dstFolder = IMAGES_PATH + File.separator + "dst";
            File srcDir = new File(srcFolder);
            File[] files = srcDir.listFiles();
            System.out.println("Количество файлов: " + files.length);
            List<File> list = Arrays.asList(files);
            list.sort((File o1, File o2) -> {
                return Long.compare(o1.length(), o2.length());
            });
            ConcurrentLinkedDeque<File> queue = new ConcurrentLinkedDeque(list);
            System.out.println("Отсортированы по размеру");
            queue.forEach(e -> System.out.print(e.length() + ", "));

            for (int i = 0; i < numberCores; i++) {
                ImageResize imageResize = new ImageResize(dstFolder, queue);
                new Thread(imageResize).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
