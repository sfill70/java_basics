import core.ReaderStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.text.NumberFormat;
import java.util.*;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Marker INVALID_LINE_MARKER = MarkerManager.getMarker("VIEW_INVALID_ARRAY");
    private final static Locale LOCALE = new Locale("ru");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    private final static String CSV_SEPARATOR = ",";
    static String root = System.getProperty("user.dir");
    static String sDirSeparator = System.getProperty("file.separator");
    private static final String PATH_FILE = root + sDirSeparator + "ParserBankStatement" + sDirSeparator + "src" + sDirSeparator +
            "main" + sDirSeparator + "resources" + sDirSeparator + "movementList.csv";

    public static void main(String[] args) {
        List<String> linesFile = ReaderStatement.readerFile(PATH_FILE);
        HashMap<String, Double> organisation = new HashMap<>();

        int[] arrayLength = new int[22];
        int posDebit = 0;
        int posCredit = 0;
        int posOperation = 0;
        double debit = 0.00;
        double credit = 0.00;

        for (String line : linesFile
        ) {
            String[] array = line.trim().split(CSV_SEPARATOR);
            if (posCredit == 0 || posDebit == 0) {
                for (int i = 0; i < array.length; i++) {
                    if (array[i].equalsIgnoreCase("приход"/*"дебет"*/)) {
                        posDebit = i;
                    }
                    if (array[i].equalsIgnoreCase("расход"/*"кредит"*/)) {
                        posCredit = i;
                    }
                    if (array[i].equalsIgnoreCase("описание операции")) {
                        posOperation = i;
                    }
                }
                continue;
            }
            arrayLength[array.length] = arrayLength[array.length] + 1;
            if (array.length != takeLengthArray(arrayLength)) {
                LOGGER.warn(INVALID_LINE_MARKER, "length array = " + array.length + " / string line -> "
                        + Arrays.toString(array));
                continue;
            }
            if (array[posDebit] != null && array[posDebit].length() > 0) {
                debit = debit + getMovement(array[posDebit], "Debit", array);
            }
            if (array[posCredit] != null && array[posCredit].length() > 0) {
                double movement = getMovement(array[posCredit], "Credit", array);
                credit = credit + movement;
                //Оставил слеши без них не читаемо на пробелы не стал менять
                String operation = array[posOperation].replaceAll("(?u)[^\\p{Alpha}\\\\]", "");
                organisation.put(operation, organisation.getOrDefault(operation, 0.00) + movement);
            }
        }
        System.out.println("Сумма расходов : " + FORMAT.format(credit));
        System.out.println("Сумма доходов :" + FORMAT.format(debit));
        System.out.println("Суммы расходов по организациям:");
        for (Map.Entry<String, Double> entry : organisation.entrySet()
        ) {
            System.out.println(entry.getKey() + " - " + FORMAT.format(entry.getValue()) + " руб.");
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
