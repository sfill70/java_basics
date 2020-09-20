package Company;

import Employee.Employee;
import Employee.Operator;
import Employee.TopManager;
import Employee.Manager;

import java.math.BigDecimal;
import java.util.*;

public class Company {

    private static LaborResources laborResources = new LaborResources();
    public static final Company LABOR_EXCHANGE = new Company("Биржа тружа");
    private ArrayList<Employee> allEmployees = new ArrayList<Employee>();
    private String name;
    private BigDecimal income = new BigDecimal("0");

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    //Только для проверки
    public static HashMap<String, Employee> getLaborResources() {
        return laborResources.getLaborResources();
    }

    //Только для проверки
    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<Employee>(allEmployees);
    }

    public static String getIdEmployee() {
        String stId;
        do {
            UUID id = UUID.randomUUID();
            stId = id.toString().substring(id.toString().length() - 12);
        } while (laborResources.getLaborResources().containsKey(stId));
        return stId;
    }

    public static void addLaborResources(Employee employee) {
        laborResources.addLaborResources(employee);
    }

    public void removeEmployee(Employee employee) {
        allEmployees.remove(employee);
    }

    public void addEmployee(Employee employee) {
        allEmployees.add(employee);
    }


    // Здесь можно использовать мктод add всех Employee и отказаться от конструктора Employee(company)
    //Так и сделал
    public void hire(Employee employee) {
        employee.add(this);
    }

    public void hireAll(HashSet<Employee> set) {
        for (Employee employee : set
        ) {
            hire(employee);
        }
    }

    //Получение Set для hireAll
    public HashSet<Employee> getEmployeeSet(int operator, int manager, int topManager) {
        return laborResources.getEmployeeSet(operator, manager, topManager);
    }

    //Получение Set для hireAll по сотруднику можно перегрузить предыдущий метод
    public HashSet<Employee> getStaffSet(Staff staff, int count) {
        return laborResources.getStaffSet(staff, count);
    }

    public void increaseIncome(Employee employee) {
        income = income.add(employee.getIncome());
    }

    public void fire(Employee employee) {
        if (allEmployees.contains(employee)) {
            employee.remove();
//            System.out.println("Уволен сотрудник - " + employee);
        }
    }

    // Увольнение нескольких случайных сотрудников
    public void fire(int count) {
        if (allEmployees.isEmpty()) {
            System.out.println("У фирмы нет сотрудников");
            return;
        }
        if (count > allEmployees.size()) {
            System.out.println("Превышено количество сотрудников");
            return;
        }
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Employee employee = allEmployees.get(random.nextInt(allEmployees.size() - 1));
            fire(employee);
        }
    }

    //Увольнение первого попавшегося сотрудника этой профессии
    public void fire(Staff staff) {
        if (allEmployees.isEmpty()) {
            System.out.println("У фирмы нет сотрудников");
            return;
        }
        for (Employee employee : allEmployees
        ) {
            if (staff.equals(Staff.MANAGER)) {
                if (employee instanceof Manager) {
                    fire(employee);
                    break;
                }
            } else if (staff.equals(Staff.TOP_MANAGER)) {
                if (employee instanceof TopManager) {
                    fire(employee);
                    break;
                }
            } else if (staff.equals(Staff.OPERATOR)) {
                if (employee instanceof Operator) {
                    fire(employee);
                    break;
                }
            }
        }
    }

    // Для проверки
    public Employee getEmployeeDyId(String id) {
        for (Employee employee : allEmployees
        ) {
            if (employee.getId().equals(id))
                return employee;
        }
        return null;
    }


    public void decreaseIncome(Manager manager) {
        income = income.subtract(manager.getIncome());
    }

    public BigDecimal getIncome() {
        return income;
    }

//    Comparator<Employee> comparator = (Employee o1, Employee o2) -> (o1.getMonthSalary().compareTo(o2.getMonthSalary()));

    // Это IDE постарлась
    Comparator<Employee> comparator = Comparator.comparing(Employee::getMonthSalary);

    private void AllEmployeesSort() {
        allEmployees.sort(comparator);

    }

    private void AllEmployeesReverse() {
        AllEmployeesSort();
        Collections.reverse(allEmployees);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        List<Employee> list = new ArrayList<>();
        if (allEmployees.isEmpty()) {
            System.out.println("Сотрудников нет.");
            return list;
        }
        AllEmployeesReverse();
        list.addAll(allEmployees.subList(0, count));
        return list;
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        List<Employee> list = new ArrayList<>();
        if (allEmployees.isEmpty()) {
            System.out.println("Сотрудников нет.");
            return list;
        }
        AllEmployeesReverse();
        list.addAll(allEmployees.subList(allEmployees.size() - count, allEmployees.size() - 1));
        return list;
    }

    public void printLowestSalary(int count) {
        AllEmployeesSort();
        if (count > allEmployees.size() || count <= 0) {
            System.out.println("Число сотрудниеков введено неверно");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(allEmployees.get(i));
        }
    }

    public void printTopSalary(int count) {
        AllEmployeesSort();
        if (count > allEmployees.size() || count <= 0) {
            System.out.println("Число сотрудниеков введено неверно");
            return;
        }
        for (int i = allEmployees.size() - 1; i >= (allEmployees.size() - count); i--) {
            System.out.println(allEmployees.get(i));
        }
    }


}

