package LinkActionNode;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class TreeNode {
    private CopyOnWriteArrayList<Node> allNode;
    private Node parent;
    private static String root = System.getProperty("user.dir");
    private static final String PATH = String.join(File.separator, root, "src", "main", "java", "LinkActionNode", "site_map_node.txt");
    private static final String PATH_LIST = String.join(File.separator, root, "src", "main", "java", "LinkActionNode", "sitemap_list.txt");
    static File mapFile = new File(PATH_LIST);

    public TreeNode() {
        this.allNode = new CopyOnWriteArrayList<Node>();
    }

    public TreeNode(Node parent) {
        this.allNode = new CopyOnWriteArrayList<Node>();
        this.parent = parent;
        allNode.add(parent);

    }

    public Node getNode(Node node) {
        for (Node n : allNode
        ) {
            if (n.equals(node)) {
                return node;
            }
        }
        return null;
    }

    public boolean contains(Node node) {
        return allNode.contains(node);
    }

    public void add(Node node) {
        allNode.add(node);
    }

    public Node get(int i) {
        return allNode.get(i);
    }

    public int size() {
        return allNode.size();
    }

    public void printTreeNode(CopyOnWriteArrayList<Node> nodes, String tab) {
        for (Node n : nodes
        ) {
            System.out.println(tab + n.getData());
            if (n.isParent()) {
                printTreeNode(n.getListChild(), "\t" + tab);
            }
        }
    }

    public void writeTreeNode(Node parent, String tab) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH)))) {
            System.out.println(parent.getData());
            out.println(parent.getData());
            writeNodes(parent, tab, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeNodes(Node parent, String tab, PrintWriter out) {
        for (Node n : parent.getListChild()
        ) {
            System.out.println(tab + n.getData());
            out.println(tab + n.getData());
            if (n.isParent()) {
                writeNodes(n, "\t" + tab, out);
            }
        }

    }

    public void writeAllArrays(Node parent){
        try {
            ArrayList<ArrayList<String>> listArray = new ArrayList<>();
            ArrayList<String> listString = new ArrayList<>();
            listArray.add(new ArrayList<>(Collections.singletonList(parent.getData())));
            listArray.add(listString);
            fillingArrays(parent, listString, listArray);
            listArray.removeIf(ArrayList::isEmpty);
            FileUtils.writeLines(mapFile, listArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillingArrays(Node parent, ArrayList<String> listString, ArrayList<ArrayList<String>> listArray) {
        ArrayList<String> list = new ArrayList<>();
        listArray.add(list);
        for (Node n : parent.getListChild()
        ) {
            listString.add(n.getData());
            if (n.isParent()) {
                fillingArrays(n,  list, listArray);
            }
        }
    }

    private void writeString(String s) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("site_map_node.txt"/*, true*/)))) {
            out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
