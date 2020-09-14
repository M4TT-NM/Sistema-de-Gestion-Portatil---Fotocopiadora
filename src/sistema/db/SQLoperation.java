package sistema.db;

import java.sql.*;

/**
 * This class contains the standard methods to work with a Database, which one
 * should be defined in the DataBase class.
 *
 * @author Nicolas Matute M.Z
 * @version 1.4
 * @since 2020-07
 */
public class SQLoperation {

    /**
     * This method is used to insert data in determinate fields of table.
     * When finish, the connection and statement get closed.
     *
     * Remember: varchar values must be between ''.
     *
     * @param table is the table where you want to add a register.
     * @param fields are the specific fields you want to add a register.
     * @param values are the values you want to add.
     */
    public static void insert(String table, String fields, String values){
        Connection cn;
        Statement st;
        try{ 
            cn = Database.connect();
            st = cn.createStatement();
            st.executeUpdate("INSERT INTO " + table + " (" + fields + ") " + "VALUES (" + values + ")");
            st.close();
            cn.close();   
        } catch (SQLException e){
            System.err.println("Error inserting: " + e.getMessage());
        }             
        
    }

    /**
     * This method is used to insert data in all fields within table.
     * When finish, the connection and statement get closed.
     *
     * Remember: varchar values must be between ''.
     *
     * @param table is the table where you want to add a register.
     * @param values are the values you want to add.
     */
    public static void insert(String table, String values){

        Connection cn;
        Statement st;
        try{ 
            cn = Database.connect();
            st = cn.createStatement();
            st.executeUpdate("INSERT INTO " + table + " VALUES (" + values + ")");
            st.close();
            cn.close();   
        } catch (SQLException e){
            System.err.println("Error inserting: " + e.getMessage());                      
        }
    }

    /**
     * This method is used to delete data from any table. When finish, the
     * connection and statement get closed.
     *
     * Remember: varchar values must be between ''.
     *
     * @param table is the table where you want to delete a register.
     * @param condition is the condition to delete a specific register.
     */
    public static void delete(String table, String condition){

        Connection cn;
        Statement st;
        try{ 
            cn = Database.connect();
            st = cn.createStatement();
            st.executeUpdate("DELETE FROM " + table + " WHERE " + condition);
            st.close();
            cn.close();   
        } catch (SQLException e){
            System.err.println("Error deleting: " + e.getMessage());
        }        
        

    }

    /**
     * This method is used to update registers from any table. When finish, the
     * connection and statement get closed.
     *
     * Remember: varchar values must be between ''.
     *
     * @param table is the table where you want to update a register.
     * @param update is the data you want to modify. This must follow the format: 'field = new_data'
     * @param condition is the condition to update a specific register.
     */
    public static void update(String table, String update, String condition){

        Connection cn;
        Statement st;
        try{ 
            cn = Database.connect();
            st = cn.createStatement();
            st.executeUpdate("UPDATE " + table + " SET " + update + " WHERE " + condition);
            st.close();
            cn.close();   
        } catch (SQLException e){
            System.err.println("Error updating: " + e.getMessage());
        }        
    }


    /**
     * This method is used to do a custom operation, it could be a insert, delete or update. Be free of use INNER
     * JOIN and other commands. When finish, the connection and statement get
     * closed.
     *
     * Remember: varchar values must be between ''.
     *
     * @param operation is the custom update.
     */
    public static void custom(String operation){
        Connection cn;
        Statement st;
        try{ 
            cn = Database.connect();
            st = cn.createStatement();
            st.executeUpdate(operation);
            st.close();
            cn.close();   
        } catch (SQLException e){
            System.err.println("Error in custom sql: " + e.getMessage());
        }        
    }
    
    /**
     * This method is used to clean the database.
     * @param table, this is the table you want to truncate.
     */
    
    public static void truncate(String table){
        Connection cn;
        Statement st;
        try{ 
            cn = Database.connect();
            st = cn.createStatement();
            st.executeUpdate("DELETE FROM " + table);
            st.close();
            cn.close();   
        } catch (SQLException e){
            System.err.println("Error in truncate: " + e.getMessage());
        }
        
    }

}
