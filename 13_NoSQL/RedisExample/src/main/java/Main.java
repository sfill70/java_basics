
import java.util.Random;

public class Main {
    private static final int USERS_COUNT = 20;
    private static final int SLEEP = 1000;

    public static void main(String[] args) {

        RStorage redis = new RStorage();
        redis.init();
        try {
            registrationUsers(redis);
            System.out.println("20 пользователей зарегистрированы");
            iterationUsers(redis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        redis.shutdown();
    }

    private static void iterationUsers(RStorage redis) throws InterruptedException {
        for (int i = 1; ; i++) {
            if (i == 10) {
                int user_id = new Random().nextInt(19) + 1;
                printPayment(String.valueOf(user_id));
                i = 1;
            } else {
                double value = redis.getFirstValue();
                String id = redis.popFirst();
                printUser(id);
                redis.changePriority(value, id);
            }
            Thread.sleep(SLEEP);
        }
    }

    private static void registrationUsers(RStorage redis) throws InterruptedException {
        for (int i = 1; i <= USERS_COUNT; i++) {
            System.out.println("Ждем пользователей, пользлватель № " + i + " зарегистрирован");
            Thread.sleep(1000);
            redis.logPageVisit(i);
        }
    }

    private static void printUser(String userId) {
        String log = String.format("— На главной странице показываем пользователя %s", userId);
        System.out.println(log);
    }

    private static void printPayment(String userId) {
        String log = String.format("> Пользователь %s оплатил платную услугу", userId);
        System.out.println(log);
    }

}
