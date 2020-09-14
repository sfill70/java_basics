public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(123.00);
        BankAccount bankAccount1 = new BankAccount(3, 400.00);

        BankAccount bankAccount3 = new BankAccount(2, 1001.00);
        bankAccount1.deposit(34.00);
        bankAccount1.withdraw(114.00);
        double dh = bankAccount3.withdraw(250.50);
        System.out.println(dh);
        BankAccountDeposit bankAccountDeposit = new BankAccountDeposit(2000.00);



        System.out.println(bankAccount);
        System.out.println(bankAccount1);

        System.out.println(bankAccount3);
        System.out.println(bankAccountDeposit);

        System.out.println(bankAccount3);

        /*BankCard bankCard = new BankCard(4000);
        BankCard bankCard1 = new BankCard(7000);

        bankCard1.send(bankCard,8000);
        System.out.println(bankCard);
        System.out.println(bankCard1);

        bankCard1.send(bankCard,3257);
        System.out.println(bankCard);
        System.out.println(bankCard1);*/

       /* Client client = new IndividualEntrepreneur(100000);
        System.out.println(client);
        client.deposit(555);
        System.out.println(client);
        client.deposit(1234);
        System.out.println(client);
        client.withdraw(1498);
        System.out.println(client);

        Client client1 = new legalEntity(200000);
        System.out.println(client1);
        client1.deposit(1234);
        System.out.println(client1);
        client1.withdraw(10000);
        System.out.println(client1);*/

    }
}
