package sistema.util;
import java.sql.*;
import javax.swing.JOptionPane;
import sistema.db.Database;
import sistema.db.SQLoperation;

/**
 * 
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Stock {

    
    public static void addProduct(String name, double cost, String category){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + name + "'");
            
            if(!rs.next()){
                
                if(!(cost <= 0)){
                    SQLoperation.insert("Selling", name + "," + cost + "," + category);
                } else {
                    JOptionPane.showMessageDialog(null, "No ingrese numeros negativos.");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio ya existe.\n Intente de nuevo.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al añadir producto. " + e.getMessage());
        }
        
    }
    
    public static void cleanStock(){
        SQLoperation.truncate("Selling");
    }
    
    public static void deleteProduct(String name){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + name + "'");
            
            if(rs.next()){
                
                SQLoperation.delete("Selling", "Name = '" + name + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }        
        
    }
    
    public static void deleteProductByCategory(char category){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Name = '" + category + "'");
            
            if(rs.next()){
                
                SQLoperation.delete("Selling", "Category = '" + category + "'");
                
            } else {
                switch (category){
                    case 's':
                        JOptionPane.showMessageDialog(null, "Aún no hay servicios en el sistema.");
                            break;
                    case 'p':
                        JOptionPane.showMessageDialog(null, "Aún no hay productos en el sistema.");
                        break;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al borrar producto/servicio. " + e.getMessage());
        } 
        
        
    }
    
    public static void modifyProduct(String oldName, String newName, double cost){

        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + oldName + "'");
            
            if(rs.next()){
                
                SQLoperation.update("Selling", "Name = '" + newName + "', Cost = " + cost, "Name = '" + oldName + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }     
    }
    
    public static void modifyProduct(String name, double cost){

        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + name + "'");
            
            if(rs.next()){
                
                SQLoperation.update("Selling", "Cost = " + cost, "Name = '" + name + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }     
    }
    
    public static void modifyProduct(String oldName, String newName){

        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + oldName + "'");
            
            if(rs.next()){
                
                        SQLoperation.update("Selling", "Name = '" + newName + "'", "Name = '" + oldName + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }    
    }   
    
    public static int countStock(){
        int amount = 0;
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling");
            
            while(rs.next()){
                amount += 1;
            }
            
            rs.close();
            st.close();
            cn.close();
            
            
        } catch (SQLException e) {            
            System.err.println("Error counting stock: " + e.getMessage());
        }
        
        return amount;
    }
    
    public static int countStock(String category){
        int amount = 0;
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Category = '" + category + "'");
            
            while(rs.next()){
                amount += 1;
            }
            
            rs.close();
            st.close();
            cn.close();
            
            
        } catch (SQLException e) {            
            System.err.println("Error counting stock: " + e.getMessage());
        }
        
        return amount;
    }    
    

    
   
}
