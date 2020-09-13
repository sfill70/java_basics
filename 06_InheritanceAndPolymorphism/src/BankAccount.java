import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;

public class BankAccount {
    private static int countId;
    private static HashSet<Integer> idSet = new HashSet();
    private int id;
    private double balance;
    private LocalDate opened;

    public BankAccount(double balance) {
        this.id = generationId();
        countId++;
        idSet.add(id);
        this.balance = balance;
        opened = LocalDate.now();
    }

    public BankAccount(int id, double balance) {
        this.balance = balance;
        opened = LocalDate.now();
        if (!idSet.contains(id)) {
            this.id = id;
            idSet.add(id);
            countId++;
        } else {
            this.id = generationId();
            countId++;
            idSet.add(id);
            System.out.println("BankAccount с данным id создан быть не может, id: " + id);
        }
    }

    public BankAccount(int id, double balance, String date) {
        this(id, balance);
        String[] dateArray = date.trim().split("\\.|\\s|,");
        try {
            opened = LocalDate.of(Integer.parseInt(dateArray[0].replaceAll("[^0-9]", "")), Integer.parseInt(dateArray[1].replaceAll("[^0-9]", "")),
                    Integer.parseInt(dateArray[2].replaceAll("[^0-9]", "")));
        } catch (NumberFormatException | DateTimeException e) {
            opened = LocalDate.now();
            System.out.println("Пользовательские данные не коректны, создан BankAccount с текущей датой");
        }
    }

    public BankAccount(double balance, String date) {
        this(balance);
        String[] dateArray = date.trim().split("\\.|\\s|,");
        try {
            opened = LocalDate.of(Integer.parseInt(dateArray[0].replaceAll("[^0-9]", "")), Integer.parseInt(dateArray[1].replaceAll("[^0-9]", "")),
                    Integer.parseInt(dateArray[2].replaceAll("[^0-9]", "")));
        } catch (NumberFormatException | DateTimeException e) {
            opened = LocalDate.now();
            System.out.println("Пользовательские данные не коректны, создан BankAccount с текущей датой");
        }
    }

    private int generationId() {
        int id = countId + 1;
        while (idSet.contains(id)) {
            id = id + 1;
            if (!idSet.contains(id)) {
                return id;
            }
        }
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public LocalDate getOpened() {
        return opened;
    }

    public double withdraw(double amount) {
        amount = round(amount,2);
        if (amount <= this.balance) {
            this.balance = this.balance - amount;
            System.out.println("Операция выполнена, остаток - " + getBalance());
            return amount;
        } else {
            System.out.println("Остаток на счете менше запрашиваеммой суммы, доступно - " + getBalance());
        }
        return 0;
    }

    public void deposit(double amount) {
        amount = round(amount,2);
        this.balance = this.balance + amount;
        System.out.println("Счет пополнен, остаток - " + getBalance());
    }

    public boolean send(BankAccount receiver, double amount) {
        amount = round(amount,2);
        if (idSet.contains(receiver.getId())) {
            double summ;
            if ((summ = this.withdraw(amount)) > 0) {
                receiver.deposit(amount);
                return true;
            }
        }
        return false;
    }

    protected static double round(double number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        double tmp = number * pow;
        return (double) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", opened=" + opened +
                '}';
    }
}
