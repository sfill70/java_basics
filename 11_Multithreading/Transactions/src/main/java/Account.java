import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Comparable<Account> {
    private volatile long money;
    private String accNumber;
    private boolean isLocked;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        isLocked = false;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }


    public void deposit(long amount) {
        money = money + amount;
    }

    public void withdraw(long amount) {
        if (amount <= this.money) {
            this.money = this.money - amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (money != account.money) return false;
        return Objects.equals(accNumber, account.accNumber);
    }

    @Override
    public int hashCode() {
        int result = (int) (money ^ (money >>> 32));
        result = 31 * result + (accNumber != null ? accNumber.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Account o) {
        int result = this.getAccNumber().compareTo(o.accNumber);
        if (result == 0) {
            result = ((Long) this.money).compareTo(((Long) o.getMoney()));
        }
        return result;
    }
}