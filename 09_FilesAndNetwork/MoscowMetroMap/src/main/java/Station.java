import java.util.Objects;

public class Station implements Comparable<Station> {

    private String name;
    private String numberLine;

    public Station() {
    }

    public Station(String name, String numberLine) {
        this.name = name;
        this.numberLine = numberLine;
    }

    public String getName() {
        return name;
    }

    public String getNumberLine() {
        return numberLine;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", numberLine='" + numberLine + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;

        Station station = (Station) o;

        if (!Objects.equals(name, station.name)) return false;
        return Objects.equals(numberLine, station.numberLine);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (numberLine != null ? numberLine.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Station o) {

        int compare = numberLine.length() - o.getNumberLine().length();
        if (compare == 0) {
            compare = numberLine.compareToIgnoreCase(o.getNumberLine());
        }
        return compare == 0 ? name.compareTo(o.getName()) : compare;
    }


}
