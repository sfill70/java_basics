import java.util.*;

public class JsonResponse {

    private List<Line> lines = new ArrayList<>();
    private Map<String, String[]> stations = new HashMap<>();
    private Set<Station[]> connections = new HashSet<>();

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Map<String, String[]> getStations() {
        return stations;
    }

    public void setStations(List<Line>lines) {
        Map<String, String[]> map = new HashMap<>();
        for (Line l:lines
             ) {
            map.put(l.getNumber(), l.getStations().stream().map(Station::getName).toArray(String[]::new));
        }
        this.stations = map;
    }

    public Set<Station[]> getConnections() {
        return connections;
    }

    public void setConnections(Set<Connection> connections) {
       Set<Station[]>set = new HashSet<>();
       for (Connection con:connections
            ) {
           set.add(con.getStationSet().toArray(new Station[con.getStationSet().size()]));
       }
       this.connections = set;
    }
}
