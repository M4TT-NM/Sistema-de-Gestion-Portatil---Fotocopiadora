/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema.util;

import javax.swing.JOptionPane;
import org.joda.time.*;
import sistema.db.SQLoperation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.sql.SQLException;
import sistema.db.Database;



/**
 * This class is used to register sells onto the database.
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Sell {

    static LocalTime t = new LocalTime();
    static LocalDate d = new LocalDate();
    static Checker checker = new Checker();
    
    /**
     * This method is used to register sells in the database.
     * @param concept is the sell concept.
     * @param amount is the amount of products.
     * @param value is the value of the product.
     */
    public static void register(String concept, int amount, double value){
        int hour = t.getHourOfDay();
        String time = (hour < 12) ? "am" : "pm";        

        switch(hour){
            case 13: hour = 1;
                break;
            case 14: hour = 2;
                break;
            case 15: hour = 3;
                break;
            case 16: hour = 4;
                break;
            case 17: hour = 5;
                break;
            case 18: hour = 6;
                break;
            case 19: hour = 7;
                break;
            case 20: hour = 8;
                break;
            case 21: hour = 9;
                break;
            case 22: hour = 10;
                break;
            case 23: hour = 11;
                break;
            case 0: hour = 12;
                break;                    
        }
        

        String minute = (t.getMinuteOfHour() < 10) ? "0" + Integer.toString(t.getMinuteOfHour()) : Integer.toString(t.getMinuteOfHour());
        String second = (t.getSecondOfMinute() < 10) ? "0" + Integer.toString(t.getSecondOfMinute()) : Integer.toString(t.getSecondOfMinute());
        double total = amount * value;
        
        if(amount > 0 && value > 0){
            SQLoperation.insert("ReportData", "'" + hour + ":" + minute + ":" + second + " " + time + "', '" + concept + "'," + amount + "," + value + "," + total);
            SQLoperation.insert("Temp", "'" + hour + ":" + minute + ":" + second + " " + time + "', '" + concept + "'," + amount + "," + value + "," + total);
        } else {
            JOptionPane.showMessageDialog(null, "No ingrese numeros menores o igual a cero.\n Intente de nuevo.");
        }
        
    }
    
    /**
     * This method shows the total sold of the a temp transaction. Then cleans.
     */
    public static void getTempSold(){
        double total = 0;
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Total FROM Temp");
            
            while(rs.next()){
                total += rs.getDouble("Total");
            }
            
            SQLoperation.close(rs, st, cn);
            
        } catch (SQLException e) {
            System.err.println("Error getting total: " + e);
        }
        SQLoperation.truncate("Temp");
        JOptionPane.showMessageDialog(null, "El total es: $" + total);
    }    
    
    
    
    /**
     * This method calculates the total of $ per day.
     * @return the total of money per day.
     */
    public static double getTotalSold(){
        double total = 0;
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Total FROM ReportData");
            
            while(rs.next()){
                total += rs.getDouble("Total");
            }
            
            SQLoperation.close(rs, st, cn);
            
        } catch (SQLException e) {
            System.err.println("Error getting total: " + e);
        }
        return total;
    }
    
    /**
     * This method deletes a sell register in the database.
     * @param time the hour of the transaction.
     */
    public static void delete(String time){
       SQLoperation.delete("ReportData", "Time = '" + time + "'");
    }
    
    /**
     * This method cleans the sells register in the database.
     */
    public static void cleanRegisters(){
        SQLoperation.truncate("ReportData");
    }
    
    /**
     * This method generates sells reports as pdf files.
     */
    public static void generateReport(){
        
        String month = null;
        switch(d.getMonthOfYear()){
            case 1: month = "ENERO";
                break;
            case 2: month = "FEBRERO";
                break;
            case 3: month = "MARZO";
                break;
            case 4: month = "ABRIL";
                break;
            case 5: month = "MAYO";
                break;
            case 6: month = "JUNIO";
                break;
            case 7: month = "JULIO";
                break;
            case 8: month = "AGOSTO";
                break;
            case 9: month = "SEPTIEMBRE";
                break;
            case 10: month = "OCTUBRE";
                break;
            case 11: month = "NOVIEMBRE";
                break;
            case 12: month = "DICIEMBRE";
                break;                
        }
        
        Document doc = new Document();
        
        try {
            String path = checker.getReportFolder() + "/";
            String day = "Dia " + Integer.toString(d.getDayOfMonth()) + "-" + Integer.toString(d.getMonthOfYear()) + "-" + Integer.toString(d.getYear());
            
            PdfWriter.getInstance(doc, new FileOutputStream(path + day + ".pdf"));
            
            Paragraph intro = new Paragraph();
            Paragraph title = new Paragraph();
            Paragraph subtitle = new Paragraph();
            title.setAlignment(Chunk.ALIGN_CENTER);
            subtitle.setAlignment(Chunk.ALIGN_CENTER);
            intro.setAlignment(Chunk.ALIGN_JUSTIFIED);
            title.setFont(FontFactory.getFont("Arial", 20, Font.NORMAL, BaseColor.BLACK));
            subtitle.setFont(FontFactory.getFont("Arial", 16, Font.ITALIC, BaseColor.BLACK));
            title.add("DIA " + d.getDayOfMonth() + " DE " + month + " DEL " + d.getYear());
            subtitle.add("\n" + checker.getBusinessName());
            intro.setFont(FontFactory.getFont("Arial", 14, Font.NORMAL, BaseColor.BLACK));
            intro.add("\n\n\n Lista de ventas realizadas.\n\n\n\n\n");
            
            doc.open();
            doc.add(title);
            doc.add(subtitle);
            doc.add(intro);
            
            PdfPTable table = new PdfPTable(5);
            
            table.addCell("Hora");
            table.addCell("Concepto");
            table.addCell("Cantidad");
            table.addCell("Costo");
            table.addCell("Total");
            
            try {
                Connection cn = Database.connect();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM ReportData");
                
                if(rs.next()){
                    
                    do {                        
                        table.addCell(rs.getString("Time"));
                        table.addCell(rs.getString("Sell-Concept"));
                        table.addCell(rs.getString("Amount"));
                        table.addCell("$"+ rs.getString("Value"));
                        table.addCell("$"+ rs.getString("Total"));
                    } while (rs.next());
                    
                    SQLoperation.close(rs, st, cn);
                    
                    doc.add(table);
                    
                    Paragraph summary = new Paragraph();
                    summary.setAlignment(Chunk.ALIGN_RIGHT);
                    summary.setFont(FontFactory.getFont("Arial", 14, Font.NORMAL, BaseColor.BLACK));
                    summary.add("\n\n\n\n\n Total vendido hoy: $" + Sell.getTotalSold());
                    
                    doc.add(summary);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Aún no han habido ventas en este dia.");
                }
                SQLoperation.close(rs, st, cn);
                doc.close();
                SQLoperation.truncate("Temp");
                Sell.cleanRegisters();
                JOptionPane.showMessageDialog(null, "Informe de ventas listo.");
                
            } catch (DocumentException | SQLException e) {
                System.err.println("Error creating report: " + e);
            }
            
            
        } catch (FileNotFoundException | DocumentException e) {
            System.err.println("Error creating report: " + e);
        }
        
    }
}
