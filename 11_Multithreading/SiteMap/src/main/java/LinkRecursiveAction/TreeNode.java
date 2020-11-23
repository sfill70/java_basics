package LinkRecursiveAction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

public class TreeNode {
    private CopyOnWriteArrayList<Node> allNode;

    private Node parent;

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
//        System.out.println(allNode.get(0).getListChild());
        for (Node n : nodes
        ) {
            System.out.println(tab + n.getData());
            if (n.isParent()) {
                printTreeNode(n.getListChild(), "\t" + tab);
            }
        }
    }

    public void writeTreeNode(Node parent, String tab) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("site_map_node.txt"/*, true*/)))) {
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
                writeNodes(n, "\t" + tab,out);
            }
        }
    }

    private void writeString(String s){

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("site_map_node.txt"/*, true*/)))){
            out.println(s);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
