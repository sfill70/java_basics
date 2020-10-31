import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class ImageResize implements Runnable {
    private File[] files;
    private String dstFolder;

    public ImageResize(File[] files, String dstFolder) {
        this.files = files;
        this.dstFolder = dstFolder;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );
                BufferedImage newImage = resize(image, newWidth, newHeight);
                writeFiles(file, newImage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

    private void writeFiles(File file, BufferedImage newImage) throws IOException {
        File newFile = new File(dstFolder + File.separator + file.getName());
        ImageIO.write(newImage, "jpg", newFile);
        if (!newFile.exists()) {
            System.out.println(newFile);
        }
    }

    private BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
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
