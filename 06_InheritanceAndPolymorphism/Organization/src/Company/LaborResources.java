package Company;

import Employee.Employee;
import Employee.Operator;
import Employee.Manager;
import Employee.TopManager;

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
    protected HashSet<Employee> getStaffSet(Staff staff, int number) {
        int count = 0;
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        for (Map.Entry<String, Employee> entry : Company.getLaborResources().entrySet()
        ) {
            Employee employee = entry.getValue();
            if (staff.equals(Staff.MANAGER)) {
                if (employee instanceof Manager) {
                    if (employee.isWork()) {
                        employeeHashSet.add(employee);
                        count++;
                    }
                }
            } else if (staff.equals(Staff.TOP_MANAGER)) {
                if (employee instanceof TopManager) {
                    if (employee.isWork()) {
                        employeeHashSet.add(employee);
                        count++;
                    }
                }
            } else if (staff.equals(Staff.OPERATOR)) {
                if (employee instanceof Operator) {
                    if (employee.isWork()) {
                        employeeHashSet.add(employee);
                        count++;
                    }
                }
            }
            if (count >= number) {
                break;
            }
        }
        if (count > 0) {
            System.out.println("Набрано из трудовых ресурсов - " + count + " " + staff);
        }
        if (count < number) {
            for (int i = 0; i < number - count; i++) {
                if (staff.equals(Staff.MANAGER)) {
                    employeeHashSet.add(new Manager());
                } else if (staff.equals(Staff.TOP_MANAGER)) {
                    employeeHashSet.add(new TopManager());
                } else if (staff.equals(Staff.OPERATOR)) {
                    employeeHashSet.add(new Operator());
                }
            }
            System.out.println("Создано новых рабочих - " + (number - count) + " " + staff);
        }
        return employeeHashSet;
    }

    //Набор всего штата сотрудников
    protected HashSet<Employee> getEmployeeSet(int operator, int manager, int topManager) {
        HashSet<Employee> employeeHashSet = new HashSet<Employee>();
        employeeHashSet.addAll(getStaffSet(Staff.OPERATOR, operator));
        employeeHashSet.addAll(getStaffSet(Staff.MANAGER, manager));
        employeeHashSet.addAll(getStaffSet(Staff.TOP_MANAGER, topManager));
        return employeeHashSet;
    }
}
