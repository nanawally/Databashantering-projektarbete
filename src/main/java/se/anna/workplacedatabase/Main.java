package se.anna.workplacedatabase;

public class Main {
    public static void main(String[] args) {

    }
}


//remember to set autocommit to false
//probably put a rollback in the catch of the try{conn.commit} (info in lektion 2)
//make a PersonNotFoundException class, see lektion 5 ca 14:00
//implement equals and hashcode?
//perhaps structure classes as (one package: DAO-interface, employeeDAOimpl, WorkroleDAOimpl) maybe in utility package?
//make a schema.sql file, ask abt it + check lektion 6 pt 2 7:00
//remember to make application.properties in both main and test
//ask abt pstmt vs stmt in the method(s) closeStatement()