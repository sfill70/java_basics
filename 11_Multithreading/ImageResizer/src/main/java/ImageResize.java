import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ImageResize implements Runnable {
    private String dstFolder;
    ConcurrentLinkedDeque<File> queue;
    int newWidth = 60;
    public ImageResize(String dstFolder, ConcurrentLinkedDeque<File> queue) {
        this.dstFolder = dstFolder;
        this.queue = queue;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        boolean isSwitch = true;
        while (!queue.isEmpty()) {
            try {
                File file = null;
                if (isSwitch) {
                    file = queue.pollFirst();
                } else {
                    file = queue.pollLast();
                }
                isSwitch = !isSwitch;
                if (file == null) {
                    continue;
                }
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage newImage = resize(image, newWidth);
                writeFiles(file, newImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - Duration: " + (System.currentTimeMillis() - start));
    }

    private void writeFiles(File file, BufferedImage newImage) throws IOException {
        File newFile = new File(dstFolder + File.separator + file.getName());
        ImageIO.write(newImage, "jpg", newFile);
        if (!newFile.exists()) {
            System.out.println(newFile);
        }
    }

    private BufferedImage resize(BufferedImage image, int newWidth) {
        int newHeight = (int) Math.round(
                image.getHeight() / (image.getWidth() / (double) newWidth)
        );
        BufferedImage newImage = new BufferedImage(
                newWidth, newHeight, BufferedImage.TYPE_INT_RGB
        );
        int widthStep = image.getWidth() / newWidth;
        int heightStep = image.getHeight() / newHeight;
        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                int rgb = image.getRGB(x * widthStep, y * heightStep);
                newImage.setRGB(x, y, rgb);
            }
        }
        return newImage;
    }
}
