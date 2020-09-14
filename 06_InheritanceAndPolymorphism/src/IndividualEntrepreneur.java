public class IndividualEntrepreneur extends Individual {
    final static double COMMISSION_MIN = 1.005;
    final static double COMMISSION_MAX = 1.01;

    public IndividualEntrepreneur(double balance) {
        super(balance);
    }

    @Override
    public void getInformation() {
        System.out.println("При зачеслении средств взымается с комиссия:" + System.lineSeparator() +
                "зачисление до 1000  - 0,5%, " + System.lineSeparator() +
                "зачисление 1000 и выше - 1%");
    }

    @Override
    public void deposit(double amount) {
        amount = round(amount);
        double amountCommissionMin = round(amount / COMMISSION_MIN);
        double amountCommissionMax = round(amount / COMMISSION_MAX);
        if (amount < 1000.00) {
            super.deposit(amountCommissionMin);
            System.out.println(" удержена комиссия 0,5% = " + amountCommissionMin);
        } else if (amount >= 1000) {
            super.deposit(amountCommissionMax);
            System.out.println(" удержена комиссия 1,0% = " + (amountCommissionMax));
        }
    }
}
