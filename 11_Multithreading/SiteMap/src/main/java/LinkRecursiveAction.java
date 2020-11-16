import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;


public class LinkRecursiveAction extends RecursiveAction {
    private String baseLink;
    private String t;
    static String baseUrl = "https://lenta.ru/";
    //    static String baseUrl = "https://skillbox.ru/";

    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue(Collections.singletonList(baseUrl));
    private static Set<String> uniqueURL = new ConcurrentSkipListSet<>();
    private List<LinkRecursiveAction> actionList = new CopyOnWriteArrayList<>();
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private static final String REFERRER = "https://www.google.com/";


    private static final Logger LOGGER = LogManager.getLogger(LinkAction.class);
    private static final Marker INVALID_LINE_MARKER = MarkerManager.getMarker("VIEW_INVALID");
    private static final Marker VIEW_FILEPATH_MARKER = MarkerManager.getMarker("VIEW_FILEPATH");

    public LinkRecursiveAction(String baseLink, String t){
        this.baseLink = baseLink;
        this.t = t;

    }

    public static ConcurrentLinkedQueue<String> getQueue() {
        return queue;
    }

    public static Set<String> getUniqueURL() {
        return uniqueURL;
    }

    @Override
    protected void compute() {
        getLinks(baseLink, t);
    }

    private void getLinks(String url, String t) {
        try {
            Elements links = null;
            try {
                links = getElements(url);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (links.isEmpty()) {
                return;
            }
            StringBuffer tab = new StringBuffer(t);
            for (int i = 0; i < url.split("/").length - 2; i++) {
                try {
                    tab.append("\t");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            uniqueURL.add(url);
//            System.out.println(url);
            tab.append("\t");
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (/*href.contains(baseUrl)&&*/href.contains(url)&& !uniqueURL.contains(href) && !href.contains("#") && !href.contains("?")
                         && !href.contains("@")&& !href.endsWith("pdf")&& !href.contains("201")) {
                    uniqueURL.add(href);
                    Thread.sleep(100);
                    System.out.println(tab + href);
                    queue.add(tab + href);
                    LinkRecursiveAction action = new LinkRecursiveAction(href, tab.toString());
                    try {
                        action.fork();
                    } catch (Exception e) {
                            LOGGER.error(action + " fork - " + e + Arrays.toString(e.getStackTrace()));
                        LOGGER.error(INVALID_LINE_MARKER, action);
                        e.printStackTrace();
                    }
                    actionList.add(action);
                }
            }
            try {
                for (LinkRecursiveAction action : actionList)
                    try {
                        action.join();
                    } catch (Exception e) {
                            LOGGER.error(action + " join - " + e + Arrays.toString(e.getStackTrace()));
                        LOGGER.error(VIEW_FILEPATH_MARKER, action);
                        e.printStackTrace();
                    }
            } catch (Exception e) {
                LOGGER.error(VIEW_FILEPATH_MARKER, actionList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Elements getElements(String link) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(link).
                    userAgent(USER_AGENT).
                    referrer(REFERRER).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc.select("a[href]");
    }

}
