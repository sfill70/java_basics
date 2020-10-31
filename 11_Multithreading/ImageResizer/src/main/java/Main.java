import java.io.File;


public class Main {
    private static final String IMAGES_PATH = String.join(File.separator, System.getProperty("user.dir"), "ImageResizer", "images");
    private static int numberCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        try {
            String srcFolder = IMAGES_PATH + File.separator + "src";
            String dstFolder = IMAGES_PATH + File.separator + "dst";
            File srcDir = new File(srcFolder);
            File[] files = srcDir.listFiles();
            if (files.length == 0){
                System.out.println("Файлов нет");
                return;
            }
            int splitSize = files.length / numberCores + 1;
            int tmpPos = 0;
            for (int i = 0; i < numberCores; i++) {
                tmpPos = splitSize * i;
                if (tmpPos + splitSize >= files.length) {
                    splitSize = files.length - tmpPos;
                }
                File[] splitFiles = new File[splitSize];
                System.arraycopy(files, tmpPos, splitFiles, 0, splitFiles.length);
                ImageResize imageResize = new ImageResize(splitFiles, dstFolder);
                new Thread(imageResize).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
