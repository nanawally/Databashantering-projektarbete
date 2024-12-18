package se.anna.workplacedatabase.utility;

import se.anna.workplacedatabase.WorkRole;

import java.sql.SQLException;
import java.util.List;

public interface WorkRoleDAO {
    WorkRole getWorkRole(Integer roleId) throws SQLException;
    WorkRole getWorkRoleByTitle(String title) throws SQLException;
    List<WorkRole> getAllWorkRoles() throws SQLException;
    void insertWorkRole(WorkRole workRole) throws SQLException;
    void updateWorkRole(WorkRole workRole) throws SQLException;
    void deleteWorkRole(WorkRole workRole) throws SQLException;
}
