import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;


public class NewLoader {
    public static void main(String[] args) {


//        lesson 4.5.1
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        String[] words = text.trim().split("\\s+");
//        Pattern pattern = Pattern.compile("[0-9]+");
        int sum = 0;
        for (String word : words) {
            if (Pattern.matches("[0-9]+", word)) {
                sum += Integer.parseInt(word);
            }
        }
        System.out.println(text);
        System.out.println("Lesson 4.5.1 v1.0");
        System.out.println("Общий зароботок: " + sum);

//        Lesson 4.5.1
        text = text.trim().replaceAll("[^0-9[\\s]]", "").trim();
        String[] salaries = text.split("\\s+");
        int sumSalaries = 0;
        for (String salary : salaries
        ) {
            sumSalaries += Integer.parseInt(salary);
        }
        System.out.println("Lesson 4.5.1 v2.0");
        System.out.println("Общий зароботок друзей составил: " + sumSalaries);

//        Lesson 4.5.2
        String tragedy;
        try {
//            Path workingDirectory = Paths.get("").toAbsolutePath();
            String root = System.getProperty("user.dir");
            String sDirSeparator = System.getProperty("file.separator");
            byte[] bytes = Files.readAllBytes(Paths.get(root + sDirSeparator + "04_NumbersStringsAndDates" + sDirSeparator +
                    "StringExperiments" + sDirSeparator + "src" + sDirSeparator + "shakespeare.txt"));
            tragedy = new String(bytes);
        } catch (IOException e) {
            tragedy = "Romeo and Juliet William Shakespeare Romeo and Juliet is the world's most " +
                    "famous drama of tragic young love. Defying the feud which divides their families, " +
                    "Romeo and Juliet enjoy the fleeting rapture of courtship, marriage and sexual fulfillment; " +
                    "but a combination of old animosities and new coincidences brings them to suicidal deaths. " +
                    "This play offers a rich mixture of romantic lyricism, bawdy comedy, intimate harmony and " +
                    "sudden violence. Long successful in the theater, it has also generated numerous operas, " +
                    "ballets and films; and these have helped to make Romeo and Juliet perennially topical " +
                    "ROMEO AND JULIET by WILLIAM SHAKESPEARE";
        }
        String[] newWords = tragedy.replaceAll("[^\\s\\w]", "").trim().split("\\s+");
        for (int i = 0; i < 20; i++) {
            System.out.println(newWords[i]);
        }
        System.out.println("Lesson 4.5.2");
        System.out.println("Выведено 20 слов из " + newWords.length);


//        lesson 4.5.3
        System.out.println("Введите данные в формате - Фамилия Имя Отчество");
        String fio = "";
        BufferedReader reader = (new BufferedReader(new InputStreamReader(System.in)));
        try {
            fio = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] fullName = fio.trim().split("\\s+");
        if (fullName.length == 3) {
            System.out.printf("Фамилия: " + "%s" + System.lineSeparator() + "Имя: " + "%s" + System.lineSeparator()
                    + "Отчество: " + "%s" + System.lineSeparator(), fullName[0], fullName[1], fullName[2]);
        } else {
            System.out.println("Неверный формат");
        }

//        lesson 4.5.4
        System.out.println("Введите телефон в желательно в верном формате - 10 или 11 цифр");
        String phone = "";

        try {
            phone = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        phone = phone.trim().replaceAll("[^0-9]", "").trim();

        if (phone.length() > 11 || phone.length() < 10) {
            System.out.println("Неверный формат");
        } else if (phone.length() == 10) {
            System.out.println("7" + phone);
        } else {
            if (phone.startsWith("8")) {
                System.out.println("7" + phone.substring(1));
            } else if (phone.startsWith("7")) {
                System.out.println(phone);
            } else {
                System.out.println("Неверный формат");
            }
        }

//       lesson 4.5.5

        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe);

        String safe1 = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678>>> 8912", "***");
        System.out.println(safe1);

        String safe2 = searchAndReplaceDiamonds2("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe2);

        String s = "<hbt<<wrhb>tw4hbtw4 <HBT> 4hgw4h4w <> w4hw4h4wh <hbtwc>>wechdt   svcwvcwe<1242532>mm<udkm";
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