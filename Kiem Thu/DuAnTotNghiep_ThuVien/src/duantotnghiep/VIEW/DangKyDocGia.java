/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.CRUD;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author thuyz
 */
public class DangKyDocGia extends javax.swing.JFrame {
    
    private JTextField txtHoTen;
    private JTextField txtSDT;
    private JTextField txtCCCD;
    private JLabel lblHoTen;
    private JLabel lblSDT;
    private JLabel lblCCCD;
    private JButton btnDangKy;
    private JButton btnHuy;
    
    private String selectedFilePath = null;
    
    public DangKyDocGia() {
        getContentPane().setBackground(Color.decode("#F5ECE3"));
        giaoDien();
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void giaoDien() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(null);
        
        JLabel lblTieuDe = new JLabel("ĐĂNG KÝ ĐỘC GIẢ");
        lblTieuDe.setBounds(210, 20, 400, 50);
        lblTieuDe.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 26));
        lblTieuDe.setForeground(Color.decode("#5a2e0b"));
        
        lblHoTen = new JLabel("Họ và tên");
        txtHoTen = new JTextField();
        
        lblSDT = new JLabel("SĐT");
        txtSDT = new JTextField();
        
        lblCCCD = new JLabel("CCCD");
        txtCCCD = new JTextField();
        
        btnDangKy = new JButton("Đăng ký");
        btnDangKy.setForeground(Color.white);
        
        btnHuy = new JButton("Hủy");
        btnHuy.setForeground(Color.white);
        
        btnDangKy.setBounds(200, 250, 150, 40);
        btnDangKy.setBackground(Color.decode("#5a3013"));
        
        btnHuy.setBounds(370, 250, 150, 40);
        btnHuy.setBackground(Color.decode("#5a3013"));
        
        Color brownColor = Color.decode("#5a3013");
        
        lblHoTen.setBounds(120, 70, 100, 50);
        lblHoTen.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        lblHoTen.setForeground(Color.decode("#301d0e"));
        
        lblSDT.setBounds(120, 120, 100, 50);
        lblSDT.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        lblSDT.setForeground(Color.decode("#301d0e"));
        
        lblCCCD.setBounds(120, 170, 100, 50);
        lblCCCD.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        lblCCCD.setForeground(Color.decode("#301d0e"));
        
        txtHoTen.setBounds(210, 80, 320, 35);
        txtSDT.setBounds(210, 130, 320, 35);
        txtCCCD.setBounds(210, 180, 320, 35);
        
        btnDangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDangKyActionPerformed(e);
            }
        });
        
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        add(lblTieuDe);
        add(lblHoTen);
        add(txtHoTen);
        add(lblSDT);
        add(txtSDT);
        add(lblCCCD);
        add(txtCCCD);
        add(btnDangKy);
        add(btnHuy);
        
    }
    
    private void Dangky() {
        try {
            Connection conn = DriverManager.getConnection(CRUD.connectionUrl);
            String sql = "INSERT INTO DocGia (HoTen, SDT, CCCD, PointKhachHang, TrangThai, Rank) values (?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, txtHoTen.getText());
            stm.setString(2, txtSDT.getText());
            stm.setString(3, txtCCCD.getText());
            stm.setInt(4, 0);
            stm.setString(5, "Hoạt động");
            stm.setString(6, "");
            int rs = stm.executeUpdate();
            if (rs > 0) {
                JOptionPane.showMessageDialog(rootPane, "Đăng ký thành công");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Đăng ký thất bại");
            }
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean KiemTraTrungCCCD(String cccd) {
        String sql = "SELECT CCCD FROM DocGia WHERE CCCD = ?";
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = conn.prepareStatement(sql)) {
            
            stm.setString(1, cccd);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next(); // Trả về true nếu CCCD đã tồn tại
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi kiểm tra CCCD: " + e.getMessage());
            return false;
        }
    }
    
    private boolean KiemTraTrungSDT(String sdt) {
        String sql = "SELECT SDT FROM DocGia WHERE SDT = ?";
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = conn.prepareStatement(sql)) {
            
            stm.setString(1, sdt);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next(); // Trả về true nếu CCCD đã tồn tại
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi kiểm tra SDT: " + e.getMessage());
            return false;
        }
    }

// 2. Hàm xử lý đăng ký với kiểm tra dữ liệu đầu vào
    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {
        String hoTen = txtHoTen.getText().trim();
        String sdt = txtSDT.getText().trim();
        String cccd = txtCCCD.getText().trim();
        
        int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đăng ký không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }
        
        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập Họ tên");
            return;
        }
        if (!hoTen.matches("^[a-zA-ZÀ-ỹ\\s]+$")) {
            JOptionPane.showMessageDialog(rootPane, "Họ tên chỉ được chứa chữ cái và dấu cách");
            return;
        }
        
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập Số điện thoại");
            return;
        }
        if (!sdt.matches("^0\\d{9,10}$")) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ (10-11 số, bắt đầu bằng 0)");
            return;
        }
        
        if (cccd.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập CCCD");
            return;
        }
        if (!cccd.matches("^\\d{12}$")) {
            JOptionPane.showMessageDialog(rootPane, "CCCD phải là 12 chữ số");
            return;
        }
        
        if (KiemTraTrungCCCD(cccd)) {
            JOptionPane.showMessageDialog(rootPane, "CCCD đã tồn tại trong hệ thống!");
            return;
        }
        if (KiemTraTrungSDT(sdt)) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại đã tồn tại trong hệ thống!");
            return;
        }

        // Gọi hàm đăng ký nếu tất cả đều hợp lệ
        Dangky();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DangKyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKyDocGia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKyDocGia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
