package organization;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TopManager implements Employee {

    private Company company;
    private Boolean isWork;
    final static BigDecimal RATE_INCOME = new BigDecimal("10000000");
    final private String id = Company.getIdEmployee();
    final private String name;
    final private BigDecimal salary = new BigDecimal("100000").setScale(2, RoundingMode.HALF_UP);


    public TopManager(Company company) {
        this.company = company;
        this.name = "topManager_" + id;
        isWork = true;
        Company.addLaborResources(id,this);
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

    protected void remove(){
        company.removeEmployee(this);
        setWork(false);
        setCompany(Company.LABOR_EXCHANGE);

    }

    protected void add(Company company){
        setWork(true);
        setCompany(company);
        company.addEmployee(this);

    }


    public BigDecimal getMonthSalary() {
        return salary.add(getBonus()).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getBonus() {
        if(company == null){
            return new BigDecimal("0");
        }
        if (company.getIncome().compareTo(RATE_INCOME) > 0) {
            return new BigDecimal(String.valueOf(salary.multiply((BigDecimal.valueOf(1.5))))).setScale(2, RoundingMode.HALF_UP);
        }
        return new BigDecimal("0");
    }

    @Override
    public String toString() {
        return "TopManager{" +
                "company=" + (company.equals(Company.LABOR_EXCHANGE) ? "Я пока без работы" : company.getName()) +
                ", name='" + name + '\'' +
                ", salary=" + getMonthSalary() +
                '}';
    }
}
