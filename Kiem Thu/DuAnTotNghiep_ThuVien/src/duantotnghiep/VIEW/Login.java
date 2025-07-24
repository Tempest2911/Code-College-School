/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.CRUD;
import duantotnghiep.DAO.DaoNhanVien;
import duantotnghiep.MODEL.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author MINHDUC
 */
public class Login extends javax.swing.JFrame {

    static String selectQuery = "SELECT * FROM NhanVien WHERE NameAc=? and Pass=?";
    /**
     * Creates new form Login
     */
    public static int currentUserId = -1;

    public Login() {
        batdau bd = new batdau(this, rootPaneCheckingEnabled);
        bd.setVisible(true);
        initComponents();
        setTitle("Hệ thống quản lý thư viện");
        setSize(930, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#311b0c"));

        JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP");
        lblDangNhap.setSize(300, 50);
        lblDangNhap.setLocation(540, 40);
        lblDangNhap.setFont(new Font("Arial", Font.BOLD, 35));
        lblDangNhap.setForeground(Color.decode("#FFEBCD"));
        this.add(lblDangNhap);

        JLabel lblTen = new JLabel("Tên Đăng Nhập:");
        lblTen.setSize(200, 50);
        lblTen.setLocation(400, 80);
        lblTen.setForeground(Color.decode("#FFF5EE"));
        lblTen.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblTen);

        JTextField txtTen = new JTextField();
        txtTen.setSize(460, 40);
        txtTen.setLocation(400, 130);
        this.add(txtTen);

        JLabel lblMk = new JLabel("Mật Khẩu:");
        lblMk.setSize(200, 100);
        lblMk.setForeground(Color.decode("#FFF5EE"));
        lblMk.setLocation(400, 150);
        lblMk.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblMk);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setSize(460, 40);
        txtPass.setLocation(400, 230);
        this.add(txtPass);

        JButton btnDangNhap = new JButton("Đăng Nhập");
        getRootPane().setDefaultButton(btnDangNhap);
        btnDangNhap.setBounds(400, 300, 200, 50);
        btnDangNhap.setFont(new Font("Arial", Font.BOLD, 13));
        btnDangNhap.setBackground(Color.decode("#FFEBCD"));
        btnDangNhap.setForeground(Color.decode("#4D2913"));
        btnDangNhap.setBorder(BorderFactory.createLineBorder(Color.decode("#5A3013")));
        btnDangNhap.setFocusPainted(false);

        btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangNhap.setBackground(Color.decode("#F5DEB3")); // hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangNhap.setBackground(Color.decode("#FFEBCD")); // default
            }
        });
        this.add(btnDangNhap);

// Quên Mật Khẩu – giống hệt
        JButton btnQuen = new JButton("Quên Mật Khẩu");
        btnQuen.setBounds(650, 300, 200, 50);
        btnQuen.setFont(new Font("Arial", Font.BOLD, 13));
        btnQuen.setBackground(Color.decode("#FFEBCD"));
        btnQuen.setForeground(Color.decode("#4D2913"));
        btnQuen.setBorder(BorderFactory.createLineBorder(Color.decode("#5A3013")));
        btnQuen.setFocusPainted(false);

        btnQuen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuen.setBackground(Color.decode("#F5DEB3"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuen.setBackground(Color.decode("#FFEBCD"));
            }
        });
        this.add(btnQuen);

        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ten = txtTen.getText();
                char[] passwordChars = txtPass.getPassword();
                String pass = new String(passwordChars);
                if (ten.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Tên người dùng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (pass.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String regex = "^.*[A-Za-z].*$";
                if (checkLogin(ten, pass)) {
                    String role = getRole(ten, pass);
                    if (role.equalsIgnoreCase("Thủ thư")) {
                        NhanVien nhanVien = getNhanVien(ten, pass);
                        currentUserId = nhanVien.getNhanVienID();
                        Main main = new Main(nhanVien);
                        main.setVisible(true);
                        dispose();
                        JOptionPane.showMessageDialog(rootPane, "Chào mừng đến với hệ thống Thoth Library!");
                    } else {
                        if (role.equalsIgnoreCase("Nhân viên")) {
                            NhanVien nhanVien = getNhanVien(ten, pass);
                            currentUserId = nhanVien.getNhanVienID();
                            TrangChuNhanVien tc = new TrangChuNhanVien(nhanVien);
                            dispose();
                            tc.setVisible(true);
                            JOptionPane.showMessageDialog(rootPane, "Hello nhân viên");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Sai Tài Khoản Hoặc Mật Khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        btnQuen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                QuenMatKhau qmk = new QuenMatKhau();
                qmk.setVisible(true);
            }
        });
    }

    public boolean checkLogin(String Username, String Passwords) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(selectQuery)) {
            stm.setString(1, Username);
            stm.setString(2, Passwords);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getRole(String user, String pass) {
        String role = null;
        try {
            Connection con = DriverManager.getConnection(CRUD.connectionUrl);
            String sql = "SELECT Role "
                    + "FROM NhanVien  "
                    + "WHERE NameAc = ? AND Pass = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    public NhanVien getNhanVien(String user, String pass) {
        NhanVien nhanVien = null;
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "SELECT * FROM NhanVien WHERE NameAc = ? AND Pass = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int nhanVienId = rs.getInt("NhanVienID");
                    String nameAc = rs.getString("NameAc");
                    String password = rs.getString("Pass");
                    String role = rs.getString("Role");
                    String tenNhanVien = rs.getString("TenNhanVien");
                    String cccd = rs.getString("CCCD");
                    int namSinh = rs.getInt("NamSinh");
                    String sdt = rs.getString("SDT");
                    String email = rs.getString("Email");
                    String gioiTinh = rs.getString("GioiTinh");
                    String avatar = rs.getString("Avatar");

                    nhanVien = new NhanVien(nhanVienId, nameAc, password, role, tenNhanVien, cccd, namSinh, sdt, email, gioiTinh, avatar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanVien;
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/logo.PNG"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
