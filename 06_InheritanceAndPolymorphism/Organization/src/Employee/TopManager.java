package Employee;

import Company.Company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TopManager implements Employee {

    private Company company;
    final static BigDecimal RATE_INCOME = new BigDecimal("10000000");
    final private String id = Company.getIdEmployee();
    final private String name;
    final private BigDecimal salary = new BigDecimal("100000").setScale(2, RoundingMode.HALF_UP);

    public TopManager() {
        this.name = "topManager_" + id;
        this.company = Company.LABOR_EXCHANGE;
        Company.addLaborResources(this);
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

    @Override
    public BigDecimal getMonthSalary() {
        return salary.add(getBonus()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getIncome() {
        return new BigDecimal("0");
    }

    private BigDecimal getBonus() {
        if (company != null && company.getIncome().compareTo(RATE_INCOME) > 0) {
            return new BigDecimal(String.valueOf(salary.multiply((BigDecimal.valueOf(1.5)))))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return new BigDecimal("0");
    }

    @Override
    public String toString() {
        return "TopManager{" +
                "company=" + ((company == null || company.equals(Company.LABOR_EXCHANGE)) ? "Я пока без работы" : company.getName()) +
                ", name='" + name + '\'' +
                ", salary=" + getMonthSalary() +
                '}';
    }

    /*@Override
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
    }*/

}
