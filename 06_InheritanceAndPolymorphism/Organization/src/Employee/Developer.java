package Employee;

import Company.Company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Developer  implements Employee{
    private Company company;
    final private String id = Company.getIdEmployee();
    final private String name;
    final private BigDecimal salary = new BigDecimal("60000").setScale(2, RoundingMode.HALF_UP);

    public Developer() {
        this.name = "operator_" + id;
        this.company = Company.LABOR_EXCHANGE;
        Company.addLaborResources(this);
    }

    public boolean isWork() {
        return company.equals(Company.LABOR_EXCHANGE);
    }

    protected void setCompany(Company company) {
        this.company = company;
    }

    public String getId() {
        return id;
    }

    @Override
    public void add(Company company) {
        if (company != Company.LABOR_EXCHANGE) {
            setCompany(company);
            company.addEmployee(this);
        }
    }

    @Override
    public void remove() {
        company.removeEmployee(this);
        setCompany(Company.LABOR_EXCHANGE);
    }

    @Override
    public BigDecimal getIncome() {
        return new BigDecimal("0");
    }

    @Override
    public BigDecimal getMonthSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return " Developer{" +
                "company=" + (company.equals(Company.LABOR_EXCHANGE) ? "Я пока без работы" : company.getName()) +
                ", name='" + name + '\'' +
                ", salary=" + getMonthSalary() +
                '}';
    }
}
