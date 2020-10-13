import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReaderMovement {
    private int[] arrayLength = new int[22];
    private static final Logger LOGGER = LogManager.getLogger(ReaderMovement.class);

    public List<String[]> getMovementList(String pathFile, char csvSeparator, char quote) {
        List<String[]> listArray = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(pathFile), csvSeparator, quote, 0)) {
            //Read CSV line by line and use the string array as you want
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                //Verifying the read data here
                arrayLength[nextLine.length] = arrayLength[nextLine.length] + 1;
                listArray.add(nextLine);
            }
        } catch (Exception e) {
            LOGGER.error(e + " - " + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
        return listArray;
    }

    public int[] getArrayLength() {
        return arrayLength;
    }
}