import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Integer.compare;

public class Main {
    /*String root = "D:\\IdeaProjects\\java_basics\\07_AdvancedOOPFeatures";
    String path = "D:\\IdeaProjects\\java_basics\\07_AdvancedOOPFeatures\\LambdaExpressions\\data";*/
    static String getRoot = System.getProperty("user.dir");
    static String dirSeparator = System.getProperty("file.separator");
    private static String staffFile = getRoot + dirSeparator + "LambdaExpressions" + dirSeparator + "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {

        ArrayList<Employee> staff = loadStaffFromFile();

        Comparator<Employee> comparator = (Employee o1, Employee o2) -> {
            int compareSalary = (o1.getSalary().compareTo(o2.getSalary().compareTo(o2.getName().compareTo(o1.getName()))));
            if ((compareSalary == 0)) {
                return o1.getName().compareTo(o2.getName());
            }
            return compareSalary;
        };
        staff.sort(comparator);

/*
        default Comparator<T> thenComparing(Comparator<? super T> other) {
            Objects.requireNonNull(other);
            return (Comparator<T> & Serializable) (c1, c2) -> {
                int res = compare(c1, c2);
                return (res != 0) ? res : other.compare(c1, c2);
            };
        }*/

        //Покороче
        staff.sort((Employee o1, Employee o2) -> {
            int compareSalary = o1.getSalary().compareTo(o2.getSalary());
            return compareSalary == 0 ? o1.getName().compareTo(o2.getName()) : compareSalary;
        });


        for (Employee e : staff
        ) {
            System.out.println(e);

        }

        System.out.println("------------------/--------------------");

        staff.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));

        for (Employee e : staff
        ) {
            System.out.println(e);

        }
    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}