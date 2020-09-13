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
        amount = round(amount, 2);
        System.out.println("Снятие " + amount);
        System.out.println("Снятие " + round(amount * 1.01, 2));
        if (this.getBalance() >= round(amount * 1.01, 2)) {
            return super.withdraw(round(amount * 1.01, 2));
        } else {
            System.out.println("Остаток на счете менше запрашиваеммой суммы, доступно - " + round(amount * 1.01, 2));
            return 0;
        }
    }
}
