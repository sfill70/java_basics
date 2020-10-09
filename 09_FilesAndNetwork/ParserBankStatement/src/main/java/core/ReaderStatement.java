package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class ReaderStatement {
/*    public static void main(String[] args) {
        System.out.println(Double.parseDouble("14243.005"));
    }*/
    private static final Logger LOGGER = LogManager.getLogger(ReaderStatement.class);
    public static List<String> readerFile(String stPath) {
        List<String> lines = null;
        Path path = Paths.get(stPath);
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            LOGGER.error(e + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
        return lines;
    }
}
