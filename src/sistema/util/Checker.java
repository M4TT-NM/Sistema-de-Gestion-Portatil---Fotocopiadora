/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema.util;
import sistema.db.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * This class is in charge of checking important information for the system. 
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Checker {

    private final String KEY = "2WSX1QAZ";
    
    /**
     * This method checks the key wrote by user.
     * If it is the first time the user starts the system, it is going to solicitate the KEY.
     * If the key is wrong, the client won't be able to use the system.
     * @return true if key is right, else false.
     */
    public boolean keyCheck(){
        boolean response = false;
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Data FROM PrivateData WHERE Name = 'SegurityCode'");
            
            if(rs.getString("Data") != null){
                String key = rs.getString("Data");
                
                response = key.equals(KEY);
            
                SQLoperation.close(rs,st,cn);
            
            } else {
            
                SQLoperation.close(rs,st,cn);
                          
                String key;
                do {                    
                    key = JOptionPane.showInputDialog("Ingresa la clave de seguridad:");
                } while (key == null || !key.equals(KEY));
                
                SQLoperation.update("PrivateData", "Data = '" + key + "'", "Name = 'SegurityCode'");
                
                response = key.equals(KEY);
                
                JOptionPane.showMessageDialog(null, "Sistema listo para usarse.");
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking key: " + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * This method is used to set the folder where reports will save.
     */
    public void setReportFolder(){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Data FROM PrivateData WHERE Name = 'ReportDestination'");
            
            if(rs.getString("Data") == null){
                
            
                SQLoperation.close(rs,st,cn);
            
                                              
                String folder_path = JOptionPane.showInputDialog("Ingrese la ruta a la carpeta:");
                
                if(folder_path != null || !folder_path.equals("")){
                    SQLoperation.update("PrivateData", "Data = '" + folder_path + "'", "Name = 'ReportDestination'");
                    JOptionPane.showMessageDialog(null, "Carpeta actualizada.");
                } else {
                    JOptionPane.showMessageDialog(null, "No ha incluido la carpeta para \n los reportes.");
                }
              
            } else {
                SQLoperation.close(rs,st,cn);                
            }
             
        } catch (SQLException e) {
            System.err.println("Error setting reports folder: " + e.getMessage());
        }
        
    }
    
    /**
     * This method returns the folder path of reports.
     * @return the folder path.
     */
    public String getReportFolder(){
        String folder_path = null;
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Data FROM PrivateData WHERE Name = 'ReportDestination'");
            
            folder_path = rs.getString("Data");

            SQLoperation.close(rs,st,cn);
                        
        } catch (SQLException e) {
            System.err.println("Error getting reports folder: " + e.getMessage());
        }
        return folder_path;
    }

    /**
     * This method is used to set the business name in the database.
     */
    public void setBusinessName(){

        String businessName;

        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Data FROM PrivateData WHERE Name = 'BusinessName'");
            
            if(rs.getString("Data") == null){
                SQLoperation.close(rs,st,cn);
                businessName = JOptionPane.showInputDialog("Ingrese el nombre de su negocio:");
                SQLoperation.update("PrivateData", "Data = '" + businessName + "'", "Name = 'BusinessName'");
            } else {
                SQLoperation.close(rs,st,cn);                
            }
            

            
        } catch (SQLException e) {
            System.err.println("Error setting business name: " + e.getMessage());
        }               
    }
    
    /**
     * This method is used to get the business name registered in the database.
     * @return the business name. In case of none, returns null.
     */
    public String getBusinessName(){
        String businessName = "";
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Data FROM PrivateData WHERE Name = 'BusinessName'");
            
                businessName = rs.getString("Data");
                
            SQLoperation.close(rs,st,cn);
            
            return businessName;
            
        } catch (SQLException e) {
            System.err.println("Error getting business name: " + e.getMessage());
        }
        return businessName;
    }
       
    
}
