import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;


public class HomeworkCalendar {
    public static void main(String[] args) {

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
        }
    }
}
