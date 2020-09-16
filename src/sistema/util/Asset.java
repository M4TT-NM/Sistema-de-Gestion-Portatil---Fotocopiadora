/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sistema.db.Database;
import sistema.db.SQLoperation;

/**
 * This class is used to deal with assets administration in the database.
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Asset {
    
    /**
     * This method is used to add assets to the database.
     * @param name is the name of the asset.
     * @param account_name is the account of the asset.
     * @param value is the value of the asset.
     */
    public static void add(String name, String account_name, double value){
        
        if(value > 0){
            try {
                Connection cn = Database.connect();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Assets WHERE Name = '" + name + "'");

                if(!rs.next()){

                    SQLoperation.close(rs,st,cn);

                    SQLoperation.insert("Assets", "'" + name + "', '"+ account_name + "', " + value);

                } else {
                    JOptionPane.showMessageDialog(null, "Este activo ya existe.\n Intente de nuevo.");
                }            

                SQLoperation.close(rs,st,cn);                       

            } catch (SQLException e) {
                System.err.println("Error adding asset. " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ingrese numeros menores o igual a cero.\n Intente de nuevo.");            
        }
              
    }
    
    /**
     * This method is used to delete an asset from the database.
     * @param name is the name of the asset.
     */
    public static void delete(String name){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Assets WHERE Name = '" + name + "'");
            
            if(rs.next()){
            
                SQLoperation.close(rs,st,cn);
                
                SQLoperation.delete("Assets", "Name = '" + name + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este activo no existe.\n Intente de nuevo.");
            }            
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error deleting asset. " + e.getMessage());
        }
             
    }
    
    /**
     * This method is to modify completely an asset in the database.
     * @param oldName is the old name.
     * @param newName is the new name.
     * @param account_name is the new account's name.
     * @param value is the new value.
     */
    public static void modify(String oldName, String newName, String account_name, double value){
        
        if(value > 0){
            try {
                Connection cn = Database.connect();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Assets WHERE Name = '" + oldName + "'");

                if(rs.next()){

                    SQLoperation.close(rs,st,cn);

                    SQLoperation.update("Assets", "Name = '" + newName + "', Account_Name = '" + account_name + "', Value = " + value, "Name = '" + oldName + "'");

                } else {
                    JOptionPane.showMessageDialog(null, "Este activo no existe.\n Intente de nuevo.");
                }            

                SQLoperation.close(rs,st,cn);                       

            } catch (SQLException e) {
                System.err.println("Error modifying asset. " + e.getMessage());
            }            
        } else {
            JOptionPane.showMessageDialog(null, "No ingrese numeros menores o igual a cero.\n Intente de nuevo.");            
        }
                
    }
    
    /**
     * This method is used to rename an asset in the database.
     * @param oldName is the old name.
     * @param newName is the new name.
     */
    public static void modify(String oldName, String newName){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Assets WHERE Name = '" + oldName + "'");
            
            if(rs.next()){
            
                SQLoperation.close(rs,st,cn);
                
                SQLoperation.update("Assets", "Name = '" + newName + "'", "Name = '" + oldName + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Este activo no existe.\n Intente de nuevo.");
            }            
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error modifying asset. " + e.getMessage());
        }
              
    }
    
    /**
     * This method is to modify the value of an asset in the database.
     * @param name is the name of the asset.
     * @param value is the new value.
     */
    public static void modify(String name, double value){
        
        if(value > 0){
            try {
                Connection cn = Database.connect();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Assets WHERE Name = '" + name + "'");

                if(rs.next()){

                    SQLoperation.close(rs,st,cn);

                    SQLoperation.update("Assets", "Value = " + value, "Name = '" + name + "'");

                } else {
                    JOptionPane.showMessageDialog(null, "Este activo no existe.\n Intente de nuevo.");
                }            

                SQLoperation.close(rs,st,cn);                       

            } catch (SQLException e) {
                System.err.println("Error modifying asset. " + e.getMessage());
            }            
        } else {
            JOptionPane.showMessageDialog(null, "No ingrese numeros menores o igual a cero.\n Intente de nuevo.");            
        }
              
    }
    
    /**
     * This method is used to calculate the assets worth in the database.
     * @return assets worth as DOUBLE.
     */
    public static double totalWorth(){
      double worth = 0;
      
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Assets");
            
            while(rs.next()){
                worth += rs.getDouble("Value");
            }         
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error calculating worth. " + e.getMessage());
        }      
      
      return worth;
    }
    
}
