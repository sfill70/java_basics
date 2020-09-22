package Company;

import Employee.Employee;
import Employee.Operator;
import Employee.Manager;
import Employee.TopManager;
import Employee.Developer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LaborResources {
    private HashMap<String, Employee> laborResources = new HashMap<String, Employee>();

    public HashMap<String, Employee> getLaborResources() {
        return new HashMap<String, Employee>(laborResources);
    }

    public void addLaborResources(Employee employee) {
        laborResources.put(employee.getId(), employee);
    }

    //Набор указаного количества первонала указанной профессии из свободных трудовых ресурсов (laborResources)
    // если не набрали создаем новых
    protected HashSet<Employee> getStaffSet(Class<? extends Employee> classToFire, int number) {
        int count = 0;
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        for (Map.Entry<String, Employee> entry : Company.getLaborResources().entrySet()
        ) {
            Employee employee = entry.getValue();
            if (employee.getClass() == (classToFire)) {
                if (employee.isWork()) {
                    employeeHashSet.add(employee);
                    count++;

                }
            }
            if (count >= number) {
                break;
            }
            /*try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        if (count > 0) {
            System.out.println("Набрано из трудовых ресурсов - " + count + " " + classToFire);
        }
        if (count < number) {
            for (int i = 0; i < number - count; i++) {
                try {
                    employeeHashSet.add(classToFire.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Создано новых рабочих - " + (number - count) + " " + classToFire);
        }
        return employeeHashSet;
    }

    //Набор всего штата сотрудников
    protected HashSet<Employee> getEmployeeSet(int operator, int manager, int topManager, int developer) {
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        employeeHashSet.addAll(getStaffSet(Operator.class, operator));
        employeeHashSet.addAll(getStaffSet(Manager.class, manager));
        employeeHashSet.addAll(getStaffSet(TopManager.class, topManager));
        employeeHashSet.addAll(getStaffSet(Developer.class, topManager));
        return employeeHashSet;
    }
}
