package se.anna.workplacedatabase.utility;

import se.anna.workplacedatabase.Employee;
import se.anna.workplacedatabase.WorkRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;

    @Override
    public Employee getEmployee(Integer employeeId) throws SQLException {
        Employee employee = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = """
                        SELECT e.employee_id, e.name, e.email, e.password, e.role_id, 
                               wr.title, wr.description, wr.salary, wr.creation_date
                        FROM employee e
                        JOIN work_role wr ON e.role_id = wr.role_id
                        WHERE e.employee_id = ?
                    """;
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, employeeId);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                WorkRole workRole = new WorkRole(
                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );

                Integer employeeID = rs.getInt("employee_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                employee = new Employee(employeeID, name, email, password, workRole);
            } else {
                System.out.println("Employee not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
        return employee;
    }

    public Employee getEmployeeByEmail(String email, WorkRoleDAO workRoleDAO) throws SQLException {
        Employee employee = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM employee WHERE email = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, email);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                Integer employeeID = rs.getInt("employee_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                Integer roleId = rs.getInt("role_id");
                WorkRole role = workRoleDAO.getWorkRole(roleId);
                employee = new Employee(employeeID, name, email, password, role);
            } else {
                System.out.println("Employee with email " + email + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving employee by email.", e);
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
        return employee;
    }

    @Override
    public List<Employee> getallEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = """
                    SELECT e.employee_id, e.name, e.email, e.password, e.role_id, 
                           wr.title, wr.description, wr.salary, wr.creation_date
                    FROM employee e
                    JOIN work_role wr ON e.role_id = wr.role_id
                    """;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                WorkRole workRole = new WorkRole(
                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );

                Integer employeeID = rs.getInt("employee_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Employee employee = new Employee(employeeID, name, email, password, workRole);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeConnection(conn);
        }
        return employees;
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        try {
            conn = JDBCUtil.getConnection();
            String sql = """
                    INSERT INTO employee (name, email, password, role_id)
                    VALUES (?, ?, ?, ?)
                    """;
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, employee.getName());
            pStmt.setString(2, employee.getEmail());
            pStmt.setString(3, employee.getPassword());
            pStmt.setInt(4, employee.getRoleid().getRoleId());

            int rows = pStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);

            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE employee SET name = ?, email = ?, password = ?" +
                    "WHERE employee_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, employee.getName());
            pStmt.setString(2, employee.getEmail());
            pStmt.setString(3, employee.getPassword());
            pStmt.setInt(4, employee.getEmployeeId());

            int rows = pStmt.executeUpdate();
            JDBCUtil.commit(conn);
            if (rows > 0) {
                System.out.println("Employee updated.");
            }
//            else {
//                throw new EmployeeNotFoundException("No employee with the id " +
//                        employee.getEmployeeId() + " was found.");
//            }
        } catch (SQLException e) {
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Override
    public void deleteEmployee(Employee employee) throws SQLException {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM employee WHERE employee_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, employee.getEmployeeId());

            int rows = pStmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Employee not found or could not be deleted.");
            } else {
                System.out.println("Rows affected: " + rows);
            }

            pStmt.executeUpdate();
            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            JDBCUtil.rollback(conn);
            throw new RuntimeException("Error deleting employee", e);
        } finally {
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
    }
}
