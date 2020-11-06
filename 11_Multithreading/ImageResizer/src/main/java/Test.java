import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Test {
    private static final String IMAGES_PATH = String.join(File.separator, System.getProperty("user.dir"),/*"11_Multithreading", "ImageResizer",*/ "images");
    private static int numberCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        try {
            String srcFolder = IMAGES_PATH + File.separator + "src";
            System.out.println(srcFolder);
            String dstFolder = IMAGES_PATH + File.separator + "dst";
            File srcDir = new File(srcFolder);
            File[] files = srcDir.listFiles();
            if (args.length > 0) {
                System.out.println("Количество файлов: " + files.length);
                if (args[0].equalsIgnoreCase("qeque")) {
                    imageResizeQeque(dstFolder, files);
                }
                if (args[0].equalsIgnoreCase("deque")) {
                    imageResizeDeque(dstFolder, files);
                }
            } else {
                imageResizeQeque(dstFolder, files);
                Thread.sleep(100);
                imageResizeDeque(dstFolder, files);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void imageResizeDeque(String dstFolder, File[] files) throws InterruptedException {
        List<File> list = Arrays.asList(files);
        list.sort((File o1, File o2) -> {
            return Long.compare(o1.length(), o2.length());
        });
        ConcurrentLinkedDeque<File> queue = new ConcurrentLinkedDeque(list);
        List<Thread> threadList = new ArrayList<>();
        File dstDir = deleteFiles(dstFolder);
        long start = System.currentTimeMillis();
        System.out.println("Двухсторонняя очередь");
        for (int i = 0; i < numberCores; i++) {
            Thread.sleep(20);
            ImageResize imageResize = new ImageResize(dstFolder, queue);
            Thread thread = new Thread(imageResize);
            thread.start();
            threadList.add(thread);
        }
        threadJoin(threadList);
        System.out.println(Thread.currentThread().getName() + " - Duration: :" + (System.currentTimeMillis() - start));
        printCountFiles(dstDir);
    }

    private static void imageResizeQeque(String dstFolder, File[] files) throws InterruptedException {
        ConcurrentLinkedQueue<File> queueQ = new ConcurrentLinkedQueue<>(Arrays.asList(files));
        List<Thread> threadList = new ArrayList<>();
        long start = System.currentTimeMillis();
        File dstDir = deleteFiles(dstFolder);
        System.out.println("Просто очередь");
        for (int i = 0; i < numberCores; i++) {
            Thread.sleep(20);
            ImageResizeQeque imageResize = new ImageResizeQeque(dstFolder, queueQ);
            Thread thread = new Thread(imageResize);
            thread.start();
            threadList.add(thread);
        }
        threadJoin(threadList);
        System.out.println(Thread.currentThread().getName() + " - Duration: :" + (System.currentTimeMillis() - start));
        printCountFiles(dstDir);
    }

    private static void printCountFiles(File dstDir) {
        File[] filesDstEnd = dstDir.listFiles();
        if (filesDstEnd != null) {
            System.out.println("Переработано - " + filesDstEnd.length + " файлов");
        }
    }

    private static File deleteFiles(String dstFolder) {
        File dstDir = new File(dstFolder);
        File[] filesDst = dstDir.listFiles();
        if (filesDst.length > 0) {
            for (File file : filesDst) {
                file.delete();
            }
        }
        return dstDir;
    }

    private static void threadJoin(List<Thread> threadList) {
        for (Thread tr : threadList
        ) {
            try {
                tr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
