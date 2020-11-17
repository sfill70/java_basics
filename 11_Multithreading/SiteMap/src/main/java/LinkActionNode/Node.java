package LinkActionNode;

import java.util.concurrent.CopyOnWriteArrayList;

public class Node {
    public int key;
    public String data;
    boolean isParent;
    Node parent;
    private CopyOnWriteArrayList<Node> listChild;

    public Node(String data, boolean isParent) {
        this.data = data;
        this.isParent = isParent;
        listChild = new CopyOnWriteArrayList<Node>();
    }

    public String getData() {
        return data;
    }

    public void addChild(Node node) {
        listChild.add(node);
    }

    public CopyOnWriteArrayList<Node> getListChild() {
        return listChild;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (isParent != node.isParent) return false;
        if (data != null ? !data.equals(node.data) : node.data != null) return false;
        return listChild != null ? listChild.equals(node.listChild) : node.listChild == null;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (isParent ? 1 : 0);
        result = 31 * result + (listChild != null ? listChild.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return data;
    }


}
