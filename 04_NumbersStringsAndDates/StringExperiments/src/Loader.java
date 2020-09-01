import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


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
        StringBuilder resultBuilder = new StringBuilder();
        String target = "1234567890";
        char[] chars = text.toCharArray();
        int sum = 0;
        char ch;
        for (int i = 0; i < chars.length; i++) {
            ch = chars[i];
            if (target.contains(Character.toString(ch))) {
                resultBuilder.append(ch);
                if (i == chars.length - 1) {
                    sum += Integer.parseInt(resultBuilder.toString());
                    resultBuilder.setLength(0);
                }
            } else if (resultBuilder.length() != 0) {
                sum += Integer.parseInt(resultBuilder.toString());
                resultBuilder.setLength(0);
            }
        }
        System.out.println("Общий зароботок: " + sum);
        System.out.println(text);
        System.out.println((int) ' ');


//        lesson 4.4.3
        System.out.println("Введите данные в формате - Фамилия Имя Отчество");
        String fio = "";
        ArrayList<String> fullName = new ArrayList<>();
        try (BufferedReader reader = (new BufferedReader(new InputStreamReader(System.in)))) {
            fio = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] chars1 = fio.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            ch = chars1[i];
            if (ch != 32) {
                resultBuilder.append(ch);
                if (i == chars1.length - 1) {
                    fullName.add(resultBuilder.toString());
                    resultBuilder.setLength(0);
                }
            } else if (resultBuilder.length() != 0) {
                fullName.add(resultBuilder.toString());
                resultBuilder.setLength(0);
            }
        }
        if (fullName.size() == 3) {
            System.out.printf("Фамилия: " + "%s" + System.lineSeparator() + "Имя: " + "%s" + System.lineSeparator()
                    + "Отчество: " + "%s" + System.lineSeparator(), fullName.get(0), fullName.get(1), fullName.get(2));
        } else {
            System.out.println("Неверный формат");
        }


//       lesson 4.4.4

        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe);

        String safe1 = searchAndReplaceDiamonds2("Номер кредитной карты <4008 1234 5678>>> 8912", "***");
        System.out.println(safe1);

        String safe2 = searchAndReplaceDiamonds2("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe2);

        String s = "<hbt<<wrhb>tw4hbtw4 <HBT> 4hgw4h4w <> w4hw4h4wh <hbtwc>>wechdt   svcwvcwe<1242532>mm<udkm>>>";
        System.out.println(s);
        System.out.println(searchAndReplaceDiamonds(s, "!!!"));
        System.out.println(searchAndReplaceDiamonds2(s, "!!!"));

    }

    // Настроен проглатывать повторы кавычек
    static String searchAndReplaceDiamonds(String text, String placeholder) {
        return text.replaceAll("<.*?>+", placeholder);
    }

    //    Есть мнение, что RegEx это плохо
    static String searchAndReplaceDiamonds2(String text, String placeholder) {
        StringBuilder result = new StringBuilder();
        int count = -1;
        boolean canAdd = true;
        char ch;
        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            if (canAdd && ch != 60) {
                result.append(ch);
            } else if (ch == 60) {
                count = i;
                canAdd = false;
            } else if ((ch == 62 && i + 1 < text.length() && text.charAt(i + 1) != 62) || (ch == 62 && i == text.length() - 1)) {
                result.append(placeholder);
                canAdd = true;
            }
        }
        if (!canAdd) {
            result.append(text.substring(count));
        }
        return result.toString();
    }
}