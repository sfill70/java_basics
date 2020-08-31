import java.util.Scanner;

public class Main {

    static int CONTAINER_CAPACITY = 27;
    static int TRACK_CAPACITY = 12;

    public static void main(String[] args) {

        while (true) {

            System.out.println("Введите количество ящиков с помощью " +
                    "Для выхода введите - -2 (ноль)");

            int countBox;

            try {
                countBox = (new Scanner(System.in)).nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Надо вводить цифры");
                continue;
            }

            if (countBox == -2) {
                break;
            }

            int containers = 0;
            int trucks = 0;

            for (int i = 1; i <= countBox; i++) {
                if ((i - 1) % (TRACK_CAPACITY * CONTAINER_CAPACITY) == 0) {
                    System.out.println("Грузовик " + ++trucks); // ++ в начале, сначала прибавляет 1, потом использует значение
                }

                if ((i - 1) % CONTAINER_CAPACITY == 0) {
                    System.out.println("\t" + "Контейнер" + ++containers);
                }

                System.out.println("\t\t\t" + "Ящик " + i);
            }

            System.out.println("Вариант 0");
            System.out.println("Необходимо: ");
            System.out.println("Контейнеров: " + containers);
            System.out.println("Грузовиков: " + trucks);
            System.out.println();

            int countContainer = 0;
            int countCar = 0;

            for (int i = 1; i <= countBox; i++) {
                if (i == 1) {
                    countContainer++;
                    countCar++;
                    System.out.println("Грузовик " + countCar);
                    System.out.println("\t" + "Контейнер" + countContainer);
                }
                System.out.println("\t\t\t" + "Ящик " + i);
                if (i % CONTAINER_CAPACITY == 0) {
                    countContainer++;
                    if (countContainer % TRACK_CAPACITY == 1) {
                        countCar++;
                        System.out.println("Грузовик " + countCar);
                        System.out.println("UHP " + (i  / CONTAINER_CAPACITY / TRACK_CAPACITY +1) );
                    }
                    System.out.println("\t" + "Контейнер" + countContainer);
                }
            }
            System.out.println("Вариант 1");
            System.out.println("Необходимо: ");
            System.out.println("Контейнеров: " + countContainer);
            System.out.println("Грузовиков: " + countCar);
            System.out.println();

//           Вариант 2

            int count = 0;
            int countCon = 0;
            int countTrack = 0;
            boolean isWork = countBox > 0;
            if (!isWork) {
                System.out.println("Количество ящиков должно быть больше 0");
                continue;
            }
            while (isWork) {
                countTrack++;
                System.out.println("Грузовик " + countTrack);
                for (int j = 0; j < TRACK_CAPACITY; j++) {
                    if (!isWork) {
                        break;
                    }
                    countCon++;
                    System.out.println("\t" + "Контейнер" + countCon);
                    for (int i = 0; i < CONTAINER_CAPACITY; i++) {
                        count++;
                        System.out.println("\t\t\t" + "Ящик " + count);
                        if (count >= countBox) {
                            isWork = false;
                            break;
                        }
                    }
                }
            }

            System.out.println("Вариант 1");
            System.out.println("Необходимо: ");
            System.out.println("Контейнеров: " + countContainer);
            System.out.println("Грузовиков: " + countCar);
            System.out.println();

            System.out.println("Вариант 2");
            System.out.println("Необходимо: ");
            System.out.println("Контейнеров: " + countCon);
            System.out.println("Грузовиков: " + countTrack);

        }
    }
}
