import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;

import java.util.Collection;
import java.util.Date;

import static com.skillbox.airport.Flight.Type.DEPARTURE;

public class Main {
    final static long HOUR = 2 * 60 * 60 * 1000;

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        //класс даты
        System.out.println(airport.getTerminals().get(1).getFlights().get(1).getDate().getClass().getCanonicalName());

        Date date = new Date(System.currentTimeMillis());
        Date beforeDate = new Date(System.currentTimeMillis() + HOUR);

        airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(s -> s.getType().equals(DEPARTURE))
                .filter(k -> k.getDate().after(date))
                .filter(n -> n.getDate().before(beforeDate))
                .forEach((e) -> System.out.printf("Дата вылета - %s : Модель самолета - %s",e.getDate(), e.getAircraft() + System.lineSeparator()));
    }
}
