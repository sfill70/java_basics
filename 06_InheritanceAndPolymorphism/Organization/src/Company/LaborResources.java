package Company;

import Employee.Employee;
import Employee.Operator;
import Employee.Manager;
import Employee.TopManager;
import Employee.Developer;

import java.util.*;

public class LaborResources {
    private static HashMap<String, Employee> laborResources = new HashMap<String, Employee>();

    public static HashMap<String, Employee> getLaborResources() {
        return new HashMap<String, Employee>(laborResources);
    }

    public static String getIdEmployee() {
        String stId;
        do {
            UUID id = UUID.randomUUID();
            stId = id.toString().substring(id.toString().length() - 12);
        } while (getLaborResources().containsKey(stId));
        return stId;
    }


    public static void addLaborResources(Employee employee) {
        laborResources.put(employee.getId(), employee);
    }

    public static HashSet<Employee> getStaffSet(LinkedHashMap<Staff, Integer> employeeMap) {
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        for (Map.Entry<Staff, Integer> ent : employeeMap.entrySet()) {
            int count = 0;
            for (Map.Entry<String, Employee> entry : Company.getLaborResources().entrySet()
            ) {
                Employee employee = entry.getValue();
                if (employee.getClass() == ent.getKey().getTitle()) {
                    if (employee.isWork()) {
                        employeeHashSet.add(employee);
                        count++;
                    }
                }
                if (count >= ent.getValue()) {
                    break;
                }
            }
            if (count > 0) {
                System.out.println("Набрано из трудовых ресурсов - " + count + " " + ent.getKey());
            }
            if (count < ent.getValue()) {
                for (int i = 0; i < ent.getValue() - count; i++) {
                    if (ent.getKey() == Staff.OPERATOR) {
                        employeeHashSet.add(new Operator());
                    }
                    if (ent.getKey() == Staff.MANAGER) {
                        employeeHashSet.add(new Manager());
                    }
                    if (ent.getKey() == Staff.TOP_MANAGER) {
                        employeeHashSet.add(new TopManager());
                    }
                    if (ent.getKey() == Staff.DEVELOPER) {
                        employeeHashSet.add(new Developer());
                    }
                }
                System.out.println("Создано новых рабочих - " + (ent.getValue() - count) + " " + ent.getKey());
            }
        }
        return employeeHashSet;
    }


    public static HashSet<Employee> getStaffSet(HashMap<Class<? extends Employee>, Integer> employeeMap) {

        HashSet<Employee> employeeHashSet = new HashSet<Employee>();

        for (Map.Entry<Class<? extends Employee>, Integer> ent : employeeMap.entrySet()) {
            int count = 0;
            for (Map.Entry<String, Employee> entry : Company.getLaborResources().entrySet()
            ) {
                Employee employee = entry.getValue();
                if (employee.getClass() == ent.getKey()) {
                    if (employee.isWork()) {
                        employeeHashSet.add(employee);
                        count++;
                    }
                }
                if (count >= ent.getValue()) {
                    break;
                }
            }
            if (count > 0) {
                System.out.println("Набрано из трудовых ресурсов - " + count + " " + ent.getKey());
            }
            if (count < ent.getValue()) {
                for (int i = 0; i < ent.getValue() - count; i++) {
                    try {
                        employeeHashSet.add(ent.getKey().newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Создано новых рабочих - " + (ent.getValue() - count) + " " + ent.getKey());
            }
        }
        return employeeHashSet;
    }

    //Набор указаного количества первонала указанной профессии из свободных трудовых ресурсов (laborResources)
    // если не набрали создаем новых
    public static HashSet<Employee> getStaffSet(Class<? extends Employee> classToFire, int number) {
        HashMap<Class<? extends Employee>, Integer> employeeMap = new HashMap<>();
        employeeMap.put(classToFire, number);

        HashSet<Employee> employeeHashSet = new HashSet<Employee>();

        for (Map.Entry<Class<? extends Employee>, Integer> ent : employeeMap.entrySet()) {
            int count = 0;
            for (Map.Entry<String, Employee> entry : Company.getLaborResources().entrySet()
            ) {
                Employee employee = entry.getValue();
                if (employee.getClass() == ent.getKey()) {
                    if (employee.isWork()) {
                        employeeHashSet.add(employee);
                        count++;
                    }
                }
                if (count >= number) {
                    break;
                }
            }
            if (count > 0) {
                System.out.println("Набрано из трудовых ресурсов - " + count + " " + ent.getKey());
            }
            if (count < number) {
                for (int i = 0; i < number - count; i++) {
                    try {
                        employeeHashSet.add(ent.getKey().newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Создано новых рабочих - " + (number - count) + " " + ent.getKey());
            }
        }
        return employeeHashSet;
    }

    //Набор всего штата сотрудников
    public static HashSet<Employee> getEmployeeSet(int operator, int manager, int topManager, int developer) {
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        employeeHashSet.addAll(getStaffSet(Operator.class, operator));
        employeeHashSet.addAll(getStaffSet(Manager.class, manager));
        employeeHashSet.addAll(getStaffSet(TopManager.class, topManager));
        employeeHashSet.addAll(getStaffSet(Developer.class, developer));
        return employeeHashSet;
    }
}
