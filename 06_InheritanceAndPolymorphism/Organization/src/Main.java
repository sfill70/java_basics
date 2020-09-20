import Company.Company;
import Employee.Manager;
import Employee.TopManager;
import Employee.Operator;
import Employee.Employee;
import Company.Staff;
import Company.LaborResources;

public class Main {
    public static void main(String[] args) {

        Company rose = new Company("Роза");
        rose.hireAll(rose.getEmployeeSet(120, 110, 15));
        System.out.println("Весь персонал - " + rose.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

        for (int i = 0; i < 40; i++) {
            rose.fire(Staff.OPERATOR);
        }
        for (int i = 0; i < 22; i++) {
            rose.fire(Staff.MANAGER);
        }

        for (int i = 0; i < 5; i++) {
            rose.fire(Staff.TOP_MANAGER);
        }
        System.out.println(rose.getLowestSalaryStaff(20));
        System.out.println(rose.getTopSalaryStaff(20));
        System.out.println("----------------/-----------------------/----------------------------/------------------------/");

        //Вот здесь набрано количество по условию задачи.
        Company romashka = new Company("Ромашка");
        romashka.hireAll(romashka.getStaffSet(Staff.OPERATOR, 180));
        romashka.hireAll(romashka.getStaffSet(Staff.MANAGER, 70));
        romashka.hireAll(romashka.getEmployeeSet(0, 10, 0));
        romashka.hireAll(romashka.getEmployeeSet(0, 0, 10));
        System.out.println("Доход компании - " + romashka.getIncome());
        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        System.out.println(romashka.getLowestSalaryStaff(20));
        System.out.println(romashka.getTopSalaryStaff(20));
        //Увольнение половины.
        romashka.fire(romashka.getAllEmployees().size() / 2);
        System.out.println("----------------/-----------------------/----------------------------/------------------------/");
        Company tulpan = new Company("Тюльпан");
        tulpan.hireAll(tulpan.getStaffSet(Staff.TOP_MANAGER, 7));
        tulpan.hireAll(tulpan.getStaffSet(Staff.MANAGER, 11));
        tulpan.hireAll(tulpan.getStaffSet(Staff.MANAGER, 20));
        tulpan.hireAll(tulpan.getStaffSet(Staff.OPERATOR, 11));
        tulpan.hireAll(tulpan.getStaffSet(Staff.OPERATOR, 11));
        tulpan.hireAll(tulpan.getEmployeeSet(15, 20, 5));
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





/*

        romashka.fire(romashka.getAllEmployees().size() / 2);
        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources());

        romashka.hire(Staff.MANAGER, 200);
        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());
        romashka.fire(Staff.TOP_MANAGER);
        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

        romashka.fire(150);
        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

        Company lutik = new Company("Лютик");
        lutik.hire(Staff.OPERATOR, 30);
        lutik.hire(Staff.MANAGER, 120);
        lutik.hire(Staff.TOP_MANAGER, 8);

        System.out.println("Весь персонал Лютик - " + lutik.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

        System.out.println("Доходы Лютик - " + lutik.getIncome());
        lutik.fire(40);
        System.out.println("Доходы Лютик - " + lutik.getIncome());

        System.out.println("-----------/ Махсимальные зарплаты Лютик /----------------------------");
        lutik.printTopSalary(15);
        System.out.println("-----------/ Махсимальные зарплаты Ромашка /----------------------------");
        romashka.printTopSalary(20);
        System.out.println("-----------/ Минимальные зарплаты Ромашка /----------------------------");
        romashka.printLowestSalary(20);

        System.out.println(romashka.getLowestSalaryStaff(20));
        System.out.println(romashka.getTopSalaryStaff(20));


        Company fikus = new Company("Фикус");
        fikus.hireAll(0, 0, 10);
        fikus.printTopSalary(10);
        fikus.fire(Staff.OPERATOR);
        fikus.fire(Staff.MANAGER);
        System.out.println(fikus.getIncome());
        fikus.hire(Staff.MANAGER, 120);
        fikus.printTopSalary(30);
        System.out.println(fikus.getIncome());
        fikus.hire(Staff.OPERATOR, -3);
        fikus.hireAll(-1, -6, -30);

        BigDecimal bg;
        System.out.println(bg = fikus.getIncome());
        fikus.fire(Staff.MANAGER);
        BigDecimal dg = bg.subtract(fikus.getIncome());
        System.out.println(dg);
        System.out.println(fikus.getIncome());



       */
/* for (int i = 0; i < 20; i++) {
            System.out.println( (new Random().nextInt(25000) + 115000));
            System.out.println(BigDecimal.valueOf((double) (new Random().nextInt(25000) + 115000) / 20).setScale(2, RoundingMode.HALF_UP));
            System.out.println(new BigDecimal((new Random().nextInt(25000) + 115000)).setScale(2, RoundingMode.HALF_UP));
            double q = ((double)(new Random().nextInt(25) + 115)  / 20);*//*

         */
/*BigDecimal income = new BigDecimal((new Random().nextInt(25000) + 115000)).setScale(2, RoundingMode.HALF_UP);
            System.out.println(income);
            BigDecimal bonus = new BigDecimal(String.valueOf(income.divide(BigDecimal.valueOf(20.00)))).setScale(2, RoundingMode.HALF_UP);
            System.out.println(bonus);
            Income = Income.add(income);
            System.out.println(new BigDecimal("40000.00").add(bonus));
            System.out.println(Income);
            System.out.println("---------------------------/----------------------------------");*//*



         */
    }
}

