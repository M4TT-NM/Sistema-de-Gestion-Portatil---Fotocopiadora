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
 * This class is to register assets accounts in the database.
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Account {
    
    /**
     * This method adds an account to the database.
     * @param name is the account name.
     * @param type is the type of Asset.  
     */
    public static void add(String name, char type){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Accounts WHERE Name = '" + name + "'");
            
            if(!rs.next()){
            
                SQLoperation.close(rs,st,cn);
                
                SQLoperation.insert("Accounts", "'" + name + "', '" + type + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Esta cuenta ya existe.\n Intente de nuevo.");
            }            
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error adding account. " + e.getMessage());
        }
        
    }

    /**
     * This method is used to delete an account in the database.
     * @param name is the account's name.
     */
    public static void delete(String name){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Accounts WHERE Name = '" + name + "'");
            
            if(rs.next()){
            
                SQLoperation.close(rs,st,cn);
                
                SQLoperation.delete("Accounts", "Name = '" + name + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Esta cuenta no existe.\n Intente de nuevo.");
            }            
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error deleting account. " + e.getMessage());
        }
                   
    }
    
    /**
     * This method is used to modify completely an account in the database.
     * @param oldName is the old name of the account.
     * @param newName is the new name for the account.
     * @param type is the new type of the account.
     */
    public static void modify(String oldName, String newName, char type){
         
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Accounts WHERE Name = '" + oldName + "'");
            
            if(rs.next()){
            
                SQLoperation.close(rs,st,cn);
                
                SQLoperation.update("Accounts", "Name = '"+ newName + "', '" + type + "'", "Name = '" + oldName + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Esta cuenta no existe.\n Intente de nuevo.");
            }            
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error modifying account. " + e.getMessage());
        }
            
    }
    
    /**
     * This method is used to modify the name of an account in the database.
     * @param oldName is the old name.
     * @param newName is the new name.
     */
    public static void modify(String oldName, String newName){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Accounts WHERE Name = '" + oldName + "'");
            
            if(rs.next()){
            
                SQLoperation.close(rs,st,cn);
                
                SQLoperation.update("Accounts", "Name = '" + newName + "'", "Name = '" + oldName + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Esta cuenta no existe.\n Intente de nuevo.");
            }            
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error modifying account. " + e.getMessage());
        }
             
    }
    
    /**
     * This method is used to modify the type of an account in the database.
     * @param name is the name of the account.
     * @param type is the new type.
     */
    public static void modify(String name, char type){
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Accounts WHERE Name = '" + name + "'");
            
            if(rs.next()){
            
                SQLoperation.close(rs,st,cn);
                
                SQLoperation.update("Accounts", "Type = '" + type + "'", "Name = '" + name + "'");
                
            } else {
                JOptionPane.showMessageDialog(null, "Esta cuenta no existe.\n Intente de nuevo.");
            }            
            
            SQLoperation.close(rs,st,cn);                       
            
        } catch (SQLException e) {
            System.err.println("Error modifying account. " + e.getMessage());
        }
             
    }
    
    
}
