package se.anna.workplacedatabase.utility;

import se.anna.workplacedatabase.Employee;
import se.anna.workplacedatabase.WorkRole;

import java.sql.*;
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
            String sql = "SELECT * FROM employee WHERE employee_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, employeeId);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                WorkRole workRole = new WorkRole(
                        rs.getInt("role_id"),
                         rs.getString("title"),
                         rs.getString("description"),
                         rs.getDouble("salary"),
                         rs.getDate("creation_date"));

                Integer employeeID = rs.getInt("employee_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Integer roleId = rs.getInt("role_id");
                employee = new Employee(employeeID, name, email, password, workRole);
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

    @Override
    public List<Employee> getallEmployees() throws SQLException {
        return List.of();
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {

    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {

    }

    @Override
    public void deleteEmployee(Employee employee) throws SQLException {

    }
}
