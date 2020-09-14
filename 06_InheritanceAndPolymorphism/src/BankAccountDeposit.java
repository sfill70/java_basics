import java.time.LocalDate;

public class BankAccountDeposit extends BankAccount {

    private LocalDate withdrawDeposit;
    final static int DEPOSIT_PERIOD = 1;

    public BankAccountDeposit(double balance) {
        super(balance);
        this.withdrawDeposit = this.getOpened().plusMonths(DEPOSIT_PERIOD);
    }

    public BankAccountDeposit(int id, double balance) {
        super(id, balance);
        this.withdrawDeposit = this.getOpened().plusMonths(DEPOSIT_PERIOD);
    }

    public BankAccountDeposit(int id, double balance, String date) {
        super(id, balance, date);
        this.withdrawDeposit = this.getOpened().plusMonths(DEPOSIT_PERIOD);
    }

    public BankAccountDeposit(double balance, String date) {
        super(balance, date);
        this.withdrawDeposit = this.getOpened().plusMonths(DEPOSIT_PERIOD);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        LocalDate withdrawTmp = this.withdrawDeposit.plusMonths(DEPOSIT_PERIOD);
        if (withdrawTmp.isAfter(withdrawDeposit)){
            withdrawDeposit = withdrawTmp;
        }
    }

    @Override
    public double withdraw(double amount) {
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.withdrawDeposit)) {
            return super.withdraw(amount);
        } else {
            System.out.println("Срок снятия депозита не наступил");
            return 0;
        }
    }
}
