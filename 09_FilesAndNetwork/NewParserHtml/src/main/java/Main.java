import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private static final String REFERRER = "https://www.google.com/";
    static String root = System.getProperty("user.dir");
    static String dirSeparator = System.getProperty("file.separator");
    private static final String IMAGES_PATH = root + dirSeparator + "NewParserHtml" + dirSeparator + "images" + dirSeparator;
    private static final String REGEX_FILTER = ".+[.]png|.+[.]jpg|.+[.]tif|.+[.]jpeg";
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String SOURCE_URL = "https://lenta.ru/";
    private static final Marker INVALID_LINE_MARKER = MarkerManager.getMarker("VIEW_INVALID");
    private static final Marker VIEW_FILEPATH_MARKER = MarkerManager.getMarker("VIEW_FILEPATH");

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

        Elements listSrc = doc.select("[src]");

        long start = System.currentTimeMillis();
        AtomicReference<String> url1 = new AtomicReference<>("");
        listSrc.stream().filter(e -> e.normalName().equals("img"))
                .map(e -> e.attr("abs:src"))
                .map(e -> {
                    url1.set(e);
                    return e.substring(e.lastIndexOf("/") + 1);
                })
                .filter(e -> e.matches(REGEX_FILTER))
                .forEach(e -> {
                    FileDownload.downloadFileNio(url1.get(), IMAGES_PATH + e);
                    System.out.println(e);
                });
        long finish = System.currentTimeMillis();
        System.out.printf("Execution spend %d,%d sec & %d mb of memory." + System.lineSeparator(),
                ((finish - start) / 1000), ((finish - start) % 1000), (Runtime.getRuntime().totalMemory()
                        - Runtime.getRuntime().freeMemory()) / 1024 / 1024);

        long start1 = System.currentTimeMillis();

        for (Element src : listSrc
        ) {
            if (src.normalName().equals("img")) {
                String url = src.attr("abs:src");
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                if (fileName.matches(REGEX_FILTER)) {
                    String pathFile = IMAGES_PATH + fileName;
                    LOGGER.info(VIEW_FILEPATH_MARKER, pathFile);
                    FileDownload.downloadFile(url, pathFile);
                    System.out.println(fileName);
                }
            } else {
                LOGGER.warn(INVALID_LINE_MARKER, src);
            }
        }
        long finish1 = System.currentTimeMillis();
        System.out.printf("Execution spend %d,%d sec & %d mb of memory.",
                ((finish1 - start1) / 1000), ((finish1 - start1) % 1000), (Runtime.getRuntime().totalMemory()
                        - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
    }
}
