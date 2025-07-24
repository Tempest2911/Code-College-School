/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.CRUD;
import duantotnghiep.MODEL.GuiMail;
import duantotnghiep.MODEL.Mail;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author MINHDUC
 */
public class QuenMatKhau extends javax.swing.JFrame {

    /**
     * Creates new form QuenMatKhau
     */
    private javax.swing.Timer bamgio;
    private int thoigiandemnguoc = 60;
    private JLabel lblMa, lblMk, lblNhapMa, lblDangNhap, lblTen;
    private JTextField txtTen, txtEmail, txtNhap;
    private JButton btnGui, btnTiep,btnHuy;

    public QuenMatKhau() {
        
        initComponents();
        setTitle("Tạo mật khẩu mới");
        setSize(1000, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#311b0c"));

        lblDangNhap = new JLabel("TẠO MẬT KHẨU MỚI");
        lblDangNhap.setSize(400, 50);
        lblDangNhap.setLocation(550, 40);
        lblDangNhap.setForeground(Color.white);
        lblDangNhap.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(lblDangNhap);

        lblTen = new JLabel("Tên Đăng Nhập:");
        lblTen.setSize(200, 50);
        lblTen.setLocation(450, 75);
        lblTen.setForeground(Color.white);
        lblTen.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblTen);

        txtTen = new JTextField();
        txtTen.setSize(500, 40);
        txtTen.setLocation(450, 125);
        txtTen.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(txtTen);

        lblMk = new JLabel("Nhập Email:");
        lblMk.setSize(200, 100);
        lblMk.setLocation(450, 158);
        lblMk.setForeground(Color.white);
        lblMk.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblMk);

        txtEmail = new JTextField();
        txtEmail.setSize(500, 40);
        txtEmail.setLocation(450, 230);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(txtEmail);

        lblNhapMa = new JLabel("Nhập Mã:");
        lblNhapMa.setSize(200, 100);
        lblNhapMa.setLocation(450, 260);
        lblNhapMa.setForeground(Color.white);
        lblNhapMa.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lblNhapMa);

        txtNhap = new JTextField();
        txtNhap.setSize(350, 40);
        txtNhap.setLocation(450, 330);
        txtNhap.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(txtNhap);

        btnGui = new JButton("Gửi Mã");
        btnGui.setSize(125, 40);
        btnGui.setLocation(820, 330);
        btnGui.setBackground(Color.white);
        btnGui.setFont(new Font("Arial", Font.BOLD, 13));
        this.add(btnGui);

        btnTiep = new JButton("Tiếp tục");
        btnTiep.setSize(240, 50);
        btnTiep.setLocation(710, 390);
        this.add(btnTiep);
        
        btnHuy = new JButton("Huỷ");
        btnHuy.setSize(240, 50);
        btnHuy.setLocation(450, 390);
        this.add(btnHuy);
        
        btnTiep.setFont(new Font("Arial", Font.BOLD, 13));

        btnTiep.setBackground(Color.decode("#FFEBCD"));
        btnTiep.setForeground(Color.decode("#4D2913"));
        btnTiep.setFocusPainted(false);

        btnTiep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTiep.setBackground(Color.decode("#F5DEB3")); // hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTiep.setBackground(Color.decode("#FFEBCD")); // default
            }
        });
        
        bamgio = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thoigiandemnguoc--;
                if (thoigiandemnguoc > 0) {
                    btnGui.setText("Gửi lại sau (" + thoigiandemnguoc + "s)");
                } else {
                    bamgio.stop();
                    btnGui.setEnabled(true);
                    btnGui.setText("Gửi mã");
                    thoigiandemnguoc = 60; // Reset thời gian
                }
            }
        });
        
        btnHuy.setFont(new Font("Arial", Font.BOLD, 13));

        btnHuy.setBackground(Color.decode("#FFEBCD"));
        btnHuy.setForeground(Color.decode("#4D2913"));
        btnHuy.setFocusPainted(false);

        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHuy.setBackground(Color.decode("#F5DEB3")); // hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHuy.setBackground(Color.decode("#FFEBCD")); // default
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login lg =new Login();
                lg.setVisible(true);
            }
        });
        btnGui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String ten = txtTen.getText();

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập email");
                    return;
                }
                if (!checkTaiKhoan(ten, email)) {
                    JOptionPane.showMessageDialog(rootPane, "Sai tên hoặc email tài khoản");
                    return;
                }
                if (GuiMail.sendOTP(email)) {
                    JOptionPane.showMessageDialog(rootPane, "Đã gửi mã OTP qua email");
                    btnGui.setEnabled(false); // Vô hiệu hóa nút
                    bamgio.start();         // Bắt đầu đếm ngược

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Gửi mã thất bại");
                }
            }
        });

        btnTiep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtTen.getText();
                String mail = txtEmail.getText();
                String ma = txtNhap.getText();
                if (name.isEmpty() || mail.isEmpty() || ma.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đầy đủ thông tin");
                } else {
                    if (checkTaiKhoan(name, mail)) {
                        if (ma.equals(GuiMail.getOTPCode())) { // So sánh mã OTP
                            Mail.setEmail(mail);
                            dispose();
                            TaoMatKhau tmk = new TaoMatKhau();
                            tmk.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Mã OTP không đúng");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Tài khoản hoặc email không tồn tại");
                    }
                }
            }
        });

    }

    public static boolean checkTaiKhoan(String name, String email) {
        String sql = "select * from NhanVien where NameAC=? and Email=?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, email);
            ResultSet rs = stm.executeQuery();
            return rs.next();
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/logo.PNG"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
