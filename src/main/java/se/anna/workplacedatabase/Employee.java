package se.anna.workplacedatabase;

import java.util.Objects;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleid(WorkRole roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "-------------------------"+
                "\nEmployee ID: " + employeeId +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nPassword: " + password +
                "\n-------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(name, employee.name) && Objects.equals(email, employee.email) && Objects.equals(password, employee.password) && Objects.equals(roleid, employee.roleid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, email, password, roleid);
    }
}
