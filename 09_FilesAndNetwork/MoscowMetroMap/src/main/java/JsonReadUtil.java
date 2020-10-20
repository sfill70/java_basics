import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class JsonReadUtil {

    protected static void printJson(String pathFile) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile(pathFile));
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        JSONArray connectionObject = (JSONArray) jsonData.get("connections");
        System.out.println("Количество станций на каждой линии: ");
        stationsObject.keySet().stream()
                .sorted(Comparator.comparing(s -> s.toString().length()).thenComparing(s -> s.toString()))
                .forEach(lineNumberObject ->
                {
                    JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
                    int stationsOnLineCount = stationsArray.size();
                    System.out.println(lineNumberObject + " : " + stationsOnLineCount);
                });
        System.out.println("Количество переходов (узлов) - " + connectionObject.size());
    }

    private static String getJsonFile( String pathFile) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathFile));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
