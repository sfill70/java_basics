package binary_tree;

import java.util.ArrayList;

public class BinaryTree {
    private Node root;

    public void addNode(String data) {
        root = addRecursive(root, data);
    }

   /* private Node addRecursive(Node current, String data) {
        if (current == null) {
            return new Node(data);
        }
        if (data.compareTo(current.getData()) < 0) {
            current.setLeft(addRecursive(current.getLeft(), data));
//            current.getLeft().setParent(current);
        } else {
            current.setRight(addRecursive(current.getRight(), data));
//            current.getRight().setParent(current);
        }
        return current;
    }*/

    public Node addRecursive(Node current, String data) {
        if (current == null) {
            return new Node(data);
        }
        if (data.compareToIgnoreCase(current.getData()) < 0) {
            current.setLeft(addRecursive(current.getLeft(), data));
        } else if (data.compareToIgnoreCase(current.getData()) > 0) {
            current.setRight(addRecursive(current.getRight(), data));
        } else {
            //value already exists
            return current;
        }
        return current;
    }

    public boolean isContains(String data) {
        return containsNodeRecursive(root, data);
    }

    private boolean containsNodeRecursive(Node current, String data) {
        if (current == null) {
            return false;
        }
        if (data.equals(current.getData())) {
            return true;
        }
        return data.compareToIgnoreCase(current.getData()) < 0
                ? containsNodeRecursive(current.getLeft(), data)
                : containsNodeRecursive(current.getRight(), data);
    }

    public Node getRoot() {
        return root;
    }

    public void print() {
        int count = 1;
        System.out.print("Root: ");
        System.out.println(root);
        ArrayList<Node> rootNodeList = new ArrayList<>();
        if (root.getLeft() != null) {
            rootNodeList.add(root.getLeft());
        }
        if (root.getRight() != null) {
            rootNodeList.add(root.getRight());
        }
        System.out.print("Level " + count + " : ");
        System.out.println(rootNodeList);
        recursivePrint(rootNodeList, count);
    }

    private void recursivePrint(ArrayList<Node> rootNodeList, int count) {
        count++;
        ArrayList<Node> nodeArrayList = new ArrayList<>();
        for (Node node : rootNodeList
        ) {
            if (node.getLeft() != null) {
                nodeArrayList.add(node.getLeft());
            }
            if (node.getRight() != null) {
                nodeArrayList.add(node.getRight());
            }
        }
        if (nodeArrayList.size() == 0) {
            return;
        }
        System.out.print("Level " + count + " : ");
        System.out.println(nodeArrayList);
        recursivePrint(nodeArrayList, count);
    }

    public void printNodes() {
        System.out.println("Root level. " + root.toString());
        printRecursive(root, 1);
    }

    private void printRecursive(Node current, int level) {
        if (current != null) {
            Node left = current.getLeft();
            if (left != null) {
                System.out.println("Level " + level + ". " + left.toString());
                printRecursive(current.getLeft(), level + 1);
            }

            Node right = current.getRight();
            if (right != null) {
                System.out.println("Level " + level + ". " + right.toString());
                printRecursive(current.getRight(), level + 1);
            }
        }
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
