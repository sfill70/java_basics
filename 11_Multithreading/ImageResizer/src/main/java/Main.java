import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Main {
    private static final String IMAGES_PATH = String.join(File.separator, System.getProperty("user.dir"),/*"11_Multithreading", "ImageResizer",*/ "images");
    private static int numberCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        try {
            String srcFolder = IMAGES_PATH + File.separator + "src";
            System.out.println(srcFolder);
            String dstFolder = IMAGES_PATH + File.separator + "dst";
            File dstDir = new File(dstFolder);
            File[] filesDst =dstDir.listFiles();
            if(filesDst.length > 0){
                for (File file :filesDst) {
                    file.delete();
                }
            }
            long start = System.currentTimeMillis();
            List<Thread> threadList = new ArrayList<>();
            File srcDir = new File(srcFolder);
            File[] files = srcDir.listFiles();
            System.out.println("Количество файлов: " + files.length);
            ConcurrentLinkedQueue<File> queueQ = new ConcurrentLinkedQueue<>(Arrays.asList(files));
            System.out.println("Просто очередь");
            for (int i = 0; i < numberCores; i++) {
                ImageResizeQeque imageResize = new ImageResizeQeque(dstFolder, queueQ);
                Thread thread =  new Thread(imageResize);
                thread.start();
                threadList.add(thread);
                Thread.sleep(5);

            }
            threadJoin(threadList);
            System.out.println(Thread.currentThread().getName() + " - Duration: :" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String srcFolder = IMAGES_PATH + File.separator + "src";
            System.out.println(srcFolder);
            String dstFolder = IMAGES_PATH + File.separator + "dst";
            File dstDir = new File(dstFolder);
            File[] filesDst =dstDir.listFiles();
            if(filesDst.length > 0){
                for (File file :filesDst) {
                    file.delete();
                }
            }
            long start = System.currentTimeMillis();
            List<Thread> threadList = new ArrayList<>();
            File srcDir = new File(srcFolder);
            File[] files = srcDir.listFiles();
            System.out.println("Количество файлов: " + files.length);
            List<File> list = Arrays.asList(files);
            list.sort((File o1, File o2) -> {
                return Long.compare(o1.length(), o2.length());
            });
            ConcurrentLinkedDeque<File> queue = new ConcurrentLinkedDeque(list);
            System.out.println("Двухсторонняя очередь");

            for (int i = 0; i < numberCores; i++) {
                ImageResize imageResize = new ImageResize(dstFolder, queue);
                Thread thread =  new Thread(imageResize);
                thread.start();
                threadList.add(thread);

            }
            threadJoin(threadList);
            System.out.println(Thread.currentThread().getName() + " - Duration: :" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }


/*        try {
            String srcFolder = IMAGES_PATH + File.separator + "src";
            System.out.println(srcFolder);
            String dstFolder = IMAGES_PATH + File.separator + "dst";
            File dstDir = new File(dstFolder);
            File[] filesDst =dstDir.listFiles();
            if(filesDst.length > 0){
                for (File file :filesDst) {
                    file.delete();
                }
            }
            long start = System.currentTimeMillis();
            List<Thread> threadList = new ArrayList<>();
            File srcDir = new File(srcFolder);
            File[] files = srcDir.listFiles();
            System.out.println("Количество файлов: " + files.length);
            ConcurrentLinkedQueue<File> queueQ = new ConcurrentLinkedQueue<>(Arrays.asList(files));
            System.out.println("Просто очередь");
            for (int i = 0; i < numberCores; i++) {
                ImageResizeQeque imageResize = new ImageResizeQeque(dstFolder, queueQ);
                Thread thread =  new Thread(imageResize);
                threadList.add(thread);
                thread.start();
            }
            threadJoin(threadList);
            System.out.println(Thread.currentThread().getName() + " - Duration: :" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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
