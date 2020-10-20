import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line>{
    private String number;
    private String name;
    private List<Station> stations;

    public Line() {
    }

    public Line(String number, String name) {
        this.number = number;
        this.name = name;
        stations = new ArrayList<Station>();
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public Station getStation(String name) {
        for (Station st : stations
        ) {
            if (st.getName().equalsIgnoreCase(name)) {
                return st;
            }
        }
        return new Station("ErrorStation", "ErrorStation");
    }

    @Override
    public String toString() {
        return "Line{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", stations=" + stations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line)) return false;

        Line line = (Line) o;

        if (getNumber() != null ? !getNumber().equals(line.getNumber()) : line.getNumber() != null) return false;
        if (getName() != null ? !getName().equals(line.getName()) : line.getName() != null) return false;
        return stations != null ? stations.equals(line.stations) : line.stations == null;
    }

    @Override
    public int hashCode() {
        int result = getNumber() != null ? getNumber().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (stations != null ? stations.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Line o) {
        int compare = number.length() - o.getNumber().length();
        if (compare == 0) {
            compare = name.compareTo(o.name);
        }
        return compare;
    }
}
