public class BankCard extends BankAccount {

    public BankCard(double balance) {
        super(balance);
    }

    public BankCard(int id, double balance) {
        super(id, balance);
    }

    public BankCard(int id, double balance, String date) {
        super(id, balance, date);
    }

    public BankCard(double balance, String date) {
        super(balance, date);
    }

    @Override
    public double withdraw(double amount) {
        if (this.getBalance() >= amount * 1.1) {
            return super.withdraw(amount * 1.01);
        } else {
            System.out.println("Остаток на счете менше запрашиваеммой суммы, доступно - " + getBalance() / 1.01);
            return 0;
        }
    }
}
