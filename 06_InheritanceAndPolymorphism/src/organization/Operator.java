package organization;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operator implements Employee {

    private Company company;
    private boolean isWork;
    final private String id = Company.getIdEmployee();
    final private String name;
    final private BigDecimal salary = new BigDecimal("30000").setScale(2, RoundingMode.HALF_UP);

    public Operator(Company company) {
        this.company = company;
        this.name = "operator_" + id;
        isWork = true;
        Company.addLaborResources(id,this);

    }

   public boolean isWork() {
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
        return salary;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "company=" + (company != null ? company.getName() : "Я пока без работы") +
                ", name='" + name + '\'' +
                ", salary=" + getMonthSalary() +
                '}';
    }
}
