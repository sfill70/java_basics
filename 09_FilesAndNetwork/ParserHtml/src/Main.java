import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private static final String REFERRER = "https://www.google.com/";
    static String root = System.getProperty("user.dir");
    static String dirSeparator = System.getProperty("file.separator");
//    private static final String IMAGES_PATH = root + dirSeparator + "ParserHtml" + dirSeparator + "images" + dirSeparator;
    private static final String IMAGES_PATH = String.join(File.separator,  root, "ParserHtml", "images");
    private static final String REGEX_FILTER = ".+[.]png|.+[.]jpg|.+[.]tif|.+[.]jpeg";
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String SOURCE_URL = "https://lenta.ru/";
    private static final Marker INVALID_LINE_MARKER = MarkerManager.getMarker("VIEW_INVALID");
    private static final Marker VIEW_FILEPATH_MARKER = MarkerManager.getMarker("VIEW_FILEPATH");
    private static String pathFile;

    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect(SOURCE_URL).
                    userAgent(USER_AGENT).
                    referrer(REFERRER).get();
        } catch (IOException e) {
            LOGGER.error(e + Arrays.toString(e.getStackTrace()));
            System.exit(1);
        }

        doc.select("[src]").stream()
                .filter(tag -> tag.normalName().equals("img"))
                .map(tag -> tag.attr("abs:src"))
                .filter(url -> url.matches(REGEX_FILTER))
                .peek(LOGGER::debug)
                .forEach(url ->  FileDownload.downloadFileNio(url, FileDownload.getDirDownload(url, IMAGES_PATH)));



       /* Elements listSrc = doc.select("[src]");
        for (Element src : listSrc
        ) {
            if (src.normalName().equals("img")) {
                String url = src.attr("abs:src");
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                if (fileName.matches(REGEX_FILTER)) {
                    pathFile = IMAGES_PATH + fileName;
                    LOGGER.info(VIEW_FILEPATH_MARKER, pathFile);
                    FileDownload.downloadFile(url, pathFile);
                System.out.println(fileName);
                }
            } else {
                LOGGER.warn(INVALID_LINE_MARKER, src);
            }
        }*/
    }
}
