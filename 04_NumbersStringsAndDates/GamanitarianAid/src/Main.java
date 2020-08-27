import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {

            System.out.println("Введите количество ящиков с помощью " +
                    "Для выхода введите - 0 (ноль)");

            int countBox;

            try {
                countBox = (new Scanner(System.in)).nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Надо вводить цифры");
                continue;
            }

            if (countBox == 0) {
                break;
            }

            int countContainer = 1;
            int countCar = 1;
            int tmpcountContainer = 0;
            boolean isNewCar = false;

            for (int i = 1; i <= countBox; i++) {
                if (i == 1) {
                    System.out.println("Грузовик " + countCar);
                    System.out.println("\t" + "Контейнер" + countContainer);
                }
                System.out.println("\t\t\t" + "Ящик " + i);
                if (i % 27 == 0) {
                    countContainer++;
                    if (isNewCar && tmpcountContainer == countContainer) {
                        System.out.println("Грузовик " + countCar);
                        isNewCar = false;
                    }
                    if (countContainer % 12 == 0) {
                        tmpcountContainer = countContainer + 1;
                        countCar++;
                        isNewCar = true;
                    }
                    System.out.println("\t" + "Контейнер" + countContainer);
                }
            }
            System.out.println("Необходимо: ");
            System.out.println("Контейнеров: " + countContainer);
            System.out.println("Грузовиков: " + countCar);

//           Вариант 2

            int count = 0;
            int countCon = 0;
            int countTrack = 0;
            boolean isWork = true;

            while (isWork) {
                countTrack++;
                System.out.println("Грузовик " + countTrack);
                for (int j = 0; j < 12; j++) {
                    if (!isWork) {
                        break;
                    }
                    countCon++;
                    System.out.println("\t" + "Контейнер" + countCon);
                    for (int i = 0; i < 27; i++) {
                        count++;
                        System.out.println("\t\t\t" + "Ящик " + count);
                        if (count >= countBox) {
                            isWork = false;
                            break;
                        }
                    }
                }
            }

            System.out.println("Необходимо: ");
            System.out.println("Контейнеров: " + countCon);
            System.out.println("Грузовиков: " + countTrack);

        }
    }
}
