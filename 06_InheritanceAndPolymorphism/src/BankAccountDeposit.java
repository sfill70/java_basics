import java.time.LocalDate;

public class BankAccountDeposit extends BankAccount {

    private LocalDate closed;

    public BankAccountDeposit(double balance) {
        super(balance);
        this.closed = this.getOpened().plusMonths(1);
    }

    public BankAccountDeposit(int id, double balance) {
        super(id, balance);
        this.closed = this.getOpened().plusMonths(1);
    }

    public BankAccountDeposit(int id, double balance, String date) {
        super(id, balance, date);
        this.closed = this.getOpened().plusMonths(1);
    }

    public BankAccountDeposit(double balance, String date) {
        super(balance, date);
        this.closed = this.getOpened().plusMonths(1);
    }

    @Override
    public void deposit(double sum) {
        super.deposit(sum);
        this.closed = this.closed.plusMonths(1);
    }

    @Override
    public double withdraw(double amount) {
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.closed)) {
            return super.withdraw(amount);
        } else {
            System.out.println("Срок снятия депозита не наступил");
            return 0;
        }
    }
}
