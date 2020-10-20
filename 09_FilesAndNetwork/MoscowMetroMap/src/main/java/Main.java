import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.util.*;


public class Main {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private static final String REFERRER = "https://www.google.com/";
    private static final String SOURCE_URL = "https://www.moscowmap.ru/metro.html#lines";
    //    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static String root = System.getProperty("user.dir");
    private static final String DATA_PATH = String.join(File.separator, root, "src", "main", "resources");
    private static final String PATH_JSON_FILE = String.join(File.separator, DATA_PATH, "metro.json");
    private static List<Line> listLine = new LinkedList<>();
    private static Set<Connection> connectionSet = new HashSet<>();

    public static void main(String[] args) {
        Document doc = null;
        try {
            /*File file = new File(DATA_PATH + File.separator + "metro.html");
            doc = Jsoup.parse(file, "utf-8");*/
             doc = Jsoup.connect(SOURCE_URL ).
                    userAgent(USER_AGENT).
                    referrer(REFERRER).get();
        } catch (Exception e) {
            System.exit(1);
        }

        Element target = doc.getElementById("metrodata");

        try {
            parseMoscowMetro(target);
            JsonWriteUtil.writeDataJson(listLine, connectionSet, PATH_JSON_FILE);
            JsonReadUtil.printJson(PATH_JSON_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseMoscowMetro(Element target) {
        Elements listElements = target.select("div.js-toggle-depend.s-depend-control-single");
        for (Element element : listElements
        ) {
            String numberLine = element.child(0).attr("data-line");
            String nameLine = element.text().trim();
            Line line = new Line(numberLine.trim(), nameLine);
            listLine.add(line);
            for (Element el : element.nextElementSibling().select("div.js-metro-stations").select("p")
            ) {
                String nameSt = el.child(0).child(1).text().trim();
                Station station = new Station(nameSt, numberLine.trim());
                line.addStation(station);
                if (el.child(0).childrenSize() > 2) {
                    Connection connection = new Connection();
                    connection.addStationSet(station);
                    for (int i = 2; i < el.child(0).childrenSize(); i++) {
                        nameSt = el.child(0).child(i).attr("title").trim().split("[«»]")[1].trim();
                        String lineNumber = el.child(0).child(i).attr("class").split("-")[3].trim();
                        connection.addStationSet(new Station(nameSt, lineNumber));
                    }
                    fillingConnectionSet(connection);
                }
            }
        }
    }

    private static void fillingConnectionSet(Connection connection) {
        boolean canAdd = true;
        for (Connection c : connectionSet) {
            if (!Collections.disjoint(c.getStationSet(), connection.getStationSet())) {
                c.getStationSet().addAll(connection.getStationSet());
                canAdd = false;
            }
        }
        if (canAdd) {
            connectionSet.add(connection);
        }
    }
}


