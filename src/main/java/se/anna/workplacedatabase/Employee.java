package se.anna.workplacedatabase;

public class Employee {
    private Integer employeeId;
    private String name;
    private String email;
    private String password;
    private WorkRole roleid;

    public Employee(Integer employeeId, String name, String email, String password, WorkRole roleid) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleid = roleid;
    }

    public Employee(String name, String email, String password, WorkRole roleid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleid = roleid;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public WorkRole getRoleid() {
        return roleid;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
                ", Name: " + name +
                ", Email: " + email +
                ", Password: " + password +
                ", Role ID: " + roleid;
    }
}
