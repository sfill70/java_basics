import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;

public class FileDownload {
    private static final Logger LOGGER = LogManager.getLogger(FileDownload.class);

    protected static void downloadFileNio(String url, String pathFile) {
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(pathFile, true)) {
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            LOGGER.error(e + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }


    protected static void downloadFile(String url, String pathFile) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(pathFile, true)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            LOGGER.error(e + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }


    protected static long fileSize(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("HEAD");
        return httpConnection.getContentLengthLong();
    }
}
