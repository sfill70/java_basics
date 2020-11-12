import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {


    public static void main(String[] args)/* throws InterruptedException*/ {
        Random random = new Random();
        Bank bank = getBank();
        List<Thread> threadList = new ArrayList<>();
        ThreadLocalRandom randomTread = ThreadLocalRandom.current();
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    transactions(random, bank);
                }
            });
            threadList.add(thread);
            thread.start();
        }
        threadJoin(threadList);
        System.out.println("Заблокированно - " + bank.countLock() + " счетов");
    }

    private static Bank getBank() {
        HashMap<String, Account> hashMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            long l = 50000 + (long) (Math.random() * (150000 - 50000));
            hashMap.put(i + "Account", new Account(l, i + "Account"));
        }
        return new Bank(hashMap);
    }

    private static void transactions(Random random, Bank bank) {
        try {
            for (int i = 0; i < 5000; i++) {
                Account accountFrom;
                Account accountTo = null;
                int fromNum = random.nextInt(bank.getAccounts().size());
                int toNum = 0;
                while (toNum == 0 || fromNum == toNum) {
                    toNum = random.nextInt(bank.getAccounts().size());
                }
                accountFrom = (Account) bank.getAccounts().get(bank.getAccounts().keySet().toArray()[fromNum]);
                accountTo = (Account) bank.getAccounts().get(bank.getAccounts().keySet().toArray()[toNum]);
                if (accountFrom.getMoney() < accountTo.getMoney()) {
                   Account tmp = accountFrom;
                   accountFrom = accountTo;
                   accountTo = tmp;
                }

                long amount = 1000 + (long) (Math.random() * 100000);
                bank.transfer(accountFrom.getAccNumber(), accountTo.getAccNumber(), amount);
//                System.out.println(Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().getName() + " Завершен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void threadJoin(List<Thread> threadList) {
        for (Thread tr : threadList
        ) {
            try {
                tr.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
