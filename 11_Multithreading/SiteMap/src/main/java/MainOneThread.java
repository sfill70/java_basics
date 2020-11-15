import java.io.File;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;


public class MainOneThread {
    static File mapFile = new File("sitemap_set.txt");
    static File mapFile1 = new File("sitemap_queue.txt");
    static String baseLink = "https://lenta.ru/";
    //    static String baseLink = "https://skillbox.ru/";
    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
    private static Set<String> uniqueURL = new ConcurrentSkipListSet<>();
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private static final String REFERRER = "https://www.google.com/";

    public static void main(String[] args) {
        queue.add(baseLink);
        try {
            getLinks(baseLink);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getLinks(String url) throws IOException, InterruptedException {
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
        StringBuffer tab = new StringBuffer();
        for (int i = 0; i < url.split("/").length - 2; i++) {
            try {
                tab.append("\t");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        uniqueURL.add(url);
        tab.append("\t");
        for (Element link : links) {
            String href = link.attr("abs:href");
            if (href.contains(url)&& !href.contains("#") && !href.contains("?")
                    && !uniqueURL.contains(href) && !href.contains("@")) {
                uniqueURL.add(href);
                Thread.sleep(100);
                System.out.println(tab + href);
                queue.add(tab + href);
                try {
                    getLinks(href);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        List<String> list = new LinkedList<>(uniqueURL);
        list.subList(1, list.size()).replaceAll(l -> l = "\t".repeat(l.split("/").length - 2) + l);
        FileUtils.writeLines(mapFile, list);
        FileUtils.writeLines(mapFile1, queue);
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
