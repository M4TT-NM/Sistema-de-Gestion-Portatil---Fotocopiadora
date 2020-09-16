/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema.util;

import javax.swing.JOptionPane;
import org.joda.time.*;
import sistema.db.SQLoperation;

/**
 * This class is used to register sells onto the database.
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Sell {

    static LocalTime t = new LocalTime();
    static LocalDate d = new LocalDate();
    
    /**
     * This method is used to register sells in the database.
     * @param concept is the sell concept.
     * @param amount is the amount of products.
     * @param value is the value of the product.
     */
    public static void register(String concept, int amount, double value){
        int hour = t.getHourOfDay();
        String time = (hour < 12) ? "am" : "pm";
        String minute = (t.getMinuteOfHour() < 10) ? "0" + Integer.toString(t.getMinuteOfHour()) : Integer.toString(t.getMinuteOfHour());
        double total = amount * value;
        
        if(amount > 0 && value > 0){
            SQLoperation.insert("ReportData", "'" + hour + ":" + minute + " " + time + "', '" + concept + "'," + amount + "," + value + "," + total);
        } else {
            JOptionPane.showMessageDialog(null, "No ingrese numeros menores o igual a cero.\n Intente de nuevo.");
        }
        
    }
    
    /**
     * This method deletes a sell register in the database.
     * @param time the hour of the transaction.
     */
    public static void delete(String time){
       SQLoperation.delete("ReportData", "Time = '" + time + "'");
    }
}
