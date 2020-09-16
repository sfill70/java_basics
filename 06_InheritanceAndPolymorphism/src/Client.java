import java.time.LocalDate;
import java.util.HashSet;

public abstract class Client {
    private static HashSet<Client> clientSet = new HashSet<>();
    private double balance;
    final private int id = clientSet.size() + 1;
    final private LocalDate opened = LocalDate.now();

    public Client(double balance) {
        if (balance < 0) {
            System.out.println("Баланс должн быть больше 0");
            return;
        }
        this.setBalance(balance);
        addClient();
    }

    protected void addClient(){
        clientSet.add(this);
    }
    public HashSet<Client> getClientSet() {
        return new HashSet<Client>(clientSet);
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    protected LocalDate getOpened(LocalDate now) {
        return opened;
    }

    public abstract void getInformation();

    public double withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма должна быть больше 0");
            return 0;
        }
        amount = round(amount);
        if (amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.printf("Списание средств со счета № %d на сумму: %.2f остаток: %.2f", getId(), amount, getBalance());
            System.out.println();
            return amount;
        } else {
            System.out.println("Остаток на счете менше запрашиваеммой суммы, доступно - " + getBalance());
        }
        return 0;
    }

    ;

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Сумма должна быть больше 0");
            return;
        }
        amount = round(amount);
        this.setBalance(getBalance() + amount);
        System.out.printf("Счет № %d пополнен, на сумму: %.2f остаток: %.2f", getId(), amount, getBalance());
        System.out.println();
    }

    ;

    public boolean send(Client receiver, double amount) {
        if (amount <= 0) {
            System.out.println("Сумма должна быть больше 0");
            return false;
        }
        amount = round(amount);
        if (getClientSet().contains(receiver)) {
            double summ = withdraw(amount);
            if (summ > 0) {
                receiver.deposit(amount);
                return true;
            }
        }
        return false;
    }

    ;

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

