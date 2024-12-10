package se.anna.workplacedatabase;

import java.util.*;

public class WorkRole {
    private Integer roleId;
    private String title;
    private String description;
    private double salary;
    private Date creationDate;

    public WorkRole(Integer roleId, String title, String description, double salary, Date creationDate) {
        this.roleId = roleId;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public WorkRole(String title, String description, double salary, Date creationDate) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getSalary() {
        return salary;
    }

    public java.sql.Date getCreationDate() {
        return (java.sql.Date) creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "-------------------------" +
                "\nRole ID: " + roleId +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nSalary: " + salary +
                "\nCreation Date: " + creationDate +
                "\n-------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkRole workRole = (WorkRole) o;
        return Double.compare(salary, workRole.salary) == 0 && Objects.equals(roleId, workRole.roleId) && Objects.equals(title, workRole.title) && Objects.equals(description, workRole.description) && Objects.equals(creationDate, workRole.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, title, description, salary, creationDate);
    }
}
