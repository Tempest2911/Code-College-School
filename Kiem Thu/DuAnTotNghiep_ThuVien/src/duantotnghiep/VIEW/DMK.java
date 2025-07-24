/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.CRUD;
import duantotnghiep.MODEL.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.ResultSet;
import javax.swing.JPasswordField;

/**
 *
 * @author MINHDUC
 */
public class DMK extends javax.swing.JFrame {

    /**
     * Creates new form DMK
     */
    private NhanVien nhanVien;
    private QLThongTin qLThongTin;
    public DMK(NhanVien nhanVien,QLThongTin qLThongTin) {
        this.nhanVien = nhanVien;
        this.qLThongTin = qLThongTin;
                getContentPane().setBackground(Color.decode("#f9f6e8"));

        initComponents();
        FOrm();
    }

    public void FOrm() {
        setTitle("Tạo mật khẩu mới");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblDangNhap = new JLabel("Tạo Mật Khẩu Mới");
        lblDangNhap.setSize(250, 50);
        lblDangNhap.setLocation(600, 40);
        lblDangNhap.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(lblDangNhap);

        JLabel lblID = new JLabel("ID Nhân Viên:");
        lblID.setSize(230, 50);
        lblID.setLocation(400, 100);
        lblID.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblID);

        JTextField txtID = new JTextField();
        txtID.setSize(300, 40);
        txtID.setLocation(630, 105);
        txtID.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(txtID);
        txtID.setText(String.valueOf(Login.currentUserId));

        JLabel lblOldPass = new JLabel("Nhập mật khẩu hiện tại:");
        lblOldPass.setSize(230, 50);
        lblOldPass.setLocation(400, 150);
        lblOldPass.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblOldPass);

        JPasswordField txtOldPass = new JPasswordField();
        txtOldPass.setSize(300, 40);
        txtOldPass.setLocation(630, 155);
        txtOldPass.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(txtOldPass);

        JLabel lblTen = new JLabel("Nhập mật khẩu mới:");
        lblTen.setSize(230, 50);
        lblTen.setLocation(400, 200);
        lblTen.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblTen);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setSize(300, 40);
        txtPass.setLocation(630, 205);
        txtPass.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(txtPass);

        JLabel lblXN = new JLabel("Xác nhận mật khẩu mới:");
        lblXN.setSize(230, 50);
        lblXN.setLocation(400, 250);
        lblXN.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblXN);

        JPasswordField txtXN = new JPasswordField();
        txtXN.setSize(300, 40);
        txtXN.setLocation(630, 255);
        txtXN.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(txtXN);
        
        JButton btnHuy = new JButton("Huỷ");
        btnHuy.setSize(175, 50);
        btnHuy.setLocation(500, 320);
        this.add(btnHuy);
        
        JButton btnDo = new JButton("Xác nhận đổi mật khẩu");
        btnDo.setSize(175, 50);
        btnDo.setLocation(700, 320);
        this.add(btnDo);

        btnDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idNV = Integer.parseInt(txtID.getText().trim());
                String matKhauHienTai = String.valueOf(txtOldPass.getPassword()).trim();
                String matKhauMoi = String.valueOf(txtPass.getPassword()).trim();
                String xacNhanMK = String.valueOf(txtXN.getPassword()).trim();

                if (!kiemTraMatKhauHienTai(idNV, matKhauHienTai)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu hiện tại không đúng!");
                    return;
                }

                if (matKhauMoi.equals(matKhauHienTai)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu mới không được trùng với mật khẩu hiện tại!");
                    return;
                }

                if (!matKhauMoi.equals(xacNhanMK)) {
                    JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không trùng khớp!");
                    return;
                }

                if (DoiPass(matKhauMoi, idNV)) {
                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
                    nhanVien.setPass(txtPass.getText());
                    qLThongTin.updateForm(nhanVien);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
                }
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static boolean DoiPass(String pass, int idNV) {
        String sql = "UPDATE NhanVien SET [Pass] = ? WHERE NhanVienID=?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, pass);
            stm.setInt(2, idNV);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean kiemTraMatKhauHienTai(int idNV, String matKhau) {
        String sql = "SELECT * FROM NhanVien WHERE NhanVienID = ? AND [Pass] = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, idNV);
            stm.setString(2, matKhau);
            ResultSet rs = stm.executeQuery();
            return rs.next(); // Nếu có kết quả nghĩa là đúng mật khẩu
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/logotrang.PNG"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(DMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DMK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        NhanVien nhanVien = new NhanVien();
        QLThongTin qLThongTin = new QLThongTin(nhanVien);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DMK(nhanVien,qLThongTin).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
