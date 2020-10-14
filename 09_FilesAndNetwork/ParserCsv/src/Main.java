import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Main {
    private final static Locale LOCALE = new Locale("ru");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    private final static char CSV_SEPARATOR = ',';
    private final static char QUOTE = '"';
    static String root = System.getProperty("user.dir");
    static String sDirSeparator = System.getProperty("file.separator");
    private static final String PATH_FILE = root + sDirSeparator + "ParserCsv" + sDirSeparator +
            "resources" + sDirSeparator + "movementList.csv";
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Marker INVALID_LINE_MARKER = MarkerManager.getMarker("VIEW_INVALID_ARRAY");
    private static int posDebit = 0;
    private static int posCredit = 0;
    private static int posOperation = 0;
    private static int posDate = 0;
    private static int posMcc = 0;
    private static List<Movement> movementList = new ArrayList<>();

    public static void main(String[] args) {
        ReaderMovement readerMovement = new ReaderMovement();
        List<String[]> listArray = readerMovement.getMovementList(PATH_FILE, CSV_SEPARATOR, QUOTE);
        int verificationArray = takeLengthArray(readerMovement.getArrayLength());
        for (String[] array : listArray
        ) {
            if (posCredit == 0 || posDebit == 0) {
                getPositionInArray(array);
                continue;
            }
            //Массивы которые отличаются по длинне от самой распространненой длинны массива пропускаем
            if (array.length != verificationArray) {
                LOGGER.warn(INVALID_LINE_MARKER, "length array = " + array.length + " / string line -> "
                        + Arrays.toString(array));
                continue;
            }
            String nameOrganisation = "";
            String mcc = "";
            double income = 0;
            double expense = 0;
            LocalDate date = LocalDate.of(1, 1, 1);
            if (array[posCredit] != null) {
                expense = getMovement(array[posCredit], "Credit", array);
            }
            if (array[posOperation] != null) {
                nameOrganisation = array[posOperation].replaceAll("\\\\+", "/").replaceAll("[\\s]+", "").replaceAll("(?u)[^\\p{Alpha}/]", "");
            }
            if (array[posDate] != null) {
                date = getLocalDate(array[posDate]);
            }
            if (array[posDebit] != null) {
                income = getMovement(array[posDebit], "Debit", array);
            }
            if (array[posMcc] != null) {
                mcc = array[posMcc];
            }
            movementList.add(new Movement(nameOrganisation, date, income, expense, mcc));
        }
        double sumExpense = movementList.stream().map(Movement::getExpense).reduce((Double::sum)).orElse(0.0);
        double sumIncome = movementList.stream().map(Movement::getIncome).reduce((Double::sum)).orElse(0.0);
        System.out.println("Сумма расходов : " + FORMAT.format(sumExpense));
        System.out.println("Сумма расходов : " + FORMAT.format(sumIncome));
        System.out.println("Суммы расходов по организациям:");
        movementList.stream().collect(Collectors.groupingBy((Movement::getName),
                Collectors.summingDouble(Movement::getExpense)))
                .forEach((o, e) -> System.out.println(o + " - " + FORMAT.format(e) + " руб."));
    }

    private static LocalDate getLocalDate(String s) {
        try {
            LocalDate date;
            String[] arrayDate;
            arrayDate = s.trim().split("[.]");
            date = LocalDate.of(Integer.parseInt(20 + arrayDate[2]), Integer.parseInt(arrayDate[1]),
                    Integer.parseInt(arrayDate[0]));
            return date;
        } catch (NumberFormatException e) {
            LOGGER.error(e + " - " + Arrays.toString(e.getStackTrace()) + " error in -> {} ", s);
            return LocalDate.of(1, 1, 1);
        }
    }


    private static void getPositionInArray(String[] array) {
        for (int i = 0; i < array.length; i++) {

            if (array[i].equalsIgnoreCase("приход")) {
                posDebit = i;
            }
            if (array[i].equalsIgnoreCase("расход")) {
                posCredit = i;
            }
            if (array[i].contains("Описание")) {
                posOperation = i;
            }
            if (array[i].contains("Дата")) {
                posDate = i;
            }
            if (array[i].equalsIgnoreCase("Референс проводки")) {
                posMcc = i;
            }

        }
    }

    private static double getMovement(String s, String operation, String[] array) {
        double movement = 0;
        try {
            movement = Double.parseDouble(s.replace(",", "."));
        } catch (NumberFormatException e) {
            LOGGER.error("error in -> {} parse -> {} string line -> {}", operation, s, Arrays.toString(array));
            e.printStackTrace();
        }
        return movement;
    }

    // метод находит самую частую длинну массива, делаем вывод, что это необходимый формат данных
    private static int takeLengthArray(int[] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[index]) {
                index = i;
            }
        }
        return index;
    }
}

