import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;


public class HomeworkCalendar {
    public static void main(String[] args) {

        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy - EEE", new Locale("en"));
        LocalDate birthday = LocalDate.of(1961, 4, 12);
        LocalDate today1 = LocalDate.now();
        int i = 0;
        while (today1.isAfter(birthday)) {
            System.out.print(i + " - ");
            System.out.println(printFormat.format(birthday));
            birthday = birthday.plusYears(1);
            i++;
        }

        String date = "1234,56,67";
        String[] dateArray = date.trim().split("\\.|\\s|,");
        System.out.println(Arrays.asList(dateArray));
        String da = "1232xffx.. ";
        System.out.println(da.replaceAll("[^0-9]", ""));


       /* Базовый вариант
       DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - EEE", Locale.ENGLISH);
        Calendar birthday = Calendar.getInstance(Locale.ENGLISH);
        Calendar today = Calendar.getInstance(Locale.ENGLISH);
        birthday.set(1945, Calendar.MAY, 9);
        int i = 0;
        while (today.after(birthday)) {
            System.out.print(i + " - ");
            System.out.println(dateFormat.format(birthday.getTime()));
            birthday.add(Calendar.YEAR, 1);
            i++;
        }*/
    }
}
