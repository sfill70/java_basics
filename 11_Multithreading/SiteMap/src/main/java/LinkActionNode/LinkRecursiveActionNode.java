package LinkActionNode;

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
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;


public class LinkRecursiveActionNode extends RecursiveAction {
    private String baseLink;
    private Node parent;
    static String baseUrl = "https://lenta.ru/";
//        static String baseUrl = "https://skillbox.ru/";

    private static Set<String> uniqueURL = new ConcurrentSkipListSet<>(Collections.singletonList(baseUrl));
    private List<LinkRecursiveActionNode> actionList = new CopyOnWriteArrayList<>();
    private static TreeNode treeNode = new TreeNode(new Node(baseUrl, true));

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private static final String REFERRER = "https://www.google.com/";

    private static final Logger LOGGER = LogManager.getLogger(LinkRecursiveActionNode.class);
    private static final Marker INVALID_LINE_MARKER = MarkerManager.getMarker("VIEW_INVALID");
    private static final Marker VIEW_FILEPATH_MARKER = MarkerManager.getMarker("VIEW_FILEPATH");

    public LinkRecursiveActionNode(String baseLink, Node parent) {
        this.baseLink = baseLink;
        this.parent = parent;
    }

    public static TreeNode getTreeNode() {
        return treeNode;
    }

    public static Set<String> getUniqueURL() {
        return uniqueURL;
    }

    @Override
    protected void compute() {
        getLinks(baseLink, parent);
    }

    private void getLinks(String url, Node parent) {
        try {
            Elements links = getElements(url);

            if (links.isEmpty()) {
                return;
            }
            parent.setIsParent(true);
//            System.out.println(url);
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (href.contains(baseUrl) && href.contains(url) && !uniqueURL.contains(href) && !href.contains("#") /*&& !href.contains("?")*/
                        && !href.contains("@") && !href.endsWith("pdf")&& !href.contains("news") && !href.contains("comments")  /*&& !href.contains("201")*/ /*&& !href.contains("200")*/) {
                    uniqueURL.add(href);
                    Node child = new Node(href, false);
                    parent.addChild(child);
                    Thread.sleep(100);
                    System.out.println(href);
                    LinkRecursiveActionNode action = new LinkRecursiveActionNode(href, child);
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
                for (LinkRecursiveActionNode action : actionList)
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
            return new Elements(0);
        }
        return doc.select("a[href]");
    }

}
