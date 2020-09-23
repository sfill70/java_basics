package Employee;

import Company.Company;
import Company.LaborResources;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Manager implements Employee {

    private Company company;
    final private String id = LaborResources.getIdEmployee();
    final private String name;
    protected BigDecimal income = getIncomeManager();
    final private BigDecimal salary = new BigDecimal("40000").setScale(2, RoundingMode.HALF_UP);

    public Manager() {
        this.name = "manager_" + id;
        this.company = Company.LABOR_EXCHANGE;
        LaborResources.addLaborResources(this);
    }

    public boolean isWork() {
        return company.equals(Company.LABOR_EXCHANGE);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getId() {
        return id;
    }

    private BigDecimal getIncomeManager() {
        Random random = new Random();
        return new BigDecimal((random.nextInt(25000) + 115000)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getBonus() {
        return new BigDecimal(String.valueOf(income.divide(BigDecimal.valueOf(20.00))));
    }


    @Override
    public BigDecimal getMonthSalary() {
        return salary.add(getBonus()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getIncome() {
        return income;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "company=" + (company.equals(Company.LABOR_EXCHANGE) ? "Я пока без работы" : company.getName()) +
                ", name='" + name + '\'' +
                ", income=" + income +
                ", salary=" + getMonthSalary() +
                '}';
    }

    /*@Override
    public void add(Company company) {
        if (company != Company.LABOR_EXCHANGE) {
            setCompany(company);
            company.addEmployee(this);
            company.increaseIncome(this);
        }
    }

    @Override
    public void remove() {
        company.decreaseIncome(this);
        company.removeEmployee(this);
        setCompany(Company.LABOR_EXCHANGE);
    }*/
}
