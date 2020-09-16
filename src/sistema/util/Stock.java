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

    /**
     * This method adds a product/service to the database.
     * @param name is the name of the product/service.
     * @param cost is the price of the product/service.
     * @param category what type is it: 'S' Service - 'P' Product.
     */
    public static void addProduct(String name, double cost, String category){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Name = '" + name + "'");
            
            if(!rs.next()){
                rs.close();
                st.close();
                cn.close();              
                if(!(cost <= 0)){
                    SQLoperation.insert("Selling", "'" + name + "', " + cost + ", '" + category + "'");
                } else {
                    JOptionPane.showMessageDialog(null, "No ingrese numeros negativos.");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio ya existe.\n Intente de nuevo.");
            }
            
            rs.close();
            st.close();
            cn.close();  
            
        } catch (SQLException e) {
            System.err.println("Error al añadir producto. " + e.getMessage());
        }
        
    }
    
    /**
     * This method cleans the whole stock in the database.
     */
    public static void cleanStock(){
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
            
            if(amount > 0){
                SQLoperation.truncate("Selling");
                JOptionPane.showMessageDialog(null,"Inventario limpiado.");
            } else {
                JOptionPane.showMessageDialog(null,"El inventario esta vacio.");
            }
            
            
        } catch (SQLException e) {            
            System.err.println("Error counting stock: " + e.getMessage());
        }    
    
    }
    
    /**
     * This method deletes a product/service by name.
     * @param name is the name of that product/service.
     */
    public static void deleteProduct(String name){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + name + "'");
            
            if(rs.next()){
                rs.close();
                st.close();
                cn.close();                  
                SQLoperation.delete("Selling", "Name = '" + name + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }
            
            rs.close();
            st.close();
            cn.close();          
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }        
        
    }
    
    /**
     * This method is used to delete by category in the database. It deletes all the
     * registers with the same category.
     * @param category is the category (S or P).
     */
    public static void deleteProduct(char category){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Category = '" + category + "'");
            
            if(rs.next()){
                rs.close();
                st.close();
                cn.close();                  
                SQLoperation.delete("Selling", "Category = '" + category + "'");
                
            } else {
                switch (category){
                    case 'S':
                        JOptionPane.showMessageDialog(null, "Aún no hay servicios en el sistema.");
                            break;
                    case 'P':
                        JOptionPane.showMessageDialog(null, "Aún no hay productos en el sistema.");
                        break;
                }
            }
            
            rs.close();
            st.close();
            cn.close();  
            
        } catch (SQLException e) {
            System.err.println("Error al borrar producto/servicio. " + e.getMessage());
        } 
        
        
    }
    
    /**
     * This method modifies a product/service completely.
     * @param oldName is the old name of the product/service.
     * @param newName is the new name of the product/service.
     * @param cost is the new cost of the product/service.
     */
    public static void modifyProduct(String oldName, String newName, double cost){

        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + oldName + "'");
            
            if(rs.next()){
                rs.close();
                st.close();
                cn.close();                  
                SQLoperation.update("Selling", "Name = '" + newName + "', Cost = " + cost, "Name = '" + oldName + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }

            rs.close();
            st.close();
            cn.close();  
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }     
    }
    
    /**
     * This method modifies the cost of a product/service.
     * @param name is the name of the product/service.
     * @param cost is the new cost of the product/service.
     */
    public static void modifyProduct(String name, double cost){

        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + name + "'");
            
            if(rs.next()){
                rs.close();
                st.close();
                cn.close();            
                SQLoperation.update("Selling", "Cost = " + cost, "Name = '" + name + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }
            
            rs.close();
            st.close();
            cn.close(); 
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }     
    }
    
    /**
     * This method modifies the name of a product/service.
     * @param oldName is the old name.
     * @param newName is the new name.
     */
    public static void modifyProduct(String oldName, String newName){

        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Name FROM Selling WHERE Name = '" + oldName + "'");
            
            if(rs.next()){
                rs.close();
                st.close();
                cn.close();        
                
                SQLoperation.update("Selling", "Name = '" + newName + "'", "Name = '" + oldName + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este producto/servicio no existe.\n Intente de nuevo.");
            }
            
            rs.close();
            st.close();
            cn.close(); 
            
        } catch (SQLException e) {
            System.err.println("Error al producto/servicio. " + e.getMessage());
        }    
    }   
    
    /**
     * This method calculates the amount of products avaliable in stock.
     * @return the amount of products in stock.
     */
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
    
    /**
     * This method calculates the amount of products OR services avaliable in stock.
     * @param category is the category (S or P).
     * @return the amount of products or services in stock.
     */
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
    
    /**
     * This method search the price of a product/service by name.
     * @param product is the name of the product/service.
     * @return the price.
     */
    public static double priceOf(String product){
        double price = 0;
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Name = '" + product + "'");
        
            if(rs.next()){
                price = rs.getDouble("Cost");
            } else {
                System.out.println("Este producto no existe.");
            }
            
            rs.close();
            st.close();
            cn.close();
        
        } catch (SQLException e) {
            System.err.println("Error searching price: " + e.getMessage());
        }
        
        return price;
    }
    
    /**
     * This method shows with JOptionPanes the product/service information.
     * @param name is the name of the product/service.
     */
    public static void productInfo(String name){
     
        String product_name, product_cost, product_type;
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Name = '" + name + "'");
            
            if(rs.next()){
                
                product_cost = rs.getString("Cost");
                product_name = rs.getString("Name");
                product_type = (rs.getString("Category").equals('S')) ? "Servicio" : "Producto";
                
                JOptionPane.showMessageDialog(null, "Nombre: " + product_name
                                               + "\n Precio: " + product_cost
                                               + "\n Tipo: " + product_type);
            } else {
                System.out.println("Este producto no existe.");
            }

            rs.close();
            st.close();
            cn.close();            
            
        } catch (SQLException e) {
            System.err.println("Error searching information of: " + name + "\n" + "Error: " + e.getMessage());
        }
        
    }
    
   
}
