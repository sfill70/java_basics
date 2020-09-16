public class legalEntity extends Individual {

    final static double COMMISSION = 1.01;

    public legalEntity(double balance) {
        super(balance);
    }

    @Override
    public void getInformation() {
        System.out.println("При снятии средств взымается с комиссия - 1%");
    }

    @Override
    public double withdraw(double amount) {
        amount = round(amount);
        double amountCommission = round(amount * COMMISSION);
        if (getBalance() >= amountCommission) {
            System.out.println("Комиссия за снятие составляет - 1% = " + (amountCommission - amount));
            return super.withdraw(amountCommission);
        } else {
            System.out.println("Остаток на счете менше запрашиваеммой суммы, доступно - " + (round(amount/COMMISSION))
                    + " с учетом комиссии 1%");
            return 0;
        }
    }
}
