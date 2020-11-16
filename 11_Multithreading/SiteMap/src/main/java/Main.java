import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static File mapFile = new File("sitemap_recursive_set.txt");
    static File mapFile1 = new File("sitemap_recursive_queue.txt");
    static String baseLink = "https://lenta.ru/";

    /*static File mapFile = new File("skill.txt");
    static String baseLink = "https://skillbox.ru/";*/

    public static void main(String[] args) {
        try {
            new ForkJoinPool().invoke(new LinkRecursiveAction(baseLink,""));
            System.out.println("Ссылки собраны");
            List<String> linkList = new ArrayList<String>(LinkRecursiveAction.getUniqueURL());
            linkList.subList(1, linkList.size()).replaceAll(link -> link = "\t".repeat(link.split("/").length - 2) + link);
            FileUtils.writeLines(mapFile, linkList);
            FileUtils.writeLines(mapFile1, LinkRecursiveAction.getQueue() );
            System.out.println("Конец");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
