package sistema.db;

import java.sql.*;

/**
 * 
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Database {
    
    static Connection cn = null;
    
    public static Connection connect(){
                
        try {
            Class.forName("org.sqlite.JDBC");
            
            cn = DriverManager.getConnection("jdbc:sqlite:central.db");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error in class: " + e.getMessage());
        } catch (SQLException e){
            System.err.println("Error in sql: " + e.getMessage());
        }
        
        return cn;
    }

}
