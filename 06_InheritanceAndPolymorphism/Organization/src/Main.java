
import Company.Company;
import Company.Staff;
import Employee.Manager;
import Employee.TopManager;
import Employee.Operator;
import Employee.Employee;
import Employee.Developer;
import Company.LaborResources;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class Main {

    public static void main(String[] args) {

        Company gvozdika = new Company("Гвоздика");
        LinkedHashMap<Staff, Integer> employeeM = new LinkedHashMap<Staff, Integer>();
        employeeM.put(Staff.OPERATOR, 40);
        employeeM.put(Staff.MANAGER, 50);
        employeeM.put(Staff.TOP_MANAGER, 5);
        employeeM.put(Staff.DEVELOPER, 10);
        gvozdika.hireAll(LaborResources.getStaffSet(employeeM));

        System.out.println("Весь персонал Гвоздика - " + gvozdika.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

        Company liliya = new Company("Лилия");
        HashMap<Class<? extends Employee>, Integer> employeeMap = new HashMap<>();
        employeeMap.put(Operator.class, 90);
        employeeMap.put(Manager.class, 100);
        employeeMap.put(Developer.class, 40);
        employeeMap.put(TopManager.class, 15);
        liliya.hireAll(LaborResources.getStaffSet(employeeMap));
        System.out.println("Весь персонал Лилия - " + liliya.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        Company rose = new Company("Роза");
        rose.hireAll(LaborResources.getEmployeeSet(120, 110, 15, 20));
        System.out.println("Весь персонал Роза - " + rose.getAllEmployees().size());
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
        romashka.hireAll(LaborResources.getStaffSet(Operator.class, 180));
        romashka.hireAll(LaborResources.getStaffSet(Manager.class, 70));
        romashka.hireAll(LaborResources.getEmployeeSet(0, 10, 0, 1));
        romashka.hireAll(LaborResources.getEmployeeSet(0, 0, 10, 0));
        System.out.println("Доход компании Ромашка - " + romashka.getIncome());
        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println(romashka.getLowestSalaryStaff(20));
        System.out.println(romashka.getTopSalaryStaff(20));
        //Увольнение половины.
        romashka.fire(romashka.getAllEmployees().size() / 2);
        System.out.println("Доход компании Ромашка - " + romashka.getIncome());
        System.out.println("----------------Фиалка-----------------------/----------------------------/------------------------/");

        Company fialka = new Company("Фиалка");
        LinkedHashMap<Staff, Integer> employeeMa = new LinkedHashMap<Staff, Integer>();
        employeeMa.put(Staff.OPERATOR, 10);
        employeeMa.put(Staff.MANAGER, 15);
        employeeMa.put(Staff.TOP_MANAGER, 5);
        employeeMa.put(Staff.DEVELOPER, 10);
        fialka.hireAll(LaborResources.getStaffSet(employeeMa));
        System.out.println("Весь персонал Фиалка - " + fialka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

        System.out.println("----------------/-----------------------/----------------------------/------------------------/");

        Company tulpan = new Company("Тюльпан");
        tulpan.hireAll(LaborResources.getStaffSet(TopManager.class, 7));
        tulpan.hireAll(LaborResources.getStaffSet(Manager.class, 11));
        tulpan.hireAll(LaborResources.getStaffSet(Manager.class, 20));
        tulpan.hireAll(LaborResources.getStaffSet(Operator.class, 11));
        tulpan.hireAll(LaborResources.getStaffSet(Operator.class, 11));
        tulpan.hireAll(LaborResources.getEmployeeSet(15, 20, 5, 5));
        for (int i = 0; i < 20; i++) {
            tulpan.hire(new Developer());
        }
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println("В фирме Тюльпан - " + tulpan.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println("В фирме ромашка - " + romashka.getAllEmployees().size());
        Employee employee = romashka.getLowestSalaryStaff(135).get(2);
        System.out.println("Выбран для увольения сотрудник - " + (employee));
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

