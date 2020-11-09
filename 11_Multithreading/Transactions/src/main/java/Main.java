import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static int numberCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args)/* throws InterruptedException*/ {
        Random random = new Random();
        Bank bank = getBank();
        List<Thread> threadList = new ArrayList<>();
        ThreadLocalRandom randomTread = ThreadLocalRandom.current();
        for (int i = 0; i < numberCores; i++) {
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
        System.out.println("Заблокированно - " + bank.getLockedAccounts().size() + " счетов");
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
            for (int i = 0; i < bank.getAccounts().size(); i++) {
                Account accountFrom;
                Account accountTo = null;

                synchronized (bank.getAccounts()) {
                    int fromNum = random.nextInt(bank.getAccounts().size());
                    accountFrom = (Account) bank.getAccounts().get(bank.getAccounts().keySet().toArray()[fromNum]);
                    int toNum = 0;

                    while (accountTo == null || accountFrom.equals(accountTo)) {
                        toNum = random.nextInt(bank.getAccounts().size());

                        accountTo = (Account) bank.getAccounts().get(bank.getAccounts().keySet().toArray()[toNum]);
                    }
                }
                if (accountFrom.getMoney() < accountTo.getMoney()) {
                    permutation(accountFrom, accountTo);
                }
                Long amount = accountFrom.getMoney() / 3 + (long) (Math.random() * (accountFrom.getMoney() / 3));
//                System.out.println(accountFrom.getMoney() + " - " + accountFrom.getAccNumber());
//                System.out.println(accountTo.getMoney() + " - " + accountTo.getAccNumber());
                bank.transfer(accountFrom.getAccNumber(), accountTo.getAccNumber(), amount);
//                System.out.println(accountFrom.getMoney() + " - " + amount + " - " + accountFrom.getAccNumber());
//                System.out.println(accountTo.getMoney() + " - " +  amount + " - " + accountTo.getAccNumber());
//                System.out.println(Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().getName() + " Завершен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void permutation(Account accountFrom, Account accountTo) {
        long tmp = accountFrom.getMoney();
        accountFrom.setMoney(accountTo.getMoney());
        accountTo.setMoney(tmp);
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
