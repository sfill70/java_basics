package organization;

import java.math.BigDecimal;
import java.util.*;

public class Company {
    private static HashMap<String, Employee> laborResources = new HashMap();
    private ArrayList<Employee> allEmployees = new ArrayList();
    private String name;
    private BigDecimal income = new BigDecimal("0");
    final static Company LABOR_EXCHANGE = new Company("Биржа тружа");

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    //Только для проверки
    protected static HashMap<String, Employee> getLaborResources() {
        return laborResources;
    }

    //Только для проверки
    protected ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    protected static String getIdEmployee() {
        UUID id = UUID.randomUUID();
        String stId = id.toString().substring(id.toString().length() - 12);
        while (laborResources.containsKey(stId)) {
            id = UUID.randomUUID();
            stId = id.toString().substring(id.toString().length() - 12);
        }
        return stId;
    }

    protected static void addLaborResources(String id, Employee employee) {
        laborResources.put(id, employee);
    }

    protected void removeEmployee(Employee employee) {
        allEmployees.remove(employee);
    }

    protected void addEmployee(Employee employee) {
        allEmployees.add(employee);
    }

 /*   public <T extends Employee> void hire(Employee employee){

    }*/

    public void hire(Employee employee) {
        allEmployees.add(employee);
        if (employee instanceof Manager) {
            income = income.add(((Manager) employee).getIncome());
        }
    }


    //Набор указаного количества первонала указанной профессии из свободных трудовых ресурсов (laborResources)
    // если не набрали создаем новых
    public void hire(Staff staff, int number) {
        int count = 0;
        for (Map.Entry<String, Employee> entry : laborResources.entrySet()
        ) {
            Employee employee = entry.getValue();
            if (staff.equals(Staff.MANAGER)) {
                if (employee instanceof Manager) {
                    Manager manager = (Manager) employee;
                    if (!manager.isWork()) {
                        manager.add(this);
                        income = income.add(manager.getIncome());
                        count++;
                    }
                }
            } else if (staff.equals(Staff.TOP_MANAGER)) {
                if (employee instanceof TopManager) {
                    TopManager topManager = (TopManager) employee;
                    if (!topManager.isWork()) {
                        topManager.add(this);
                        count++;
                    }
                }
            } else if (staff.equals(Staff.OPERATOR)) {
                if (employee instanceof Operator) {
                    Operator operator = (Operator) employee;
                    if (!operator.isWork()) {
                        operator.add(this);
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
                    hire(new Manager(this));
                } else if (staff.equals(Staff.TOP_MANAGER)) {
                    hire(new TopManager(this));
                } else if (staff.equals(Staff.OPERATOR)) {
                    hire(new Operator(this));
                }
            }
            System.out.println("Создано новых рабочих - " + (number - count) + " " + staff);
        }
    }

    //Набор всего штата сотрудников
    public void hireAll(int operator, int manager, int topManager) {
        hire(Staff.OPERATOR, operator);
        hire(Staff.MANAGER, manager);
        hire(Staff.TOP_MANAGER, topManager);
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
//            allEmployees.remove(employee);
            if (employee instanceof Manager) {
                Manager manager = (Manager) employee;
                income = income.subtract(manager.getIncome());
                manager.remove();
            } else if (employee instanceof TopManager) {
                TopManager topManager = (TopManager) employee;
                topManager.remove();
            } else if (employee instanceof Operator) {
                Operator operator = (Operator) employee;
                operator.remove();
            }
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
                    Manager manager = (Manager) employee;
                    income = income.subtract(manager.getIncome());
                    manager.remove();
                    break;
                }
            } else if (staff.equals(Staff.TOP_MANAGER)) {
                if (employee instanceof TopManager) {
                    TopManager topManager = (TopManager) employee;
                    topManager.remove();
                    break;
                }
            } else if (staff.equals(Staff.OPERATOR)) {
                if (employee instanceof Operator) {
                    Operator operator = (Operator) employee;
                    operator.remove();
                    break;
                }
            }
        }

    }

    //Увольнение непонятно кого и непонятно как ???
    public void fire(Employee employee) {
        if (employee instanceof Manager) {
            Manager manager = (Manager) employee;
            income = income.subtract(manager.getIncome());
            manager.remove();
        } else if (employee instanceof TopManager) {
            TopManager topManager = (TopManager) employee;
            topManager.remove();
        } else if (employee instanceof Operator) {
            Operator operator = (Operator) employee;
            operator.remove();
        }
    }

    public BigDecimal getIncome() {
        return income;
    }

// Comparator'ы
   /* Comparator<Employee> comparator1 = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return (((Employee) o1).getMonthSalary().compareTo(((Employee) o2).getMonthSalary()));

        }
    };*/

//    Comparator<Employee> comparator = (Employee o1, Employee o2) -> (o1.getMonthSalary().compareTo(o2.getMonthSalary()));

    // Это IDE постарлась
    Comparator<Employee> comparator = Comparator.comparing(Employee::getMonthSalary);

    private void AllEmployeesSort() {
        allEmployees.sort(comparator);

    }

    // Метод сортровки по убыванию, не ипользуется
    private void AllEmployeesReverse() {
        AllEmployeesSort();
        Collections.reverse(allEmployees);
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
        list.addAll(allEmployees.subList(allEmployees.size() - 1 - count, allEmployees.size() - 1));
        return list;
    }

}

