import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

public class HomeworkFiveOne {

    static int NUMBER_OF_PATIENTS = 30;
    static float MAXIMUM_PATIENT_TEMPERATURE = (float) 40.0;
    static float MINIMUM_PATIENT_TEMPERATURE = (float) 32.0;
    static float MAXIMUM_TEMPERATURE_HEALTHY_PATIENTS = (float) 36.9;
    static float MINIMUM_TEMPERATURE_HEALTHY_PATIENTS = (float) 36.2;

    public static void main(String[] args) {

//        Homework 5.1.1
        System.out.println("Homework 5.1.2");
        String word = "Каждый охотник желает знать, где сидит фазан.";
        String[] arrayWords = word.trim().replaceAll("\\p{Punct}", "").trim().split("\\s+");
        System.out.println(Arrays.asList(arrayWords));
        int i = 0;
        String tmp;
        while (i < arrayWords.length / 2) {
            tmp = arrayWords[i];
            arrayWords[i] = arrayWords[arrayWords.length - i - 1];
            arrayWords[arrayWords.length - i - 1] = tmp;
            i++;
        }
        System.out.println(Arrays.asList(arrayWords));

        //        Homework 5.1.2
        Random rand = new Random();
        float temperature;
        float[] temperatures = new float[30];
        float averageTemperature = 0;
        int countHealthy = 0;
        for (int j = 0; j < NUMBER_OF_PATIENTS; j++) {
            temperature = round(MINIMUM_PATIENT_TEMPERATURE + rand.nextFloat() * (MAXIMUM_PATIENT_TEMPERATURE - MINIMUM_PATIENT_TEMPERATURE), 1);
//            System.out.println(round(fl,1));
            averageTemperature += temperature;
            if (temperature >= MINIMUM_TEMPERATURE_HEALTHY_PATIENTS && temperature <= MAXIMUM_TEMPERATURE_HEALTHY_PATIENTS) {
                countHealthy++;
            }
            temperatures[j] = temperature;
        }

        System.out.println("Homework 5.1.2");
        System.out.println("Температуры пациентов - " + Arrays.toString(temperatures).replaceAll("\\[", "").replaceAll("]", ""));
        System.out.println("Средняя температура - " + round(averageTemperature / temperatures.length, 2));
        System.out.println("Количество здоровых пациетов - " + countHealthy);

        //        Homework 5.1.3

        System.out.println("Homework 5.1.3");
        String[][] crosses = new String[7][7];
        int n = 0;
        for (int j = 0; j < crosses.length; j++) {
            for (int k = 0; k < crosses[0].length; k++) {
                if (k == crosses[0].length - 1 - n || k == n) {
                    crosses[j][k] = "X";
                    System.out.print("X" + k);
                } else {
                    crosses[j][k] = " ";
                    System.out.print(" ");
                }
            }
            n++;
            System.out.println();
        }
    }

    private static float round(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }


}






