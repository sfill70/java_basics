import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HomeworkCalendar {
    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY - EEE", Locale.ENGLISH);
        Calendar victoryDay = Calendar.getInstance(/*Locale.ENGLISH*/);
        victoryDay.set(1945, Calendar.MAY, 9);

        for (int i = 1945; i < 2021; i++) {
            System.out.print(i - 1945 + " - ");
            System.out.println(dateFormat.format(victoryDay.getTime()));
            victoryDay.add(Calendar.YEAR, 1);
        }
    }
}
