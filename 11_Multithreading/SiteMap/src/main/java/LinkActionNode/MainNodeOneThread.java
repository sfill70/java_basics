package LinkActionNode;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;


public class MainNodeOneThread {
    static File mapFile = new File("sitemap_set.txt");
    static File mapFile1 = new File("sitemap_queue.txt");
    static String baseLink = "https://lenta.ru/";
    //    static String baseLink = "https://skillbox.ru/";
    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
    private static Set<String> uniqueURL = new ConcurrentSkipListSet<>();
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private static final String REFERRER = "https://www.google.com/";
    private static TreeNode treeNode = new TreeNode(new Node(baseLink, true));

    public static void main(String[] args) {
        queue.add(baseLink);
        Node nd = new Node(baseLink, true);
        try {

            getLinks(baseLink,"", nd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(treeNode);
//        treeNode.printTreeNode(treeNode.get(0).getListChild(),"");
        treeNode.writeTreeNode(nd,"\t");

    }

    private static void getLinks(String url, String t, Node parent) throws IOException, InterruptedException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)))) {
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
            parent.setIsParent(true);
//            System.out.println(url);
            out.println(tab + url);
            tab.append("\t");
            for (Element link : links) {
                String href = link.attr("abs:href");
                if (href.contains(url) && !href.contains("#") && !href.contains("?")
                        && !uniqueURL.contains(href) && !href.contains("@")) {
                    uniqueURL.add(href);
                    Node child = new Node(href, false);
                    treeNode.add(child);
                    parent.addChild(child);
                    out.println(tab + href);
                    Thread.sleep(100);
//                    System.out.println(tab + href);
                    queue.add(tab + href);
                    try {
                        getLinks(href, tab.toString(), child);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            List<String> list = new LinkedList<>(uniqueURL);
            list.subList(1, list.size()).replaceAll(l -> l = "\t".repeat(l.split("/").length - 2) + l);
            FileUtils.writeLines(mapFile, list);
            FileUtils.writeLines(mapFile1, queue);
//            System.out.println(treeNode.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
