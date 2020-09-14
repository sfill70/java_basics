import java.time.LocalDate;

public class BankCard extends BankAccount {

    public BankCard(double balance) {
        super(balance);
    }

    public BankCard(int id, double balance) {
        super(id, balance);
    }

    public BankCard(int id, double balance, LocalDate date) {
        super(id, balance, date);
    }



    @Override
    public double withdraw(double amount) {
        amount = round(amount, 2);
        if (this.getBalance() >= round(amount * 1.01, 2)) {
            return super.withdraw(round(amount * 1.01, 2));
        } else {
            System.out.println("Остаток на счете менше запрашиваеммой суммы, доступно - " + round(amount / 1.01, 2)
                    + " с учетом комиссии");
            return 0;
        }
    }
}
