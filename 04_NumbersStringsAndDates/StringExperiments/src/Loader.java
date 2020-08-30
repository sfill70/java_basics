import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {

//        lesson 4.4.1
        for (int i = 65; i < 123; i++) {
            System.out.print((char) i);
            System.out.println(" - Код буквы: " + i);
            if (i == 90) {
                i = 97;
            }
        }

//        lesson 4.4.2
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String[] words = text.trim().split("\\s+");
        int sum = 0;
        for (String word : words
        ) {
            try {
                sum += Integer.parseInt(word);
            } catch (NumberFormatException e) {
            }
        }
        System.out.println(text);
        System.out.println("Общий заробеток: " + sum);

//        lesson 4.4.3
        System.out.println("Введите данные в формате - Фамилия Имя Отчество");
        String fio = "";
        try (BufferedReader reader = (new BufferedReader(new InputStreamReader(System.in)))) {
            fio = reader.readLine();
        } catch (NumberFormatException | IOException e) {
        }
        String[] fullName = fio.trim().split("\\s+");
        if (fullName.length == 3) {
            System.out.printf("Фамилия: " + "%s" + System.lineSeparator() + "Имя: " + "%s" + System.lineSeparator()
                    + "Отчество: " + "%s" + System.lineSeparator(), fullName[0], fullName[1], fullName[2]);
        } else {
            System.out.println("Неверный формат");
        }


//       lesson 4.4.4

        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe);

        String safe1 = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678>>> 8912", "***");
        System.out.println(safe);

        String safe2 = searchAndReplaceDiamonds2("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe2);

        String s = "<hbt<<wrhb>tw4hbtw4 <HBT> 4hgw4h4w <> w4hw4h4wh <hbtwc>>wechdt   svcwvcwe<1242532>mm<udkm";
        System.out.println(s);
        System.out.println(searchAndReplaceDiamonds(s,"!!!"));
        System.out.println(searchAndReplaceDiamonds2(s, "!!!"));

    }

    // Настроен проглатывать повторы кавычек
    static String searchAndReplaceDiamonds(String text, String placeholder) {
        return text.replaceAll("<.*?>+", placeholder);
    }

    //    Есть мнение, что RegEx это плохо
    static String searchAndReplaceDiamonds2(String text, String placeholder) {
        text = text.trim().replaceAll("<+", "<").replaceAll(">+", ">");
        StringBuilder result = new StringBuilder();
        int count = -1;
        boolean isGood = true;
        char ch;
        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            if (isGood && ch != 60) {
                result.append(ch);
            } else if (ch == 60) {
                count = i;
                isGood = false;
            } else if (ch == 62) {
                result.append(placeholder);
                isGood = true;
            }
        }
        if (!isGood) {
            result.append(text.substring(count));
        }
        return result.toString();
    }
}