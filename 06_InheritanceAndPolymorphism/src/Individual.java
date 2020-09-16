public class Individual extends Client {

    public Individual(double balance) {
        super(balance);
    }

    @Override
    public void getInformation() {
        System.out.println("Пополнение счета и снятие средств происходит без комиссии");
    }
}
