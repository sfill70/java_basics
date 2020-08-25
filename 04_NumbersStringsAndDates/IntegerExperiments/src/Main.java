public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;
        System.out.println(container.count);
        System.out.println(sumDigits(container.count));
        System.out.println(sumDigits(12345));
        System.out.println(sumDigits(10));
        System.out.println(sumDigits(5059191));

        System.out.println(sumDigits2(container.count));
        System.out.println(sumDigits2(12345));
        System.out.println(sumDigits2(10));
        System.out.println(sumDigits2(5059191));

    }

    public static int sumDigits(Integer number) {
        int summ = 0;
        String num = number.toString();
        char[] chars = num.toCharArray();
        for (char ch : chars
        ) {
            summ = summ + Character.getNumericValue(ch);
        }
        return summ;
    }

    public static int sumDigits2(int number) {
        int num = number;
        int summ = 0;
        int tmp;
        while (num >= 1) {
            tmp = num % 10;
            num /= 10;
            summ += tmp;
        }
        return summ;
    }
}

