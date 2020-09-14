public class Individual extends Client {

    public Individual(double balance) {
        if (balance <= 0) {
            System.out.println("Баланс должн быть больше 0");
            return;
        }
        this.setBalance(balance);
        getClientSet().add(this);
    }

    @Override
    public void getInformation() {
        System.out.println("Пополнение счета и снятие средств происходит без комиссии");
    }

    @Override
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

    @Override
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

    @Override
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
}
