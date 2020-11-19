package LinkActionNode;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static String baseLink = "https://lenta.ru/";
//    static String baseLink = "https://skillbox.ru/";

    public static void main(String[] args) {
        try {
            Node parent = new Node(baseLink);
            new ForkJoinPool().invoke(new LinkRecursiveActionNode(baseLink, parent, "?", "pdf", "@", "#", "comments", "201", "200"));
            System.out.println("Ссылки собраны");
            //Пишем в файл ссылки с табуляцией
            LinkRecursiveActionNode.getTreeNode().writeTreeNode(parent, "\t");
            //Пишем в файл ссылки по уровням
            LinkRecursiveActionNode.getTreeNode().writeAllArrays(parent);
            System.out.println("Конец");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
