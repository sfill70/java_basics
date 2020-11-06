//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Calendar;


public class Friend {

    public Friend() {
    }

    public static void main(String[] args) {
        boolean error = false;
        if (args.length == 0) {
            System.out.println("Привет! Я - простое консольное Java-приложение.\nЗапусти меня с параметром \"help\", и я расскажу тебе, что я умею :)");
        } else if (args.length == 1) {
            if (args[0].equals("help")) {
                System.out.println("Я умею отвечать на простые вопросы.\nЧтобы увидеть мой ответ, напиши в кавычках \nлюбую фразу или вопрос из следующего списка: \n- \"привет!\"\n- \"как дела?\"\n- \"как настроение?\"\n- \"чем занимаешься?\"\n- \"почему не спишь?\"\n- \"что ты делаешь сегодня вечером?\"\n- \"пока!\"\n");
            } else if (args[0].equals("привет!")) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(11);
                if (hour < 12) {
                    System.out.println("Доброе утро!");
                } else if (hour >= 12 && hour <= 18) {
                    System.out.println("Добрый день!");
                } else {
                    System.out.println("Добрый вечер!");
                }
            } else if (args[0].equals("как дела?")) {
                System.out.println("Хорошо, спасибо! :)");
            } else if (args[0].equals("как настроение?")) {
                System.out.println("Бодрое");
            } else if (args[0].equals("чем занимаешься?")) {
                System.out.println("Читаю пост на Хабр по Java");
            } else if (args[0].equals("почему не спишь?")) {
                System.out.println("Так обед же! Кто же спит в обед?");
            } else if (args[0].equals("что ты делаешь сегодня вечером?")) {
                System.out.println("Работаю :(");
            } else if (args[0].equals("пока!")) {
                System.out.println("Пока! Хорошего дня тебе! :)");
            } else {
                error = true;
            }
        } else {
            error = true;
        }

        if (error) {
            System.out.println("Прости, я не поняла, что ты меня спрашиваешь :(\nСпроси меня правильно. Вот примеры:\njava -jar simple.jar help\njava -jar simple.jar \"привет!\"\njava -jar simple.jar \"как дела?\"\n");
        }
    }
}
