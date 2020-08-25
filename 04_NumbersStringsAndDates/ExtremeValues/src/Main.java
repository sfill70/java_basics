import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        System.out.println("Byte.MIN_VALUE = " + Byte.MIN_VALUE);
        System.out.println("Byte.MAX_VALUE = " + Byte.MAX_VALUE);

        System.out.println("Short.MIN_VALUE = " + Short.MIN_VALUE);
        System.out.println("Short.MAX_VALUE = " + Short.MAX_VALUE);

        System.out.println("Character.MIN_VALUE = " + (int) Character.MIN_VALUE);
        System.out.println("Character.MAX_VALUE = " + (int) Character.MAX_VALUE);

        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);

        System.out.println("Запись в экспоненциальная форме числа");
        System.out.println("Float.MIN_VALUE = " + Float.MIN_VALUE);
        System.out.println("Float.MAX_VALUE = " + Float.MAX_VALUE);
        System.out.println("Запись в десятичной форме числа");
        System.out.println(String.format("%.0f", Float.MAX_VALUE));


        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        System.out.println("Long.MIN_VALUE = " + Long.MIN_VALUE);
        System.out.println("Запись в экспоненциальная форме числа");
        System.out.println("Double.MIN_VALUE = " + Double.MIN_VALUE);
        System.out.println("Double.MAX_VALUE = " + Double.MAX_VALUE);
        System.out.println("Запись в десятичной форме числа");
        System.out.println(String.format("%.0f", Double.MAX_VALUE));

        System.out.println(BigDecimal.valueOf(Double.MAX_VALUE));
        System.out.println(BigInteger.valueOf(Long.MAX_VALUE));


    }
}
