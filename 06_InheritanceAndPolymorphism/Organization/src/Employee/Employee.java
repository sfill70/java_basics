package Employee;

import Company.Company;

import java.math.BigDecimal;

public interface Employee {

    BigDecimal getMonthSalary();

    Company getCompany();

    void setCompany(Company company);

    default void add(Company company) {
        if (company != Company.LABOR_EXCHANGE) {
            setCompany(company);
            company.addEmployee(this);
            company.increaseIncome(this);
        }
    }

    default void remove() {
        Company company = this.getCompany();
        company.decreaseIncome(this);
        company.removeEmployee(this);
        setCompany(Company.LABOR_EXCHANGE);
    }

    boolean isWork();

    String getId();

    BigDecimal getIncome();


}
