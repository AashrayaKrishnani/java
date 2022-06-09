import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Employee> employees;

    Department(String name)
    {
        this.name = name;
        employees = new ArrayList<>();
    }

    void addEmployee(Employee e){this.employees.add(e);}

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
