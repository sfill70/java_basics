package LinkActionNode;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static File mapFile = new File("sitemap_recursive_set.txt");
    static String baseLink = "https://lenta.ru/";
//    static String baseLink = "https://skillbox.ru/";

    public static void main(String[] args) {
        try {
            Node nd = new Node(baseLink, true);
            new ForkJoinPool().invoke(new LinkRecursiveActionNode(baseLink, nd));
            System.out.println("Ссылки собраны");
            LinkRecursiveActionNode.getTreeNode().writeTreeNode(nd, "\t");
            System.out.println("Конец");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
