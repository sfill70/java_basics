import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
   /*     BankAccount bankAccount = new BankAccount(123.00);
        BankAccount bankAccount1 = new BankAccount(3, 400.00);
        BankAccount bankAccount2 = new BankAccount( 1001.00);
        BankAccount bankAccount3 = new BankAccount( 3,8510.00,  LocalDate.of(2020,8,20));
        System.out.println(bankAccount);
        System.out.println(bankAccount1);
        System.out.println(bankAccount2);
        System.out.println(bankAccount3);
        bankAccount1.deposit(34.00);
        bankAccount1.withdraw(114.00);

        BankAccountDeposit bankAccountDeposit = new BankAccountDeposit(5,2000.00, LocalDate.of(2020,8,20));
        bankAccountDeposit.withdraw(1000);
        System.out.println(bankAccount);
        System.out.println(bankAccount1);

        System.out.println(bankAccount2);
        System.out.println(bankAccountDeposit);

        System.out.println(bankAccount2);

        BankCard bankCard = new BankCard(4000);
        BankCard bankCard1 = new BankCard(7000);

        bankCard1.send(bankCard,8000);
        System.out.println(bankCard);
        System.out.println(bankCard1);

        bankCard1.send(bankCard,3257);
        System.out.println(bankCard);
        System.out.println(bankCard1);
*/
        Client clientIP = new IndividualEntrepreneur(100000);
        System.out.println(clientIP);
//        client.deposit(555);
//        System.out.println(client);
//        client.deposit(1234);
//        System.out.println(client);
//        client.withdraw(1498);
//        System.out.println(client);

        Client clientOOO= new legalEntity(200000);
        System.out.println(clientOOO);
//        client1.deposit(1234);
//        System.out.println(client1);
//        client1.withdraw(10000);
//        System.out.println(client1);
        clientOOO.send(clientIP,2000);
        System.out.println(clientIP);
        System.out.println(clientOOO);
        clientIP.getInformation();
        clientOOO.getInformation();

    }
}
