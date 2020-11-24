package LinkActionNode;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static ForkJoinPool pool = new ForkJoinPool();
    static String baseLink = "https://lenta.ru/";
    //    static String baseLink = "https://skillbox.ru/";


    public static void main(String[] args) {
        try {
            Node parent = new Node(baseLink);
            Thread info = getThreadInfo();
            info.start();
            Thread threadScanner = getThreadThreadScanner(parent);
            LinkRecursiveActionNode linkRecursiveActionNode = new LinkRecursiveActionNode(baseLink, parent, "?", "pdf", "@", "#", "comments", "201", "200");
//          ForkJoinPool pool = new ForkJoinPool();
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
            if (threadScanner.isAlive() || info.isAlive()) {
                System.out.println("Закрываем!");
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Thread getThreadThreadScanner(Node parent) {
        Thread threadScanner = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Scanner sc = new Scanner(System.in);) {
                    while (!Thread.currentThread().isInterrupted()) {
                        if (sc.nextLine().equalsIgnoreCase("exit")) {
                            stopPool();
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
        return threadScanner;
    }

    private static Thread getThreadInfo() {
        return new Thread(new Runnable() {
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
    }

    private static void stopPool() {
        pool.shutdownNow();
    }

}
