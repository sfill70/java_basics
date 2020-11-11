import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Bank {
    private HashMap<String, Account> accounts;
    private ConcurrentSkipListSet<Account> lockedAccounts;
    private final Random random = new Random();
    private final long OPERATION_LIMIT = 50000L;

    public Bank(HashMap<String, Account> accounts) {
        this.accounts = accounts;
        lockedAccounts = new ConcurrentSkipListSet<Account>();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public ConcurrentSkipListSet<Account> getLockedAccounts() {
        return lockedAccounts;
    }

    public void setLockedAccounts(ConcurrentSkipListSet<Account> lockedAccounts) {
        this.lockedAccounts = lockedAccounts;
    }

    public int countLock() {
        int count = 0;
        for (Map.Entry<String, Account> entry : accounts.entrySet()) {
            if (entry.getValue().isLocked()) {
                count++;
            }
        }
        return count;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        boolean isLock = random.nextBoolean();
        accounts.get(fromAccountNum).setLocked(true);
        accounts.get(toAccountNum).setLocked(true);
        Thread.sleep(100);
        if (!isLock) {
            accounts.get(fromAccountNum).setLocked(false);
            accounts.get(toAccountNum).setLocked(false);
        }
        return isLock;
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {

        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (!fromAccount.isLocked() && !toAccount.isLocked()) {
            if (!fromAccount.equals(toAccount) && fromAccount.getMoney() >= amount) {
                synchronized (fromAccount.compareTo(toAccount) > 0 ? toAccount : fromAccount) {
                    synchronized (fromAccount.compareTo(toAccount) > 0 ? fromAccount : toAccount) {
                        fromAccount.withdraw(amount);
                        toAccount.deposit(amount);
                    }
                }
            }
            if (amount > OPERATION_LIMIT) {
                isFraud(fromAccountNum, toAccountNum, amount);
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }
}
