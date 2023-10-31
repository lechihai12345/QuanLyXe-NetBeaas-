/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrame;

import baocaokinangnghe.LoaiXe;
import baocaokinangnghe.NhaSanXuat;
import baocaokinangnghe.Xe;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LCH
 */
public class JFquanlyxe extends javax.swing.JFrame {

    private static String username = "root";
    private static String password = "";
    private static String data = "jdbc:mysql://localhost:3306/qlxe";

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    int a, i;

    public void GetDataTable() throws SQLException {

        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("select * from loaixe order by MaLoaiXe, TenLoaiXe, HangXe");
            rs = pst.executeQuery();

            ResultSetMetaData stData = rs.getMetaData();

            a = stData.getColumnCount();

            DefaultTableModel RecordTable = (DefaultTableModel) tableloaixe.getModel();
            RecordTable.setRowCount(0);

            while (rs.next()) {
                Vector columnData = new Vector();
                for (i = 1; i <= a; i++) {
                    columnData.add(rs.getString("MaLoaiXe"));
                    columnData.add(rs.getString("TenLoaiXe"));
                    columnData.add(rs.getString("HangXe"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void GetDataTableNSX() throws SQLException {

        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("select * from nhasanxuat order by MaSanXuat, TenSanXuat, DiaChiSX");
            rs = pst.executeQuery();

            ResultSetMetaData stData = rs.getMetaData();

            a = stData.getColumnCount();

            DefaultTableModel RecordTable = (DefaultTableModel) Tablenhasanxuat.getModel();
            RecordTable.setRowCount(0);

            while (rs.next()) {
                Vector columnData = new Vector();
                for (i = 1; i <= a; i++) {
                    columnData.add(rs.getString("MaSanXuat"));
                    columnData.add(rs.getString("TenSanXuat"));
                    columnData.add(rs.getString("DiaChiSX"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void GetDataTableXE() throws SQLException {

        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("select * from xe order by MaXe, TenXe, MaLoaiXe,MauXe,MaSanXuat");
            rs = pst.executeQuery();

            ResultSetMetaData stData = rs.getMetaData();

            a = stData.getColumnCount();

            DefaultTableModel RecordTable = (DefaultTableModel) Tablexe.getModel();
            RecordTable.setRowCount(0);

            while (rs.next()) {
                Vector columnData = new Vector();
                for (i = 1; i <= a; i++) {
                    columnData.add(rs.getString("MaXe"));
                    columnData.add(rs.getString("TenXe"));
                    columnData.add(rs.getString("MaLoaiXe"));
                    columnData.add(rs.getString("MauXe"));
                     columnData.add(rs.getString("MaSanXuat"));
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void comboboxxe() throws SQLException {

        try {

            conn = DriverManager.getConnection(data, username, password);
            String Querry = "Select * From `loaixe`";

            Statement st = conn.createStatement();
            pst = conn.prepareStatement(Querry);
            rs = pst.executeQuery();
            while (rs.next()) {
                cbbmaloaixe.addItem(rs.getString("MaLoaiXe"));
            }
            cbbmaloaixe.setSelectedItem(null);
            Jmaloaixe.setText(null);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
                pst.close();
                rs.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    
    public void comboboxnsx() throws SQLException {

        try {

            conn = DriverManager.getConnection(data, username, password);
            String Querry = "Select * From `nhasanxuat`";

            Statement st = conn.createStatement();
            pst = conn.prepareStatement(Querry);
            rs = pst.executeQuery();
            while (rs.next()) {
                jcbmasanxuat.addItem(rs.getString("MaSanXuat"));
            }
            jcbmasanxuat.setSelectedItem(null);
            jmanhasanxuat.setText(null);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
                pst.close();
                rs.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    private void CapNhatSoLuong() {
        try {
            conn = DriverManager.getConnection(data, username, password);
            Statement stmt = conn.createStatement();

            // Tính tổng số sách
            ResultSet xeee = stmt.executeQuery("SELECT COUNT(*) FROM loaixe");
            if (xeee.next()) {
                int slloaixe = xeee.getInt(1);
                txtsl.setText("Tổng loại xe: " + slloaixe);
            }
            ResultSet nsx = stmt.executeQuery("SELECT COUNT(*) FROM nhasanxuat");
            if (nsx.next()) {
                int slnsx = nsx.getInt(1);
                tongnxs.setText("Tổng nha san xuat: " + slnsx);
            }
            ResultSet slxee = stmt.executeQuery("SELECT COUNT(*) FROM xe");
            if (slxee.next()) {
                int slxeeee = slxee.getInt(1);
                tongslxe.setText("Tổng số xe: " + slxeeee);
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
   

    /**
     * Creates new form JFquanlyxe
     */
    public JFquanlyxe() throws SQLException {
        initComponents();
        GetDataTable();
        GetDataTableNSX();
        comboboxxe();
        comboboxnsx();
        GetDataTableXE();
        CapNhatSoLuong();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableloaixe = new javax.swing.JTable();
        thoat = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nhaptenloaixe = new javax.swing.JTextField();
        nhaphangxe = new javax.swing.JTextField();
        Reset = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbtimkiem = new javax.swing.JTextField();
        btntim = new javax.swing.JButton();
        txtsl = new javax.swing.JTextField();
        nhapmaloaixe = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tablenhasanxuat = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nhaptensanxuat = new javax.swing.JTextField();
        nhapdiachi = new javax.swing.JTextField();
        btnthemsx = new javax.swing.JButton();
        btnsuasx = new javax.swing.JButton();
        btnthoatsx = new javax.swing.JButton();
        btnxoasx = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jtnhapsx = new javax.swing.JTextField();
        btntimsx = new javax.swing.JButton();
        btnresetsx = new javax.swing.JButton();
        tongnxs = new javax.swing.JTextField();
        nhapmasanxuat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tablexe = new javax.swing.JTable();
        cbbmaloaixe = new javax.swing.JComboBox<>();
        Jmaloaixe = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jttenxe = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtmauxe = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jcbmasanxuat = new javax.swing.JComboBox<>();
        btnthemxe = new javax.swing.JButton();
        btnsuaxe = new javax.swing.JButton();
        btnxoaxe = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnthoatxe = new javax.swing.JButton();
        jmanhasanxuat = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jttimxe = new javax.swing.JTextField();
        btntimxe = new javax.swing.JButton();
        tongslxe = new javax.swing.JTextField();
        jtmaxe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tableloaixe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Loại Xe", "Tên Loại Xe", "Hãng Xe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableloaixe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableloaixeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableloaixe);

        thoat.setText("Thoát chương trình");
        thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatActionPerformed(evt);
            }
        });

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên Loại Xe");

        jLabel3.setText("Hãng Xe");

        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        jLabel4.setText("Tìm Kiếm");

        lbtimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbtimkiemActionPerformed(evt);
            }
        });

        btntim.setText("Tìm");
        btntim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimActionPerformed(evt);
            }
        });

        txtsl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtslActionPerformed(evt);
            }
        });

        nhapmaloaixe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapmaloaixeActionPerformed(evt);
            }
        });

        jLabel10.setText("Mã Loại Xe");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnthem))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(232, 232, 232)
                                                .addComponent(jLabel4)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(20, 20, 20))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nhapmaloaixe, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                        .addGap(72, 72, 72)
                                        .addComponent(lbtimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addComponent(btntim))
                            .addComponent(nhaptenloaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nhaphangxe, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnxoa)))
                .addGap(18, 18, 18)
                .addComponent(btnsua)
                .addGap(18, 18, 18)
                .addComponent(Reset)
                .addGap(27, 27, 27)
                .addComponent(thoat))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btntim)
                            .addComponent(lbtimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nhapmaloaixe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nhaptenloaixe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nhaphangxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thoat)
                    .addComponent(Reset)
                    .addComponent(btnsua)
                    .addComponent(btnxoa)
                    .addComponent(btnthem))
                .addGap(21, 21, 21))
        );

        jTabbedPane1.addTab("LoaiXe", jPanel1);

        Tablenhasanxuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Nhà Sản Xuất", "Tên Nhà Sản Xuất", "Địa Chỉ"
            }
        ));
        Tablenhasanxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablenhasanxuatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tablenhasanxuat);

        jLabel6.setText("Tên Nhà Sản Xuất");

        jLabel7.setText("Địa Chỉ");

        btnthemsx.setText("Thêm");
        btnthemsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemsxActionPerformed(evt);
            }
        });

        btnsuasx.setText("Sửa");
        btnsuasx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuasxActionPerformed(evt);
            }
        });

        btnthoatsx.setText("Thoát");
        btnthoatsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatsxActionPerformed(evt);
            }
        });

        btnxoasx.setText("Xóa");
        btnxoasx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoasxActionPerformed(evt);
            }
        });

        jLabel8.setText("Tìm Kiếm");

        jtnhapsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtnhapsxActionPerformed(evt);
            }
        });

        btntimsx.setText("Tìm");
        btntimsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimsxActionPerformed(evt);
            }
        });

        btnresetsx.setText("Reset");
        btnresetsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetsxActionPerformed(evt);
            }
        });

        jLabel5.setText("Mã Sản Xuất");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tongnxs, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nhapmasanxuat)
                            .addComponent(nhaptensanxuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(nhapdiachi, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnthemsx)
                    .addComponent(btnxoasx))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsuasx)
                        .addGap(18, 18, 18)
                        .addComponent(btnthoatsx))
                    .addComponent(btnresetsx))
                .addGap(33, 33, 33))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jtnhapsx, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btntimsx))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(jLabel8)))
                .addContainerGap(323, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnthemsx)
                                .addComponent(btnsuasx)
                                .addComponent(btnthoatsx))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(tongnxs, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnxoasx)
                                .addComponent(btnresetsx)
                                .addComponent(nhapmasanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nhaptensanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nhapdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtnhapsx, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntimsx))))
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab("NhaSanXuat", jPanel2);

        Tablexe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Xe", "Tên Xe", "Mã Loại Xe", "Màu Xe", "Mã Nhà Sản Xuất"
            }
        ));
        jScrollPane3.setViewportView(Tablexe);

        cbbmaloaixe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbmaloaixeActionPerformed(evt);
            }
        });

        Jmaloaixe.setText("jLabel9");

        jLabel9.setText("Mã Loại Xe");

        jLabel11.setText("Tên Xe");

        jttenxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jttenxeActionPerformed(evt);
            }
        });

        jLabel12.setText("Màu Xe");

        jtmauxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtmauxeActionPerformed(evt);
            }
        });

        jLabel13.setText("Mã Nhà Sản Xuất");

        jcbmasanxuat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbmasanxuatItemStateChanged(evt);
            }
        });
        jcbmasanxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmasanxuatActionPerformed(evt);
            }
        });

        btnthemxe.setText("Thêm");
        btnthemxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemxeActionPerformed(evt);
            }
        });

        btnsuaxe.setText("Sửa");
        btnsuaxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaxeActionPerformed(evt);
            }
        });

        btnxoaxe.setText("Xóa");
        btnxoaxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaxeActionPerformed(evt);
            }
        });

        btnreset.setText("Reset");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        btnthoatxe.setText("Thoát");
        btnthoatxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthoatxeActionPerformed(evt);
            }
        });

        jmanhasanxuat.setText("jLabel14");

        jLabel14.setText("Tìm Kiếm");

        btntimxe.setText("Tìm");
        btntimxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimxeActionPerformed(evt);
            }
        });

        jtmaxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtmaxeActionPerformed(evt);
            }
        });

        jLabel1.setText("Tong");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtmaxe)
                            .addComponent(jttenxe, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtmauxe, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbmasanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jmanhasanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tongslxe, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbmaloaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(Jmaloaixe)
                                        .addGap(252, 252, 252)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnthoatxe)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnxoaxe)
                        .addGap(18, 18, 18)
                        .addComponent(btnreset))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnthemxe)
                        .addGap(18, 18, 18)
                        .addComponent(btnsuaxe)))
                .addGap(91, 91, 91))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(jttimxe, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btntimxe)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnthemxe)
                        .addComponent(btnsuaxe)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtmauxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtmaxe, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jttenxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaxe)
                    .addComponent(btnreset)
                    .addComponent(jLabel13)
                    .addComponent(jcbmasanxuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jmanhasanxuat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbmaloaixe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthoatxe))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jmaloaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jttimxe, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimxe)
                    .addComponent(tongslxe))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Xe", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatActionPerformed
        System.exit(0);//thoát hoàn toàn chương trình
    }//GEN-LAST:event_thoatActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed

        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("insert into loaixe(TenLoaiXe,HangXe) value (?, ?)");

            
            pst.setString(1, nhaptenloaixe.getText());  
            pst.setString(2, nhaphangxe.getText());

            pst.executeUpdate();
            GetDataTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnthemActionPerformed

    private void tableloaixeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableloaixeMouseClicked
        DefaultTableModel RecordTable = (DefaultTableModel) tableloaixe.getModel();
        int SelectedRow = tableloaixe.getSelectedRow();

        nhapmaloaixe.setText(RecordTable.getValueAt(SelectedRow, 0).toString());
        nhaptenloaixe.setText(RecordTable.getValueAt(SelectedRow, 1).toString());
        nhaphangxe.setText(RecordTable.getValueAt(SelectedRow, 2).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tableloaixeMouseClicked

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("update loaixe set TenLoaiXe=?, HangXe=? where (MaLoaiXe=?)");

            pst.setString(3, nhapmaloaixe.getText());
            pst.setString(1, nhaptenloaixe.getText());
            pst.setString(2, nhaphangxe.getText());

            pst.executeUpdate();
            GetDataTable();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnsuaActionPerformed

    
    private boolean kiemTraLoaiXe(int lx) {
        boolean loaixe = false;
        // Kết nối đến cơ sở dữ liệu và kiểm tra xem sách có được mượn hay không
        try (Connection connection = DriverManager.getConnection(data, username, password)) {
            String sql = "SELECT * FROM xe WHERE MaLoaiXe = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lx);
            ResultSet resultSet = statement.executeQuery();
            loaixe = resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loaixe;
    }
    
    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
       int maloaixe = Integer.parseInt(nhapmaloaixe.getText());
       if(kiemTraLoaiXe(maloaixe)){
           JOptionPane.showMessageDialog(null, "Không thể xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
           return;
       }
       try(Connection connection = DriverManager.getConnection(data, username, password))
       {
           String sql = "DELETE FROM loaixe WHERE MaLoaiXe = ?";
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, maloaixe);
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
             }
              else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
             }
               GetDataTable();
       }
       catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed

        nhapmaloaixe.setText("");
        nhaptenloaixe.setText("");
        nhaphangxe.setText("");
        try {
            GetDataTable();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(JFquanlyxe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ResetActionPerformed

    public ArrayList<LoaiXe> ListLoaiXe(String find) {
        ArrayList<LoaiXe> loaixelist = new ArrayList<LoaiXe>();

        PreparedStatement pst;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("SELECT * FROM `loaixe` WHERE CONCAT(`MaLoaiXe`, `TenLoaiXe`, `HangXe`) LIKE '%" + find + "%'");
            rs = pst.executeQuery();

            LoaiXe loaixe;

            while (rs.next()) {
                loaixe = new LoaiXe(
                        rs.getInt("MaLoaiXe"),
                        rs.getString("TenLoaiXe"),
                        rs.getString("HangXe")
                );
                loaixelist.add(loaixe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFquanlyxe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loaixelist;

    }

    public void findLoaiXe() {
        ArrayList<LoaiXe> users = ListLoaiXe(lbtimkiem.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"MaLoaiXe", "TenLoaiXe", "HangXe"});
        Object[] row = new Object[3];

        for (int i = 0; i < users.size(); i++) {
            row[0] = users.get(i).getMaLoaixe();
            row[1] = users.get(i).getTenLoaiXe();
            row[2] = users.get(i).getHangXe();
            model.addRow(row);
        }
        tableloaixe.setModel(model);

    }

    private void btntimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimActionPerformed
        // TODO add your handling code here:
        findLoaiXe();
    }//GEN-LAST:event_btntimActionPerformed

    private void TablenhasanxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablenhasanxuatMouseClicked
        // TODO add your handling code here:
        DefaultTableModel RecordTable = (DefaultTableModel) Tablenhasanxuat.getModel();
        int SelectedRow = Tablenhasanxuat.getSelectedRow();

        nhapmasanxuat.setText(RecordTable.getValueAt(SelectedRow, 0).toString());
        nhaptensanxuat.setText(RecordTable.getValueAt(SelectedRow, 1).toString());
        nhapdiachi.setText(RecordTable.getValueAt(SelectedRow, 2).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_TablenhasanxuatMouseClicked

    private void btnthemsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemsxActionPerformed
        // TODO add your handling code here:
        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("insert into nhasanxuat(TenSanXuat,DiaChiSX) value (?, ?)");

            pst.setString(1, nhaptensanxuat.getText());
            pst.setString(2, nhapdiachi.getText());

            pst.executeUpdate();
            GetDataTableNSX();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnthemsxActionPerformed

    private void btnsuasxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuasxActionPerformed
        // TODO add your handling code here:
        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("update nhasanxuat set TenSanXuat=?, DiaChiSX=? where (MaSanXuat=?)");

            pst.setString(3, nhapmasanxuat.getText());
            pst.setString(1, nhaptensanxuat.getText());
            pst.setString(2, nhapdiachi.getText());

            pst.executeUpdate();
            GetDataTableNSX();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnsuasxActionPerformed

    private void btnthoatsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatsxActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnthoatsxActionPerformed

    private void btnresetsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetsxActionPerformed
        // TODO add your handling code here:
        nhapmasanxuat.setText("");
        nhaptensanxuat.setText("");
        nhapdiachi.setText("");
        try {
            GetDataTableNSX();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(JFquanlyxe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnresetsxActionPerformed

    private void jtnhapsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtnhapsxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtnhapsxActionPerformed


    private void lbtimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbtimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbtimkiemActionPerformed

    public ArrayList<NhaSanXuat> ListNhaSX(String find) {
        ArrayList<NhaSanXuat> nsxlist = new ArrayList<NhaSanXuat>();

        PreparedStatement pst;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("SELECT * FROM `nhasanxuat` WHERE CONCAT(`MaSanXuat`, `TenSanXuat`, `DiaChiSX`) LIKE '%" + find + "%'");
            rs = pst.executeQuery();

            NhaSanXuat nhasanxuat;

            while (rs.next()) {
                nhasanxuat = new NhaSanXuat(
                        rs.getInt("MaSanXuat"),
                        rs.getString("TenSanXuat"),
                        rs.getString("DiaChiSX")
                );
                nsxlist.add(nhasanxuat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFquanlyxe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nsxlist;

    }

    public void findNSX() {
        ArrayList<NhaSanXuat> users = ListNhaSX(jtnhapsx.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"MaSanXuat", "TenSanXuat", "DiaChiSX"});
        Object[] row = new Object[3];

        for (int i = 0; i < users.size(); i++) {
            row[0] = users.get(i).getMaSanXuat();
            row[1] = users.get(i).getTenSanXuat();
            row[2] = users.get(i).getDiaChiSanXuat();
            model.addRow(row);
        }
        Tablenhasanxuat.setModel(model);

    }

    public ArrayList<Xe> ListXe(String find) {
        ArrayList<Xe> xelist = new ArrayList<Xe>();

        PreparedStatement pst;
        ResultSet rs;

        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("SELECT * FROM `xe` WHERE CONCAT(`MaXe`, `TenXe`,`MaLoaiXe`,`MauXe`,`MaSanXuat`) LIKE '%" + find + "%'");
            rs = pst.executeQuery();

            Xe xe;

            while (rs.next()) {
                xe = new Xe(
                        rs.getInt("MaXe"),
                        rs.getString("TenXe"),
                         rs.getString("MaLoaiXe"),
                        rs.getString("MauXe"),
                         rs.getString("MaSanXuat")
                        
                );
                xelist.add(xe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFquanlyxe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xelist;

    }

    public void findXE() {
        ArrayList<Xe> users = ListXe(jttimxe.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"MaXe", "TenXe","MaLoaiXe", "MauXe","MaSanXuat"});
        Object[] row = new Object[5];

        for (int i = 0; i < users.size(); i++) {
            row[0] = users.get(i).getMaXe();
            row[1] = users.get(i).getTenXe();
            row[2] = users.get(i).getLoaiXe();
            row[3] = users.get(i).getMauXe();
            row[4] = users.get(i).getNhaSanXuat();
            model.addRow(row);
        }
        Tablexe.setModel(model);

    }
    
    
    
    

    private void btntimsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimsxActionPerformed
        // TODO add your handling code here:
        findNSX();
    }//GEN-LAST:event_btntimsxActionPerformed

    private void jttenxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jttenxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jttenxeActionPerformed

    private void cbbmaloaixeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbmaloaixeActionPerformed
        // TODO add your handling code here:
        Jmaloaixe.setText((String) cbbmaloaixe.getSelectedItem());
    }//GEN-LAST:event_cbbmaloaixeActionPerformed

    private void jtmauxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtmauxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtmauxeActionPerformed

    private void btnthoatxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthoatxeActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnthoatxeActionPerformed

    private void btnthemxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemxeActionPerformed
        // TODO add your handling code here:
         try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("insert into xe(TenXe,MaLoaiXe,MauXe,MaSanXuat) value (?, ?, ?, ?)");

            pst.setString(1, jttenxe.getText());
            pst.setString(2, Jmaloaixe.getText());
            pst.setString(3, jtmauxe.getText());
            pst.setString(4, jmanhasanxuat.getText());

            pst.executeUpdate();
            GetDataTableXE();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnthemxeActionPerformed

    private void jcbmasanxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmasanxuatActionPerformed
        // TODO add your handling code here:
        jmanhasanxuat.setText((String) jcbmasanxuat.getSelectedItem());
    }//GEN-LAST:event_jcbmasanxuatActionPerformed

    private void jcbmasanxuatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbmasanxuatItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbmasanxuatItemStateChanged

    private void btnsuaxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaxeActionPerformed
        // TODO add your handling code here:
        try {
            conn = DriverManager.getConnection(data, username, password);
            pst = conn.prepareStatement("update xe set TenXe=?,MaLoaiXe=?, MauXe=?,MaSanXuat=? where (MaXe=?)");

           
            pst.setString(1, jttenxe.getText());
            pst.setString(2, Jmaloaixe.getText());
            pst.setString(3, jtmauxe.getText());
            pst.setString(4, jmanhasanxuat.getText());
            pst.setString(5, jtmaxe.getText());

            pst.executeUpdate();
            GetDataTableXE();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnsuaxeActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:
        jttenxe.setText("");
        Jmaloaixe.setText("");
        jtmauxe.setText("");
        jmanhasanxuat.setText("");
        jtmaxe.setText("");
        try {
            GetDataTableXE();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(JFquanlyxe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnresetActionPerformed

    private void btntimxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimxeActionPerformed
        // TODO add your handling code here:
        findXE();
    }//GEN-LAST:event_btntimxeActionPerformed

    
     private boolean kiemTraNhaSanXuat(int lx) {
        boolean sx = false;
        // Kết nối đến cơ sở dữ liệu và kiểm tra xem sách có được mượn hay không
        try (Connection connection = DriverManager.getConnection(data, username, password)) {
            String sql = "SELECT * FROM xe WHERE MaSanXuat = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lx);
            ResultSet resultSet = statement.executeQuery();
            sx = resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sx;
    }
   
    
    
    private void btnxoasxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoasxActionPerformed
        // TODO add your handling code here:
         int masx = Integer.parseInt(nhapmasanxuat.getText());
       if(kiemTraNhaSanXuat(masx)){
           JOptionPane.showMessageDialog(null, "Không thể xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
           return;
       }
       try(Connection connection = DriverManager.getConnection(data, username, password))
       {
           String sql = "DELETE FROM nhasanxuat WHERE MaSanXuat = ?";
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, masx);
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
             }
              else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
             }
               GetDataTableNSX();
       }
       catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnxoasxActionPerformed


     private boolean kiemTraxe(int lx) {
        boolean xee = false;
        // Kết nối đến cơ sở dữ liệu và kiểm tra xem sách có được mượn hay không
        try (Connection connection = DriverManager.getConnection(data, username, password)) {
            String sql = "SELECT * FROM xe WHERE Maxe = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, lx);
            ResultSet resultSet = statement.executeQuery();
            xee = resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return xee;
    }
    
    private void btnxoaxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaxeActionPerformed
        // TODO add your handling code here:
         int masx = Integer.parseInt(jtmaxe.getText());
       if(kiemTraNhaSanXuat(masx)){
           JOptionPane.showMessageDialog(null, "Không thể xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
           return;
       }
       try(Connection connection = DriverManager.getConnection(data, username, password))
       {
           String sql = "DELETE FROM xe WHERE Maxe = ?";
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, masx);
             int rowsAffected = statement.executeUpdate();
             if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
             }
              else {
                JOptionPane.showMessageDialog(null, "Xóa thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
             }
               GetDataTableXE();
       }
       catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnxoaxeActionPerformed

    private void txtslActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtslActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtslActionPerformed

    private void jtmaxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtmaxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtmaxeActionPerformed

    private void nhapmaloaixeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapmaloaixeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nhapmaloaixeActionPerformed

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
            java.util.logging.Logger.getLogger(JFquanlyxe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFquanlyxe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFquanlyxe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFquanlyxe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFquanlyxe().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFquanlyxe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jmaloaixe;
    private javax.swing.JButton Reset;
    private javax.swing.JTable Tablenhasanxuat;
    private javax.swing.JTable Tablexe;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnresetsx;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnsuasx;
    private javax.swing.JButton btnsuaxe;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnthemsx;
    private javax.swing.JButton btnthemxe;
    private javax.swing.JButton btnthoatsx;
    private javax.swing.JButton btnthoatxe;
    private javax.swing.JButton btntim;
    private javax.swing.JButton btntimsx;
    private javax.swing.JButton btntimxe;
    private javax.swing.JButton btnxoa;
    private javax.swing.JButton btnxoasx;
    private javax.swing.JButton btnxoaxe;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbbmaloaixe;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbmasanxuat;
    private javax.swing.JLabel jmanhasanxuat;
    private javax.swing.JTextField jtmauxe;
    private javax.swing.JTextField jtmaxe;
    private javax.swing.JTextField jtnhapsx;
    private javax.swing.JTextField jttenxe;
    private javax.swing.JTextField jttimxe;
    private javax.swing.JTextField lbtimkiem;
    private javax.swing.JTextField nhapdiachi;
    private javax.swing.JTextField nhaphangxe;
    private javax.swing.JTextField nhapmaloaixe;
    private javax.swing.JTextField nhapmasanxuat;
    private javax.swing.JTextField nhaptenloaixe;
    private javax.swing.JTextField nhaptensanxuat;
    private javax.swing.JTable tableloaixe;
    private javax.swing.JButton thoat;
    private javax.swing.JTextField tongnxs;
    private javax.swing.JTextField tongslxe;
    private javax.swing.JTextField txtsl;
    // End of variables declaration//GEN-END:variables
}
