package se.anna.workplacedatabase.utility;

import se.anna.workplacedatabase.WorkRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDAOImpl implements WorkRoleDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;

    @Override
    public WorkRole getWorkRole(Integer roleId) throws SQLException {
        WorkRole workRole = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM workrole WHERE roleid = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, roleId);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                Integer roleIdFromDatabase = rs.getInt("role_id");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                double salary = rs.getDouble("salary");
                Date creationDate = rs.getDate("creation_date");
                workRole = new WorkRole(roleId, title, desc, salary, creationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
        return workRole;
    }

    @Override
    public List<WorkRole> getallWorkRoles() throws SQLException {
        List<WorkRole> roles = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM work_role";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Integer roleId = rs.getInt("role_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                double salary = rs.getDouble("salary");
                Date creationDate = rs.getDate("creation_date");
                WorkRole workRole = new WorkRole(roleId, title, description, salary, creationDate);
                roles.add(workRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeConnection(conn);
        }
        return roles;
    }

    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {
        try {
            conn = JDBCUtil.getConnection();
            String sql = """
                    INSERT INTO work_role (title, description, salary, creation_date)
                    VALUES (?, ?, ?, ?)
                    """;
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, workRole.getTitle());
            pStmt.setString(2, workRole.getDescription());
            pStmt.setDouble(3, workRole.getSalary());
            pStmt.setDate(4, workRole.getCreationDate());

            int rows = pStmt.executeUpdate();
            System.out.println("Rows affected: " + rows);

            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Override
    public void updateWorkRole(WorkRole workRole) throws SQLException {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE work_role SET title = ?, description = ?, salary = ?, creation_date = ? " +
                    "WHERE role_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, workRole.getTitle());
            pStmt.setString(2, workRole.getDescription());
            pStmt.setDouble(3, workRole.getSalary());
            pStmt.setDate(4, workRole.getCreationDate());
            pStmt.setInt(5, workRole.getRoleId());

            int rows = pStmt.executeUpdate();

            JDBCUtil.commit(conn);

            if (rows > 0) {
                System.out.println("Workrole updated.");
//            } else {
//                throw new WorkRoleNotFoundException("No workrole with the id " +
//                        workRole.getRoleId() + " was found.");
//            }
            }
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
    public void deleteWorkRole(WorkRole workRole) throws SQLException {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM work_role WHERE role_id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, workRole.getRoleId());

            int rows = pStmt.executeUpdate();
            if (rows == 0){
                throw new SQLException("Workrole not found or could not be deleted.");
            } else {
            System.out.println("Rows affected: " + rows);
            }

            pStmt.executeUpdate();
            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            JDBCUtil.rollback(conn);
            throw new RuntimeException("Error deleting workrole", e);
        } finally {
            JDBCUtil.closePreparedStatement(pStmt);
            JDBCUtil.closeConnection(conn);
        }
    }
}
