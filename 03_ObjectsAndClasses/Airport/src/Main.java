import com.skillbox.airport.Airport;
import com.skillbox.airport.Aircraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        // Получение сведений о медодах класса
        Class<?> cls = airport.getClass();
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            System.out.println("Method name : " + method.getName());
            System.out.println("Return type : " +
                    method.getReturnType().getName());

            Class<?>[] params = method.getParameterTypes();
            System.out.print("Parameters : ");
            for (Class<?> paramType : params) {
                System.out.print(" " + paramType.getName());
            }
            System.out.println();
        }

        List<Aircraft> aircraftList = airport.getAllAircrafts();
//        System.out.println(aircraftList.get(0).getClass().getCanonicalName());
//        for (Aircraft air : aircraftList
//        ) {
//            System.out.println(air);
//        }

        System.out.println("Количество самолетов в аэропорту - " + aircraftList.size());


    }
}
