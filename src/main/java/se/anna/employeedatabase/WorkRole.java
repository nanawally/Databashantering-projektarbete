package se.anna.employeedatabase;

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

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Role ID: " + roleId +
                ", Title: " + title +
                ", Description: " + description +
                ", Salary: " + salary +
                ", Creation Date: " + creationDate;
    }
}
