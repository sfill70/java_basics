import au.com.bytecode.opencsv.CSVReader;
import core.ReaderStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Marker INVALID_LINE_MARKER = MarkerManager.getMarker("VIEW_INVALID_ARRAY");
    //    private static final Marker VIEW_DEBIT = MarkerManager.getMarker("VIEW_DEBIT");
    private static final Marker VIEW_CREDIT = MarkerManager.getMarker("VIEW_CREDIT");
    private final static Locale LOCALE = new Locale("ru");
    private final static NumberFormat FORMAT = NumberFormat.getInstance(LOCALE);
    private final static String CSV_SEPARATOR = ",";
    static String root = System.getProperty("user.dir");
    static String sDirSeparator = System.getProperty("file.separator");
    private static final String PATH_FILE = root + sDirSeparator + "ParserBankStatement" + sDirSeparator + "src" + sDirSeparator +
            "main" + sDirSeparator + "resources" + sDirSeparator + "movementList.csv";

    private static List<String> linesFile = ReaderStatement.readerFile(PATH_FILE);
    private static HashMap<String, Double> organisation = new HashMap<>();
    private static List<String[]> listArray = new ArrayList<>();

    private static int[] arrayLength = new int[22];
    private static int posDebit = 0;
    private static int posCredit = 0;
    private static int posOperation = 0;
    private static double debit = 0.00;
    private static double credit = 0.00;
    private static double movement = 0.00;

    public static void main(String[] args) throws IOException {

        /*//Build reader instance
        //Read data.csv
        //Default seperator is comma
        //Default quote character is double quote
        //Start reading from line number 2 (line numbers start from zero)
        CSVReader reader = new CSVReader(new FileReader(PATH_FILE), ',' , '"' , 0);
        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            //Verifying the read data here
            arrayLength[nextLine.length] = arrayLength[nextLine.length] + 1;
            listArray.add(nextLine);
        }*/

        //Формируем из строки массив с данными,
        // данные в кавычках "...." помещаем в ячейку целиком
        //остальные разбиваем по csv разделителю
        for (String line : linesFile) {
//            System.out.println(line.replaceAll("\".+,?.+,?.+\"","!!!!!!!!!!!!!!!!!!!!!!!"));
//            System.out.println(line);
            String[] array = getArray(line.trim().split("\""));
            arrayLength[array.length] = arrayLength[array.length] + 1;
            listArray.add(array);
        }
        for (String[] array : listArray
        ) {
            // Полкучаем позиции в массиве интересующих нас данными
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

            //Массивы которые отличаются по длинне от самой распространненой длинны массива пропускаем
            if (array.length != takeLengthArray(arrayLength)) {
                LOGGER.warn(INVALID_LINE_MARKER, "length array = " + array.length + " / string line -> "
                        + Arrays.toString(array));
                continue;
            }
            if (array[posCredit] != null) {
                movement = getMovement(array[posCredit], "Credit", array);
                credit = credit + movement;
                if (movement != 0) {
                    //Оставил слеши без них не читаемо на пробелы не стал менять
                    String operation = array[posOperation].replaceAll("(?u)[^\\p{Alpha}\\\\]", "");
                    organisation.put(operation, organisation.getOrDefault(operation, 0.00) + movement);
                    LOGGER.info(VIEW_CREDIT, "String -> " + array[posCredit] + " / movement -> "
                            + movement + " / operation ->" + operation);
                }
            }
            if (array[posDebit] != null && movement == 0) {
                debit = debit + getMovement(array[posDebit], "Debit", array);
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

    private static String[] getArray(String[] array) {
        if (array == null || array.length == 0) {
            return new String[0];
        }
        String[] result = null;
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                String[] temp = array[i].trim().split(CSV_SEPARATOR);
                if (i + 1 < array.length) {
                    result = new String[temp.length + 1];
                    System.arraycopy(temp, 0, result, 0, temp.length);
                    result[result.length - 1] = array[i + 1];
                } else {
                    result = temp;
                }
            }
        }
        return result;
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
