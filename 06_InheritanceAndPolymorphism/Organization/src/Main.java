
import Company.Company;
import Employee.Manager;
import Employee.TopManager;
import Employee.Operator;
import Employee.Employee;
import Employee.Developer;
import Company.Staff;


public class Main {

    public static void main(String[] args)  {

        Company rose = new Company("Роза");
        rose.hireAll(rose.getEmployeeSet(120, 110, 15, 20));
        System.out.println("Весь персонал - " + rose.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

        for (int i = 0; i < 5; i++) {
            try {
                Employee employee = Operator.class.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        for (int i = 0; i < 40; i++) {
            rose.fire(Operator.class);
        }
        for (int i = 0; i < 32; i++) {
            rose.fire(Manager.class);
        }
        for (int i = 0; i < 5; i++) {
            rose.fire(TopManager.class);
        }
        System.out.println(rose.getLowestSalaryStaff(20));
        System.out.println(rose.getTopSalaryStaff(-1));
        System.out.println("----------------/-----------------------/----------------------------/------------------------/");

        //Вот здесь набрано количество по условию задачи.
        Company romashka = new Company("Ромашка");
        romashka.hireAll(romashka.getStaffSet(Operator.class, 180));
        romashka.hireAll(romashka.getStaffSet(Manager.class, 70));
        romashka.hireAll(romashka.getEmployeeSet(0, 10, 0,1));
        romashka.hireAll(romashka.getEmployeeSet(0, 0, 10,0));
        System.out.println("Доход компании - " + romashka.getIncome());
        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println(romashka.getLowestSalaryStaff(20));
        System.out.println(romashka.getTopSalaryStaff(20));
        //Увольнение половины.
        romashka.fire(romashka.getAllEmployees().size() / 2);
        System.out.println("----------------/-----------------------/----------------------------/------------------------/");
        Company tulpan = new Company("Тюльпан");
        tulpan.hireAll(tulpan.getStaffSet(TopManager.class, 7));
        tulpan.hireAll(tulpan.getStaffSet(Manager.class, 11));
        tulpan.hireAll(tulpan.getStaffSet(Manager.class, 20));
        tulpan.hireAll(tulpan.getStaffSet(Operator.class, 11));
        tulpan.hireAll(tulpan.getStaffSet(Operator.class, 11));
        tulpan.hireAll(tulpan.getEmployeeSet(15, 20, 5, 5));
        for (int i = 0; i < 20; i++) {
            tulpan.hire(new Developer());
        }
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println("В фирме Тюльпан - " + tulpan.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println("В фирме ромашка - " + romashka.getAllEmployees().size());
        Employee employee;
        System.out.println("Выбран для увольения сотрудник - " + (employee = romashka.getLowestSalaryStaff(135).get(2)));
        romashka.fire(employee);
        System.out.println("Уволеный  сотрудник -  " + Company.getLaborResources().get(employee.getId()));
        Employee employee1 = new TopManager();
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println(employee1);
        Company.LABOR_EXCHANGE.hire(new TopManager());
        System.out.println(Company.LABOR_EXCHANGE.getAllEmployees());
        Company.LABOR_EXCHANGE.fire(5);
        System.out.println("В фирме ромашка - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());


    }
}

