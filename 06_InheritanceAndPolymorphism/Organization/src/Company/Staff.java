package Company;

import Employee.Employee;
import Employee.Operator;
import Employee.Manager;
import Employee.TopManager;
import Employee.Developer;

public enum Staff {
    OPERATOR(Operator.class),
    MANAGER(Manager.class),
    TOP_MANAGER(TopManager.class),
    DEVELOPER(Developer.class);

    private Class<? extends Employee> title;

    Staff(Class<? extends Employee> employeeClass) {
        this.title = employeeClass;
    }

    public Class<? extends Employee> getTitle() {
        return title;
    }

}
