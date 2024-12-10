package se.anna.workplacedatabase;

import se.anna.workplacedatabase.utility.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("LanguageDetectionInspection")
public class Main {
    public static void main(String[] args) throws SQLException {
        WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        WorkRole CEO = new WorkRole("CEO",
                "Big stonk man",
                50.5008,
                Date.valueOf("2000-04-16"));
        WorkRole CEORole = workRoleDAO.getWorkRoleByTitle("CEO");

        WorkRole swEng = new WorkRole("Software Engineer",
                "Create products and services",
                35.9,
                Date.valueOf("2000-05-18"));
        WorkRole swEngRole = workRoleDAO.getWorkRoleByTitle("Software Engineer");

        WorkRole sysAdmin = new WorkRole("Systems Administrator",
                "Manages IT infrastructure",
                40.7,
                Date.valueOf("2000-12-06"));
        WorkRole sysAdminRole = workRoleDAO.getWorkRoleByTitle("Systems Administrator");

        WorkRole devOps = new WorkRole("DevOps Engineer",
                "Integration of operations",
                29.9,
                Date.valueOf("2000-06-30"));
        WorkRole devOpsRole = workRoleDAO.getWorkRoleByTitle("DevOps Engineer");

        WorkRole prodMan = new WorkRole("Product Manager",
                "Oversees product development",
                31.1,
                Date.valueOf("2001-01-05"));
        WorkRole prodManRole = workRoleDAO.getWorkRoleByTitle("Product Manager");

        WorkRole toBeDeleted = new WorkRole("to be deleted",
                "exists to be deleted",
                0,
                Date.valueOf("2000-01-01"));
        WorkRole toBeDeletedRole = workRoleDAO.getWorkRoleByTitle("to be deleted");


        Employee anna = new Employee("Anna",
                "anna@example.com",
                "annaspassword",
                CEORole);
        Employee clement = new Employee("Clement",
                "clement@example.com",
                "clementspassword",
                devOpsRole);
        Employee michael = new Employee("Michael",
                "michael@example.com",
                "michaelspassword",
                swEngRole);
        Employee ylva = new Employee("Ylva",
                "ylva@example.com",
                "ylvaspassword",
                sysAdminRole);
        Employee lisa = new Employee("Lisa",
                "lisa@example.com",
                "lisaspassword",
                prodManRole);
        Employee toBe_Deleted = new Employee("To Be Deleted",
                "danlkjdl@example.com",
                "tobedeleted",
                toBeDeletedRole);

        try {
//            //insert workrole
//            workRoleDAO.insertWorkRole(nameofworkroleobject);

//            //get all workroles
//            List<WorkRole> workRoles = workRoleDAO.getallWorkRoles();
//            for (WorkRole workRole : workRoles) {
//                System.out.println(workRole);
//            }

//            //get workrole
//            WorkRole workRole = workRoleDAO.getWorkRole(0);
//            System.out.println(workRole);

//            //update workrole
//            WorkRole workRole = workRoleDAO.getWorkRole(0);
//            System.out.println(workRole);
//            workRole.setTitle("example");
//            workRole.setSalary(10.000);
//            workRoleDAO.updateWorkRole(workRole);
//            System.out.println(workRole);

//            //delete workrole
//            WorkRole workRole = workRoleDAO.getWorkRole(6);
//            workRoleDAO.deleteWorkRole(workRole);

//////////////////////////////////////////////////////

//            //insert employee
//            employeeDAO.insertEmployee(nameofemployeeobject);
            //employeeDAO.insertEmployee(toBe_Deleted);

            //get all employees
            List<Employee> employees = employeeDAO.getallEmployees();
            for (Employee employee : employees) {
                System.out.println(employee);
            }

//            //get employee
//            Employee employee = employeeDAO.getEmployee(1);
//            System.out.println(employee);

//            //update employee
//            Employee employee = employeeDAO.getEmployee(5);
//            System.out.println(employee);
//            employee.setName("example");
//            employee.setPassword("example");
//            employeeDAO.updateEmployee(employee);

//            //delete employee
//            Employee employee = employeeDAO.getEmployee(5);
//            employeeDAO.deleteEmployee(employee);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//make a PersonNotFoundException class, see lektion 5 ca 14:00
//make a schema.sql file, ask abt it + check lektion 6 pt 2 7:00
//remember to make application.properties in both main and test
//ask abt pstmt vs stmt in the method(s) closeStatement()