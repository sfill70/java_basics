package Employee;

import Company.Company;

import java.math.BigDecimal;

public interface Employee {
    BigDecimal getMonthSalary();

   void add(Company company);

    void remove();

    boolean isWork();

    String getId();

    BigDecimal getIncome();


}
