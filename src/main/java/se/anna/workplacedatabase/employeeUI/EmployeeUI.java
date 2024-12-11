package se.anna.workplacedatabase.employeeUI;

import se.anna.workplacedatabase.Employee;
import se.anna.workplacedatabase.WorkRole;
import se.anna.workplacedatabase.utility.EmployeeDAO;
import se.anna.workplacedatabase.utility.EmployeeDAOImpl;
import se.anna.workplacedatabase.utility.WorkRoleDAO;
import se.anna.workplacedatabase.utility.WorkRoleDAOImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class EmployeeUI {

    public static void menu() {
        System.out.println("*----------------------------*");
        System.out.println("|--1. Create new work role---|");
        System.out.println("|--2. Delete work role-------|");
        System.out.println("|--3. Show one work role-----|");
        System.out.println("|--4. Show all work roles----|");
        System.out.println("|--5. Update work role-------|");
        System.out.println("|--6. Log in-----------------|");
        System.out.println("|--7. Exit-------------------|");
        System.out.println("*----------------------------*");
    }

    public void application() {
        ScannerHandler scannerHandler = new ScannerHandler();
        WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        boolean running = true;
        try {
            while (running) {
                menu();
                int userInput = scannerHandler.getIntInput("What do you wish to do?");
                switch (userInput) {
                    case 1 -> userAddedWorkRole(scannerHandler, workRoleDAO);
                    case 2 -> userDeletedWorkRole(scannerHandler, workRoleDAO);
                    case 3 -> userShowOneWorkRole(scannerHandler, workRoleDAO);
                    case 4 -> userShowAllWorkRoles(workRoleDAO);
                    case 5 -> userUpdateWorkRole(scannerHandler, workRoleDAO);
                    case 6 -> logIn(scannerHandler, employeeDAO, workRoleDAO);
                    case 7 -> running = false;
                    default -> System.out.println("\nInvalid input.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scannerHandler.closeScanner();
        }
    }

    private static void userAddedWorkRole(ScannerHandler scannerHandler, WorkRoleDAO workRoleDAO) throws SQLException {
        String title = scannerHandler.getStringInput("Enter the title of the work role:");
        String description = scannerHandler.getStringInput("Enter the description of the work role:");
        double salary = scannerHandler.getDoubleInput("Enter the salary for the work role:");
        Date creationDate = null;
        while (creationDate == null) {
            String dateString = scannerHandler.getStringInput("Enter the creation date (YYYY-MM-DD):");
            try {
                creationDate = Date.valueOf(dateString);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format.");
            }
        }
        WorkRole newWorkRole = new WorkRole(title, description, salary, creationDate);

        workRoleDAO.insertWorkRole(newWorkRole);
        System.out.println("Work role added successfully!");
    }

    private static void userDeletedWorkRole(ScannerHandler scannerHandler, WorkRoleDAO workRoleDAO) {
        int roleId = scannerHandler.getIntInput("Enter the role ID of the role you wish to delete.");
        try {
            WorkRole workRole = workRoleDAO.getWorkRole(roleId);
            if (workRole != null) {
                workRoleDAO.deleteWorkRole(workRole);
                System.out.println("Work role deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void userShowOneWorkRole(ScannerHandler scannerHandler, WorkRoleDAO workRoleDAO) {
        int roleId = scannerHandler.getIntInput("Enter the role ID of the role you wish to show.");
        try {
            WorkRole workRole = workRoleDAO.getWorkRole(roleId);
            System.out.println(workRole);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void userShowAllWorkRoles(WorkRoleDAO workRoleDAO) {
        try {
            List<WorkRole> workRoles = workRoleDAO.getallWorkRoles();
            for (WorkRole workRole : workRoles) {
                System.out.println(workRole);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void userUpdateWorkRole(ScannerHandler scannerHandler, WorkRoleDAO workRoleDAO) {
        int roleId = scannerHandler.getIntInput("Enter the role ID of the role you wish to update.");
        boolean running = true;
        try {
            WorkRole workRole = workRoleDAO.getWorkRole(roleId);
            if (workRole != null) {
                System.out.println(workRole);
                while (running) {
                    updateMenu();
                    int userInput = scannerHandler.getIntInput("What do you wish to do?");
                    switch (userInput) {
                        case 1 -> {
                            String newTitle = scannerHandler.getStringInput("Enter the new title for the work role:");
                            workRole.setTitle(newTitle);
                        }
                        case 2 -> {
                            String newDescription = scannerHandler.getStringInput("Enter the new description for the work role:");
                            workRole.setDescription(newDescription);
                        }
                        case 3 -> {
                            double newSalary = scannerHandler.getDoubleInput("Enter the new salary for the work role:");
                            workRole.setSalary(newSalary);
                        }
                        case 4 -> {
                            String newDateInput = scannerHandler.getStringInput("Enter the new creation date (YYYY-MM-DD):");
                            Date newCreationDate = Date.valueOf(newDateInput);
                            workRole.setCreationDate(newCreationDate);
                        }
                        case 5 -> running = false;
                        default -> System.out.println("\nInvalid input.");
                    }
                }
                workRoleDAO.updateWorkRole(workRole);
                System.out.println(workRole);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMenu() {
        System.out.println("*----------------------------*");
        System.out.println("|--1. Update title-----------|");
        System.out.println("|--2. Update description-----|");
        System.out.println("|--3. Update salary----------|");
        System.out.println("|--4. Update creation date---|");
        System.out.println("|--5. Exit-------------------|");
        System.out.println("*----------------------------*");
    }

    public static void logIn(ScannerHandler scannerHandler, EmployeeDAO employeeDAO, WorkRoleDAO workRoleDAO) {
        String email = scannerHandler.getStringInput("Please enter your email.");
        try {
            Employee employee = employeeDAO.getEmployeeByEmail(email, workRoleDAO);
            if (employee != null) {
                String password = scannerHandler.getStringInput("Please enter your password.");
                if (password.equals(employee.getPassword())) {
                    System.out.println("Login successful!");
                    System.out.println(employee);
                    WorkRole role = employee.getRoleid();
                    System.out.println(role);
                } else {
                    System.out.println("Incorrect password.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
