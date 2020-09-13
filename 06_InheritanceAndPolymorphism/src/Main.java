public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(123.00);
        BankAccount bankAccount1 = new BankAccount(3, 400.00);
        BankAccount bankAccount2 = new BankAccount(500.00, "2020 05 23");
        BankAccount bankAccount3 = new BankAccount(2, 1001.00);
        bankAccount1.deposit(34.00);
        bankAccount1.withdraw(114.00);
        double dh = bankAccount3.withdraw(250.50);
        System.out.println(dh);
        BankAccountDeposit bankAccountDeposit = new BankAccountDeposit(2000.00);
        BankAccountDeposit bankAccountDeposit1 = new BankAccountDeposit(2000.00, "2020 08 23");

        double d = bankAccountDeposit1.withdraw(1000.00);

        System.out.println(d);
        System.out.println(bankAccount);
        System.out.println(bankAccount1);
        System.out.println(bankAccount2);
        System.out.println(bankAccount3);
        System.out.println(bankAccountDeposit);
        System.out.println(bankAccountDeposit1);

        System.out.println(bankAccount3.send(bankAccount2, 124));

        System.out.println(bankAccount2);
        System.out.println(bankAccount3);

        BankCard bankCard = new BankCard(4000);
        BankCard bankCard1 = new BankCard(7000);

        bankCard1.send(bankCard,5321);

        System.out.println(bankCard);
        System.out.println(bankCard1);


    }
}
