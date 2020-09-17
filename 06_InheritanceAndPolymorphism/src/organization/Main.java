package organization;


public class Main {
    public static void main(String[] args) {

        Company romashka = new Company("Ромашка");

        for (int i = 0; i < 180; i++) {
            romashka.hire(new Operator(romashka));
        }

        for (int i = 0; i < 70; i++) {
            romashka.hire(new Manager(romashka));
        }
        romashka.hire(Staff.MANAGER, 10);

        romashka.hireAll(0,0,10);

        System.out.println("Доход компании - " + romashka.getIncome());

        System.out.println("Весь персонал - " + romashka.getAllEmployees().size());
        System.out.println("Трудовые ресурсы - " + Company.getLaborResources().size());

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
        fikus.hireAll(0,0,10);
        fikus.printTopSalary(10);
        fikus.fire(Staff.OPERATOR);
        fikus.fire(Staff.MANAGER);
        System.out.println(fikus.getIncome());
        fikus.hire(Staff.MANAGER, 20);
        fikus.printTopSalary(30);
        System.out.println(fikus.getIncome());
        fikus.hire(Staff.OPERATOR,-3);
        fikus.hireAll(-1,-6,-30);



       /* for (int i = 0; i < 20; i++) {
            System.out.println( (new Random().nextInt(25000) + 115000));
            System.out.println(BigDecimal.valueOf((double) (new Random().nextInt(25000) + 115000) / 20).setScale(2, RoundingMode.HALF_UP));
            System.out.println(new BigDecimal((new Random().nextInt(25000) + 115000)).setScale(2, RoundingMode.HALF_UP));
            double q = ((double)(new Random().nextInt(25) + 115)  / 20);*/
            /*BigDecimal income = new BigDecimal((new Random().nextInt(25000) + 115000)).setScale(2, RoundingMode.HALF_UP);
            System.out.println(income);
            BigDecimal bonus = new BigDecimal(String.valueOf(income.divide(BigDecimal.valueOf(20.00)))).setScale(2, RoundingMode.HALF_UP);
            System.out.println(bonus);
            Income = Income.add(income);
            System.out.println(new BigDecimal("40000.00").add(bonus));
            System.out.println(Income);
            System.out.println("---------------------------/----------------------------------");*/

    }

}

