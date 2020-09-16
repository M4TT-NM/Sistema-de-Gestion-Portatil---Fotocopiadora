/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema.util;
import sistema.db.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * 
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Checker {

    private String KEY = "2WSX1QAZ";
    
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
                rs.close();
                st.close();
                cn.close();
            } else {
                rs.close();
                st.close();
                cn.close();
                
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
                
                rs.close();
                st.close();
                cn.close();
                                              
                String folder_path = JOptionPane.showInputDialog("Ingrese la ruta a la carpeta:");
                
                if(folder_path.contains("/")){               
                    SQLoperation.update("PrivateData", "Data = '" + folder_path + "'", "Name = 'ReportDestination'");
                    JOptionPane.showMessageDialog(null, "Carpeta actualizada.");
                } else {
                    JOptionPane.showMessageDialog(null, "Escriba una ruta valida.");
                }                
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

            rs.close();
            st.close();
            cn.close();  
            
        } catch (SQLException e) {
            System.err.println("Error getting reports folder: " + e.getMessage());
        }
        return folder_path;
    }


    
    
    
    
    
    
}
