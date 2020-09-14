import java.time.LocalDate;
import java.util.HashSet;

public abstract class Client {
    private static HashSet<Client> ClientSet = new HashSet<>();
    private double balance;
    final private int id = ClientSet.size() + 1;
    final private LocalDate opened = LocalDate.now();

    public static HashSet<Client> getClientSet() {
        return ClientSet;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getOpened(LocalDate now) {
        return opened;
    }

    public abstract void getInformation ();

    public abstract double withdraw(double amount);

    public abstract void deposit(double amount);

    public abstract boolean send(Client receiver, double amount);

    protected static double round(double number) {
        int pow = 10;
        for (int i = 1; i < 2; i++)
            pow *= 10;
        double tmp = number * pow;
        return (double) (int) ((tmp - (int) tmp) >= 0.5d ? tmp + 1 : tmp) / pow;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", balance=" + balance +
                ", opened=" + opened +
                '}';
    }

}

