package se.anna.workplacedatabase.utility;

import se.anna.workplacedatabase.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    Employee getEmployee(Integer employeeId) throws SQLException;
    List<Employee> getallEmployees() throws SQLException;
    void insertEmployee(Employee employee) throws SQLException;
    void updateEmployee(Employee employee) throws SQLException;
    void deleteEmployee(Employee employee) throws SQLException;
}
