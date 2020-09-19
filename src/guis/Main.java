/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;
import sistema.util.*;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import sistema.db.Database;
import sistema.db.SQLoperation;

/**
 *
 * @author Nicolas Matute M.Z <manuelnmatute@gmail.com>
 */
public class Main extends javax.swing.JFrame {

    static Checker checker = new Checker();
    static CardLayout MaincardLayout;
    DefaultTableModel sellsModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells wont be editable
           return false;
        };
    };        
    DefaultTableModel stockModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells wont be editable
           return false;
        };
    };        
    DefaultTableModel assetsModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells wont be editable
           return false;
        };
    };

    
    
    /**
     * Creates new form Main 2WSX1QAZ
     */
    public Main() {
        
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();        
        //DB checks & customization
        if(!checker.keyCheck()){
            System.exit(0);
        }
        checker.setReportFolder();
        checker.setBusinessName();
        businessNameLabel.setText(checker.getBusinessName());
        //Main stuff
        category.setSelectedIndex(0);
        product.setModel(new DefaultComboBoxModel(getProducts()));
        
        MaincardLayout = (CardLayout) Container.getLayout();
        
        MaincardLayout.show(Container, "Home");
        
        //Tables stuff
        
        sellsModel.addColumn("Concepto");
        sellsModel.addColumn("Cantidad");
        sellsModel.addColumn("Precio");
        sellsModel.addColumn("Total");
        
        stockModel.addColumn("Nombre");
        stockModel.addColumn("Precio");
        stockModel.addColumn("Categoria");
        
        assetsModel.addColumn("Nombre");
        assetsModel.addColumn("Cuenta");
        assetsModel.addColumn("Valor");        
        
        fillAssets();
        fillSells();
        fillStock();        
        sellsTable.setModel(sellsModel);
        StockTable.setModel(stockModel);
        AssetsTable.setModel(assetsModel);
        sellsTable.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e){
                int row = sellsTable.rowAtPoint(e.getPoint());//any row can be selected.
                
                if(row > -1){//From row  and so on.
                    String name = sellsTable.getValueAt(row, 0).toString();
                    int confirmation = JOptionPane.showConfirmDialog(null,"¿Borrar?",null,JOptionPane.YES_NO_OPTION);
                    if(confirmation == JOptionPane.YES_OPTION){
                        ((DefaultTableModel)sellsTable.getModel()).removeRow(row);
                        SQLoperation.delete("Temp", "Sell-Concept = '" + name + "'");
                        SQLoperation.delete("ReportData", "Sell-Concept = '" + name + "'");
                        fillSells();
                    }
                }
                
            }    
        
        });
        StockTable.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e){
                int row = StockTable.rowAtPoint(e.getPoint());//any row can be selected.
                
                if(row > -1){//From row  and so on.
                    String name = StockTable.getValueAt(row, 0).toString();
                    int confirmation = JOptionPane.showConfirmDialog(null,"¿Borrar?",null,JOptionPane.YES_NO_OPTION);
                    if(confirmation == JOptionPane.YES_OPTION){
                        ((DefaultTableModel)StockTable.getModel()).removeRow(row);
                        Stock.deleteProduct(name);
                        fillStock();
                    }
                }
                
            }    
        
        });
        AssetsTable.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e){
                int row = AssetsTable.rowAtPoint(e.getPoint());//any row can be selected.
                
                if(row > -1){//From row  and so on.
                    String name = AssetsTable.getValueAt(row, 0).toString();
                    int confirmation = JOptionPane.showConfirmDialog(null,"¿Borrar?",null,JOptionPane.YES_NO_OPTION);
                    if(confirmation == JOptionPane.YES_OPTION){
                        ((DefaultTableModel)AssetsTable.getModel()).removeRow(row);
                        Asset.delete(name);
                        fillAssets();
                        worthLabel.setText("$" + Double.toString(Asset.totalWorth()));                        
                    }
                }
                
            }    
        
        });

    }
    
    @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("guis/images/011-computing-cloud-2.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogoZone = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BarMenu = new javax.swing.JPanel();
        closeButton = new guis.RSButtonMetro();
        minimizeButton = new guis.RSButtonMetro();
        MainMenu = new javax.swing.JPanel();
        businessNameLabel = new javax.swing.JLabel();
        homeButton = new guis.RSButtonMetro();
        stockButton = new guis.RSButtonMetro();
        assetsButton = new guis.RSButtonMetro();
        Container = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        category = new javax.swing.JComboBox<>();
        product = new javax.swing.JComboBox<>();
        amount = new javax.swing.JTextField();
        sellButton = new guis.RSButtonMetro();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sellsTable = new javax.swing.JTable();
        addButton = new guis.RSButtonMetro();
        reportButton = new guis.RSButtonMetro();
        jLabel14 = new javax.swing.JLabel();
        StockPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        addProduct = new guis.RSButtonMetro();
        jScrollPane3 = new javax.swing.JScrollPane();
        StockTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        productCategory = new javax.swing.JComboBox<>();
        productName = new javax.swing.JTextField();
        productPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        AssetsPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        assets_add = new guis.RSButtonMetro();
        jScrollPane4 = new javax.swing.JScrollPane();
        AssetsTable = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        worthLabel = new javax.swing.JLabel();
        name_txt = new javax.swing.JTextField();
        value_txt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        account_txt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoZone.setBackground(new java.awt.Color(26, 46, 54));
        LogoZone.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guis/images/log.png"))); // NOI18N
        LogoZone.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, 100));

        jLabel2.setBackground(new java.awt.Color(23, 63, 80));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Google");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LogoZone.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 140, -1, 30));

        jLabel3.setBackground(new java.awt.Color(23, 63, 80));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Iconos diseñados por");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LogoZone.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));

        getContentPane().add(LogoZone, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 160, -1));

        BarMenu.setBackground(new java.awt.Color(255, 0, 0));
        BarMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeButton.setBackground(new java.awt.Color(255, 0, 0));
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guis/images/close-button.png"))); // NOI18N
        closeButton.setBorderPainted(false);
        closeButton.setColorHover(new java.awt.Color(26, 46, 54));
        closeButton.setColorNormal(new java.awt.Color(255, 0, 0));
        closeButton.setColorPressed(new java.awt.Color(26, 46, 54));
        closeButton.setFocusPainted(false);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButtonMouseReleased(evt);
            }
        });
        BarMenu.add(closeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 50, 30));

        minimizeButton.setBackground(new java.awt.Color(255, 0, 0));
        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guis/images/expand-button.png"))); // NOI18N
        minimizeButton.setBorderPainted(false);
        minimizeButton.setColorHover(new java.awt.Color(26, 46, 54));
        minimizeButton.setColorNormal(new java.awt.Color(255, 0, 0));
        minimizeButton.setColorPressed(new java.awt.Color(26, 46, 54));
        minimizeButton.setFocusPainted(false);
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });
        BarMenu.add(minimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 50, 30));

        getContentPane().add(BarMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 640, 30));

        MainMenu.setBackground(new java.awt.Color(26, 46, 54));
        MainMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        businessNameLabel.setBackground(new java.awt.Color(23, 63, 80));
        businessNameLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        businessNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        businessNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        businessNameLabel.setText(checker.getBusinessName());
        businessNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MainMenu.add(businessNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 100));

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guis/images/home-button.png"))); // NOI18N
        homeButton.setText("Principal");
        homeButton.setBorderPainted(false);
        homeButton.setColorHover(new java.awt.Color(255, 0, 0));
        homeButton.setColorNormal(new java.awt.Color(26, 46, 54));
        homeButton.setColorPressed(new java.awt.Color(255, 0, 0));
        homeButton.setFocusPainted(false);
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        MainMenu.add(homeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 160, 50));

        stockButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guis/images/clipboard-paste-button.png"))); // NOI18N
        stockButton.setText("Inventario");
        stockButton.setBorderPainted(false);
        stockButton.setColorHover(new java.awt.Color(255, 0, 0));
        stockButton.setColorNormal(new java.awt.Color(26, 46, 54));
        stockButton.setColorPressed(new java.awt.Color(255, 0, 0));
        stockButton.setFocusPainted(false);
        stockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockButtonActionPerformed(evt);
            }
        });
        MainMenu.add(stockButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 160, 50));

        assetsButton.setText("Activos");
        assetsButton.setBorderPainted(false);
        assetsButton.setColorHover(new java.awt.Color(255, 0, 0));
        assetsButton.setColorNormal(new java.awt.Color(26, 46, 54));
        assetsButton.setColorPressed(new java.awt.Color(255, 0, 0));
        assetsButton.setFocusPainted(false);
        assetsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assetsButtonActionPerformed(evt);
            }
        });
        MainMenu.add(assetsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 160, 50));

        getContentPane().add(MainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 430));

        Container.setMaximumSize(new java.awt.Dimension(640, 570));
        Container.setMinimumSize(new java.awt.Dimension(640, 570));
        Container.setPreferredSize(new java.awt.Dimension(640, 570));
        Container.setLayout(new java.awt.CardLayout());

        Home.setBackground(new java.awt.Color(235, 236, 240));
        Home.setMaximumSize(new java.awt.Dimension(640, 570));
        Home.setMinimumSize(new java.awt.Dimension(640, 570));
        Home.setName("Home"); // NOI18N
        Home.setPreferredSize(new java.awt.Dimension(640, 570));
        Home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        category.setBackground(new java.awt.Color(26, 46, 54));
        category.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        category.setForeground(new java.awt.Color(255, 255, 255));
        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Producto", "Servicio" }));
        category.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        category.setOpaque(false);
        category.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categoryItemStateChanged(evt);
            }
        });
        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });
        Home.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 30));

        product.setBackground(new java.awt.Color(26, 46, 54));
        product.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        product.setForeground(new java.awt.Color(255, 255, 255));
        product.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Galletas" }));
        product.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        product.setOpaque(false);
        product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productActionPerformed(evt);
            }
        });
        Home.add(product, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 190, 30));

        amount.setBackground(new java.awt.Color(255, 255, 255));
        amount.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        amount.setForeground(new java.awt.Color(0, 0, 0));
        amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amount.setOpaque(false);
        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });
        Home.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 50));

        sellButton.setBackground(new java.awt.Color(255, 0, 0));
        sellButton.setText("VENDER");
        sellButton.setColorHover(new java.awt.Color(26, 46, 54));
        sellButton.setColorNormal(new java.awt.Color(255, 0, 0));
        sellButton.setColorPressed(new java.awt.Color(26, 46, 54));
        sellButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });
        Home.add(sellButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, 50));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NOMBRE");
        Home.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 20));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CANTIDAD");
        Home.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 90, 20));

        sellsTable.setBackground(new java.awt.Color(255, 255, 255));
        sellsTable.setForeground(new java.awt.Color(0, 0, 0));
        sellsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Concepto", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sellsTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(sellsTable);
        if (sellsTable.getColumnModel().getColumnCount() > 0) {
            sellsTable.getColumnModel().getColumn(0).setResizable(false);
            sellsTable.getColumnModel().getColumn(1).setResizable(false);
            sellsTable.getColumnModel().getColumn(2).setResizable(false);
            sellsTable.getColumnModel().getColumn(3).setResizable(false);
        }

        Home.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 580, 320));

        addButton.setBackground(new java.awt.Color(255, 0, 0));
        addButton.setText("AGREGAR");
        addButton.setColorHover(new java.awt.Color(26, 46, 54));
        addButton.setColorNormal(new java.awt.Color(255, 0, 0));
        addButton.setColorPressed(new java.awt.Color(26, 46, 54));
        addButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        Home.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, 50));

        reportButton.setBackground(new java.awt.Color(255, 0, 0));
        reportButton.setText("Generar Informe");
        reportButton.setColorHover(new java.awt.Color(26, 46, 54));
        reportButton.setColorNormal(new java.awt.Color(255, 0, 0));
        reportButton.setColorPressed(new java.awt.Color(26, 46, 54));
        reportButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });
        Home.add(reportButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 180, 50));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("CATEGORIA");
        Home.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 20));

        Container.add(Home, "Home");

        StockPanel.setBackground(new java.awt.Color(235, 236, 240));
        StockPanel.setMaximumSize(new java.awt.Dimension(640, 570));
        StockPanel.setMinimumSize(new java.awt.Dimension(640, 570));
        StockPanel.setPreferredSize(new java.awt.Dimension(640, 570));
        StockPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("INVENTARIO");
        StockPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 10, 250, 50));

        addProduct.setBackground(new java.awt.Color(255, 0, 0));
        addProduct.setText("AGREGAR");
        addProduct.setColorHover(new java.awt.Color(26, 46, 54));
        addProduct.setColorNormal(new java.awt.Color(255, 0, 0));
        addProduct.setColorPressed(new java.awt.Color(26, 46, 54));
        addProduct.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });
        StockPanel.add(addProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, -1, 50));

        StockTable.setBackground(new java.awt.Color(255, 255, 255));
        StockTable.setForeground(new java.awt.Color(0, 0, 0));
        StockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Precio", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(StockTable);
        if (StockTable.getColumnModel().getColumnCount() > 0) {
            StockTable.getColumnModel().getColumn(0).setResizable(false);
            StockTable.getColumnModel().getColumn(1).setResizable(false);
            StockTable.getColumnModel().getColumn(2).setResizable(false);
        }

        StockPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 580, 280));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CATEGORIA");
        StockPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 90, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("PRECIO");
        StockPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 90, 20));

        productCategory.setBackground(new java.awt.Color(26, 46, 54));
        productCategory.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        productCategory.setForeground(new java.awt.Color(255, 255, 255));
        productCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Producto", "Servicio" }));
        productCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productCategory.setOpaque(false);
        productCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCategoryActionPerformed(evt);
            }
        });
        StockPanel.add(productCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 110, 30));

        productName.setBackground(new java.awt.Color(255, 255, 255));
        productName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        productName.setForeground(new java.awt.Color(0, 0, 0));
        productName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productName.setOpaque(false);
        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });
        StockPanel.add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 170, 30));

        productPrice.setBackground(new java.awt.Color(255, 255, 255));
        productPrice.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        productPrice.setForeground(new java.awt.Color(0, 0, 0));
        productPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productPrice.setOpaque(false);
        productPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPriceActionPerformed(evt);
            }
        });
        StockPanel.add(productPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 90, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("NOMBRE");
        StockPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 90, 20));

        Container.add(StockPanel, "Stock");

        AssetsPanel.setBackground(new java.awt.Color(235, 236, 240));
        AssetsPanel.setMaximumSize(new java.awt.Dimension(640, 570));
        AssetsPanel.setMinimumSize(new java.awt.Dimension(640, 570));
        AssetsPanel.setPreferredSize(new java.awt.Dimension(640, 570));
        AssetsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ACTIVOS");
        AssetsPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 10, 250, 50));

        assets_add.setBackground(new java.awt.Color(255, 0, 0));
        assets_add.setText("AGREGAR");
        assets_add.setColorHover(new java.awt.Color(26, 46, 54));
        assets_add.setColorNormal(new java.awt.Color(255, 0, 0));
        assets_add.setColorPressed(new java.awt.Color(26, 46, 54));
        assets_add.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        assets_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assets_addActionPerformed(evt);
            }
        });
        AssetsPanel.add(assets_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, -1, 50));

        AssetsTable.setBackground(new java.awt.Color(255, 255, 255));
        AssetsTable.setForeground(new java.awt.Color(0, 0, 0));
        AssetsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Cuenta", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(AssetsTable);
        if (AssetsTable.getColumnModel().getColumnCount() > 0) {
            AssetsTable.getColumnModel().getColumn(0).setResizable(false);
            AssetsTable.getColumnModel().getColumn(1).setResizable(false);
            AssetsTable.getColumnModel().getColumn(2).setResizable(false);
        }

        AssetsPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 580, 280));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("CUENTA");
        AssetsPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 90, 20));

        worthLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        worthLabel.setForeground(new java.awt.Color(0, 0, 0));
        worthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        worthLabel.setText("$" + Double.toString(Asset.totalWorth())
        );
        AssetsPanel.add(worthLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 90, 30));

        name_txt.setBackground(new java.awt.Color(255, 255, 255));
        name_txt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        name_txt.setForeground(new java.awt.Color(0, 0, 0));
        name_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        name_txt.setOpaque(false);
        name_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_txtActionPerformed(evt);
            }
        });
        AssetsPanel.add(name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 170, 30));

        value_txt.setBackground(new java.awt.Color(255, 255, 255));
        value_txt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        value_txt.setForeground(new java.awt.Color(0, 0, 0));
        value_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        value_txt.setOpaque(false);
        value_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                value_txtActionPerformed(evt);
            }
        });
        AssetsPanel.add(value_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 90, 30));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Valor neto de activos:");
        AssetsPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 200, 30));

        account_txt.setBackground(new java.awt.Color(255, 255, 255));
        account_txt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        account_txt.setForeground(new java.awt.Color(0, 0, 0));
        account_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        account_txt.setOpaque(false);
        account_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                account_txtActionPerformed(evt);
            }
        });
        AssetsPanel.add(account_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 170, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("NOMBRE");
        AssetsPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 90, 20));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("VALOR");
        AssetsPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 90, 20));

        Container.add(AssetsPanel, "Assets");

        getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 640, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseReleased
        // Close button
        
        int confirmation = JOptionPane.showConfirmDialog(null, "¿Seguro desea salir?", null, JOptionPane.OK_CANCEL_OPTION);
        
        if(confirmation == JOptionPane.OK_OPTION){
            System.exit(0);
        }
        
    }//GEN-LAST:event_closeButtonMouseReleased

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        // Minimize
        setExtendedState(ICONIFIED);        
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
            if(!amount.getText().trim().equals("")){
                String concept = product.getSelectedItem().toString();
                double value = Stock.priceOf(concept);
                int quantity = Integer.parseInt(amount.getText().trim());
                Sell.register(concept, quantity, value);
                fillSells();
                amount.setText("");
            } else {
                JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
                amount.setText("");    
            }        
        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"No ingrese letras ni decimales en cantidad.");
                amount.setText("");
        } 
    }//GEN-LAST:event_addButtonActionPerformed

    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportButtonActionPerformed
        Sell.generateReport();
    }//GEN-LAST:event_reportButtonActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        if(!productName.getText().trim().equals("") && !productPrice.getText().trim().equals("")){
            try {
                String name = productName.getText().trim();
                double price = Double.parseDouble(productPrice.getText().trim());
                String pCategory = productCategory.getSelectedItem().toString();
                Stock.addProduct(name, price, pCategory);
                fillStock();
              
                productName.setText(""); productPrice.setText("");                 
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"No ingrese letras en el campo precio.");
                productName.setText(""); productPrice.setText(""); 
            }
        } else {
            JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
            productName.setText(""); productPrice.setText("");             
        }
    }//GEN-LAST:event_addProductActionPerformed

    private void productCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productCategoryActionPerformed

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameActionPerformed

    private void productPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPriceActionPerformed

    private void assets_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assets_addActionPerformed
        if(!name_txt.getText().trim().equals("") && !value_txt.getText().trim().equals("") && !account_txt.getText().trim().equals("")){
            try {
                String name = name_txt.getText().trim();
                double value = Double.parseDouble(value_txt.getText().trim());
                String account = account_txt.getText().trim();
                Asset.add(name,account, value);
                fillAssets();
                worthLabel.setText("$" + Double.toString(Asset.totalWorth()));
                name_txt.setText(""); value_txt.setText(""); account_txt.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,"No ingrese letras en el campo valor.");
                name_txt.setText(""); value_txt.setText(""); account_txt.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
            name_txt.setText(""); value_txt.setText(""); account_txt.setText("");
        }
    }//GEN-LAST:event_assets_addActionPerformed

    private void name_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_txtActionPerformed

    private void value_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_value_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_value_txtActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        MaincardLayout.show(Container, "Home");
        category.setSelectedIndex(0);
        product.setModel(new DefaultComboBoxModel(getProducts()));        
    }//GEN-LAST:event_homeButtonActionPerformed

    private void stockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockButtonActionPerformed
        // TODO add your handling code here:
        MaincardLayout.show(Container, "Stock");
    }//GEN-LAST:event_stockButtonActionPerformed

    private void assetsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assetsButtonActionPerformed
        // TODO add your handling code here:
        MaincardLayout.show(Container, "Assets");
    }//GEN-LAST:event_assetsButtonActionPerformed

    private void productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productActionPerformed

    private void account_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_account_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_account_txtActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        Sell.getTempSold();
        fillSells();
    }//GEN-LAST:event_sellButtonActionPerformed

    private void categoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categoryItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){

            if (category.getSelectedItem().toString().equals("Producto")) {
                DefaultComboBoxModel model = new DefaultComboBoxModel(getProducts());
                product.setModel(model);    
            } else {
                DefaultComboBoxModel model = new DefaultComboBoxModel(getServices());
                product.setModel(model);                    
            }
            
        }
    }//GEN-LAST:event_categoryItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssetsPanel;
    private javax.swing.JTable AssetsTable;
    private javax.swing.JPanel BarMenu;
    private javax.swing.JPanel Container;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel LogoZone;
    private javax.swing.JPanel MainMenu;
    private javax.swing.JPanel StockPanel;
    private javax.swing.JTable StockTable;
    private javax.swing.JTextField account_txt;
    private guis.RSButtonMetro addButton;
    private guis.RSButtonMetro addProduct;
    private javax.swing.JTextField amount;
    private guis.RSButtonMetro assetsButton;
    private guis.RSButtonMetro assets_add;
    private javax.swing.JLabel businessNameLabel;
    private javax.swing.JComboBox<String> category;
    private guis.RSButtonMetro closeButton;
    private guis.RSButtonMetro homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private guis.RSButtonMetro minimizeButton;
    private javax.swing.JTextField name_txt;
    private javax.swing.JComboBox<String> product;
    private javax.swing.JComboBox<String> productCategory;
    private javax.swing.JTextField productName;
    private javax.swing.JTextField productPrice;
    private guis.RSButtonMetro reportButton;
    private guis.RSButtonMetro sellButton;
    private javax.swing.JTable sellsTable;
    private guis.RSButtonMetro stockButton;
    private javax.swing.JTextField value_txt;
    private javax.swing.JLabel worthLabel;
    // End of variables declaration//GEN-END:variables

    public Vector getProducts(){
        Vector vector = new Vector();
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Category = 'Producto'");
            
            while(rs.next()){
                vector.add(rs.getString("Name"));
            }
            
            SQLoperation.close(rs, st, cn);
            
        } catch (SQLException e) {
            System.err.println("Error getting products: " + e);
        }
        
        return vector;
    }
    
    public Vector getServices(){
        Vector vector = new Vector();
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling WHERE Category = 'Servicio'");
            
            while(rs.next()){
                vector.add(rs.getString("Name"));
            }
            
            SQLoperation.close(rs, st, cn);
            
        } catch (SQLException e) {
            System.err.println("Error geting services: " + e);
        }
        
        return vector;
    }    

    public void fillAssets(){
        assetsModel.setRowCount(0);
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Assets");
            Object[] vector = new Object[3];
            while(rs.next()){
                for(int i = 1; i <= vector.length; i++){
                    vector[i - 1] = rs.getObject(i);
                }
               assetsModel.addRow(vector);
            }
            
            assetsModel.fireTableDataChanged();
            
            SQLoperation.close(rs, st, cn);
            
        } catch (SQLException e) {
            System.err.println("Error filling assets: " + e);
        }

    }
    
    public void fillStock(){
        stockModel.setRowCount(0);
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Selling");
            Object[] vector = new Object[3];
            while(rs.next()){
                for(int i = 1; i <= vector.length; i++){
                    vector[i - 1] = rs.getObject(i);
                }
               stockModel.addRow(vector);
            }
            
            stockModel.fireTableDataChanged();
            
            SQLoperation.close(rs, st, cn);
            
        } catch (SQLException e) {
            System.err.println("Error filling stock: " + e.getMessage());
        }

    }    

    public void fillSells(){
        sellsModel.setRowCount(0);
        
        try {
            Connection cn = Database.connect();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Concept,Amount,Value,Total FROM Temp");
            Object[] vector = new Object[4];
            while(rs.next()){
                for(int i = 1; i <= vector.length; i++){
                    vector[i - 1] = rs.getObject(i);
                }
               sellsModel.addRow(vector);
            }
            
           
            
            SQLoperation.close(rs, st, cn);
            
        } catch (SQLException e) {
            System.err.println("Error filling stock: " + e.getMessage());
        }

    }   

}
