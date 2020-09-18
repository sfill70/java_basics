package organization;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Manager implements Employee {

    private Company company;
    private boolean isWork;
    final private String id = Company.getIdEmployee();
    final private String name;
    protected BigDecimal income = getIncomeManager();
    final private BigDecimal salary = new BigDecimal("40000").setScale(2, RoundingMode.HALF_UP);

    public Manager(Company company) {
        this.company = company;
        this.name = "manager_" + id;
        isWork = true;
        Company.addLaborResources(id, this);

    }

    protected boolean isWork() {
        return isWork;
    }

    protected void setWork(boolean work) {
        isWork = work;
    }

    protected void setCompany(Company company) {
        this.company = company;
    }
    @Override
    public BigDecimal getMonthSalary() {
        return salary.add(getBonus()).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getIncomeManager() {
        Random random = new Random();
        return new BigDecimal((random.nextInt(25000) + 115000)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getBonus() {
        return new BigDecimal(String.valueOf(income.divide(BigDecimal.valueOf(20.00))));
    }

    public BigDecimal getIncome() {
        return income;
    }

    protected void remove(){
        company.decreaseIncome(this);
        company.removeEmployee(this);
        setWork(false);
        setCompany(Company.LABOR_EXCHANGE);

    }

    protected void add(Company company){
        setWork(true);
        setCompany(company);
        company.addEmployee(this);
        company.increaseIncome(this);

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
}
