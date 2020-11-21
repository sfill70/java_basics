package LinkActionNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static String baseLink = "https://lenta.ru/";
    //    static String baseLink = "https://skillbox.ru/";


    public static void main(String[] args) {
        try {
            Robot r = new Robot();
            Node parent = new Node(baseLink);
            Thread threadScanner = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (Scanner sc = new Scanner(System.in);) {
                        while (true) {
                            if (sc.hasNext("exit")) {
                                LinkRecursiveActionNode.getTreeNode().writeTreeNode(parent, "\t");
                                System.exit(0);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            threadScanner.start();
            System.out.println(threadScanner.getName());
            new ForkJoinPool().invoke(new LinkRecursiveActionNode(baseLink, parent, "?", "pdf", "@", "#", "comments", "201", "200"));

            threadScanner.interrupt();
            System.out.println(threadScanner.getName());
            System.out.println(threadScanner.isInterrupted());
            System.out.println(threadScanner.isAlive());
            if (threadScanner.isInterrupted()) {
                threadScanner.stop();
            }
            System.out.println(threadScanner.isAlive());

            System.out.println("Ссылки собраны");
            //Пишем в файл ссылки с табуляцией
            LinkRecursiveActionNode.getTreeNode().writeTreeNode(parent, "\t");
            //Пишем в файл ссылки по уровням
            LinkRecursiveActionNode.getTreeNode().writeAllArrays(parent);
            System.out.println("Конец");

            if (threadScanner.isAlive()) {
                System.out.println("Закрываем!");
                /*System.out.println("exit");
                r.keyPress(KeyEvent.VK_ENTER);
                r.keyRelease(KeyEvent.VK_ENTER);*/
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
