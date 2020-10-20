import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;


public class JsonWriteUtil {

    protected static void writeDataJson(List<Line> linesList, Set<Connection> connectionSet, String pathFile) throws IOException {
        JSONObject metro = new JSONObject();
        JSONArray lines = getJsonLines(linesList);
        JSONObject stations = getJsonStations(linesList);
        JSONArray connections = getJsonConnections(connectionSet);
        metro.put("lines", lines);
        metro.put("stations", stations);
        metro.put("connections", connections);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Files.write(Paths.get(pathFile), gson.toJson(metro).getBytes());
    }


    private static JSONArray getJsonLines(List<Line> lineList) {
        JSONArray lines = new JSONArray();
        for (Line line : lineList) {
            JSONObject l = new JSONObject();
            l.put("number", line.getNumber());
            l.put("name", line.getName());
            lines.add(l);
        }
        return lines;
    }

    private static JSONObject getJsonStations(List<Line> lineList) {
        JSONObject stations = new JSONObject();
        for (Line line : lineList) {
            JSONArray list = new JSONArray();
            line.getStations().forEach(station -> list.add(station.getName()));
            stations.put(line.getNumber(), list);
        }
        return stations;
    }

    private static JSONArray getJsonConnections(Set<Connection> setConnections) {
        JSONArray allConnections = new JSONArray();
        for (Connection connection : setConnections) {
            JSONArray oneConnection = new JSONArray();
            connection.getStationSet().forEach(station -> {
                JSONObject connectedStation = new JSONObject();
                connectedStation.put("line", station.getNumberLine());
                connectedStation.put("station", station.getName());
                oneConnection.add(connectedStation);
            });

            allConnections.add(oneConnection);
        }
        return allConnections;
    }
}
