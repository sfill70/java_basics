import java.util.Objects;

public class Station implements Comparable<Station> {

    private String name;
    private String line;

    public Station() {
    }

    public Station(String name, String numberLine) {
        this.name = name;
        this.line = numberLine;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", numberLine='" + line + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;

        Station station = (Station) o;

        if (!Objects.equals(name, station.name)) return false;
        return Objects.equals(line, station.line);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (line != null ? line.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Station o) {

        int compare = line.length() - o.getLine().length();
        if (compare == 0) {
            compare = line.compareToIgnoreCase(o.getLine());
        }
        return compare == 0 ? name.compareTo(o.getName()) : compare;
    }


}
