import java.util.*;

public class Connection implements Comparable<Connection> {
    private Set<Station> stationSet;


    public Connection() {
        this.stationSet = new HashSet<>();
//        this.stationSet = new TreeSet<>();
    }

    public Set<Station> getStationSet() {
        return stationSet;
    }

    public void addStationSet(Station station) {
        stationSet.add(station);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Connection that = (Connection) o;

        return Objects.equals(stationSet, that.stationSet) /* this.hashCode() == that.hashCode()*/ /*&& Objects.equals(this.hashCode(), that.hashCode())*/;
    }

    @Override
    public int hashCode() {
        return stationSet != null ? stationSet.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Connection = " + " {(hashCode= " + hashCode() +
                ") stationSet=" + stationSet +
                '}';
    }

    @Override
    public int compareTo(Connection o) {

        int compare = stationSet.size() - o.getStationSet().size();
        if (compare == 0) {
            compare = stationSet.hashCode() - o.getStationSet().hashCode();
        }
        return compare;
    }
}
