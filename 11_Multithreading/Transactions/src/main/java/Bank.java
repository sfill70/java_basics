import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    private HashMap<String, Account> accounts;
    private ConcurrentHashMap<String, Account> lockedAccounts;
    private final Random random = new Random();
    private final long OPERATION_LIMIT = 50000L;

    public Bank(HashMap<String, Account> accounts) {
        this.accounts = accounts;
        lockedAccounts = new ConcurrentHashMap<String, Account>();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public ConcurrentHashMap<String, Account> getLockedAccounts() {
        return lockedAccounts;
    }

    public void setLockedAccounts(ConcurrentHashMap<String, Account> lockedAccounts) {
        this.lockedAccounts = lockedAccounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(10);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        synchronized (accounts) {
            Account fromAccount = accounts.get(fromAccountNum);
            Account toAccount = accounts.get(toAccountNum);
            if (fromAccount != null && toAccount != null) {
                if (accounts.size() > 100 && !fromAccount.equals(toAccount) && fromAccount.getMoney() >= amount) {
                    fromAccount.withdraw(amount);
//                    accounts.put(fromAccountNum, fromAccount);
                    accounts.replace(fromAccountNum, fromAccount);
                    toAccount.deposit(amount);
//                    accounts.put(toAccountNum, toAccount);
                    accounts.replace(toAccountNum, toAccount);
                    if (amount > OPERATION_LIMIT) {
                        if (isFraud(fromAccountNum, toAccountNum, amount)) {
                            lockedAccounts.put(fromAccountNum, fromAccount);
                            lockedAccounts.put(toAccountNum, toAccount);
                            accounts.remove(fromAccountNum);
                            accounts.remove(toAccountNum);
                        }
                    }
                }
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
