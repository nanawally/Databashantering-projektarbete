package se.anna.workplacedatabase.utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.anna.workplacedatabase.WorkRole;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWorkRoleDAOImpl {

    @BeforeEach
    void setUp() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = JDBCUtil.getConnection();

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("schema.sql");
            if (inputStream == null) {
                throw new FileNotFoundException("schema.sql file not found in resources folder");
            }

            String sql = new String(inputStream.readAllBytes());
            stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @AfterEach
    void tearDown() {
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS work_role");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Test
    public void testVerifyAddedWorkRole(){
        WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();
        WorkRole workRole = new WorkRole("exampletitle", "exampledesc",10, Date.valueOf("2000-01-01"));
        List<WorkRole> workRoles = new ArrayList<>();
        try {
            workRoleDAO.insertWorkRole(workRole);
            workRoles = workRoleDAO.getallWorkRoles();
            for (WorkRole role : workRoles) {
                System.out.println(role);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        assertEquals(1, workRoles.size());
    }
}
