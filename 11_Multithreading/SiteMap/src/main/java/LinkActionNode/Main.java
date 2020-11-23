package LinkActionNode;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static String baseLink = "https://lenta.ru/";
    //    static String baseLink = "https://skillbox.ru/";


    public static void main(String[] args) {
        try {
            Node parent = new Node(baseLink);
            Thread info = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            System.out.println("Для выхода - exit");
                            Thread.sleep(10000);
                            System.out.println("Найдено уникальных ссылок - " + LinkRecursiveActionNode.getCount());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadScanner = new Thread(new Runnable() {
                @Override
                public void run() {
                    try (Scanner sc = new Scanner(System.in);) {
                        while (!Thread.currentThread().isInterrupted()) {
                            if (sc.nextLine().equalsIgnoreCase("exit")) {
                                System.out.println("Найдено уникальных ссылок - " + LinkRecursiveActionNode.getCount());
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
            info.start();
            LinkRecursiveActionNode linkRecursiveActionNode = new LinkRecursiveActionNode(baseLink, parent, "?", "pdf", "@", "#", "comments", "201", "200");
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(linkRecursiveActionNode);
            System.out.println("Ссылки собраны");
            info.interrupt();
            threadScanner.interrupt();
            Thread.sleep(1000);

            //Пишем в файл ссылки с табуляцией
            LinkRecursiveActionNode.getTreeNode().writeTreeNode(parent, "\t");
            //Пишем в файл ссылки по уровням
            LinkRecursiveActionNode.getTreeNode().writeAllArrays(parent);
            System.out.println("Конец Найдено уникальных ссылок - " + LinkRecursiveActionNode.getCount());
            System.out.println(info.isAlive());
            if (threadScanner.isAlive() ||info.isAlive()) {
                System.out.println("Закрываем!");
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
