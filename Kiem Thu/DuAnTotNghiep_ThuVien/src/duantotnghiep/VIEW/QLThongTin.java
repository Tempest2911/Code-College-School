/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoNhanVien;
import duantotnghiep.MODEL.NhanVien;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author MINHDUC
 */
public class QLThongTin extends javax.swing.JFrame {

    public String currentUserId;
    public JTextField txtId, txtNameAC, txtRoll, txtTen, txtCCCD, txtNamSinh, txtSDT, txtEmail;
    public JPasswordField txtPass;
    public JRadioButton rdoNam, rdoNu;
    public JButton btnAvatar, btnChoose;
    public JButton btnSua, btnDMK, btnXoa, btnXN;

    /**
     * Creates new form QLThongTin
     */
    public QLThongTin(NhanVien nhanVien) {
        initComponents();
        txtId = new JTextField();
        txtNameAC = new JTextField();
        txtRoll = new JTextField();
        txtTen = new JTextField();
        txtCCCD = new JTextField();
        txtNamSinh = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtPass = new JPasswordField();
        Form(nhanVien);
    }

    public void Form(NhanVien nhanVien) {
        setTitle("Hệ thống quản lý nhân viên");

        // Lấy kích thước màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);  // Kích thước form đầy màn hình
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Tối đa hóa form
        getContentPane().setBackground(Color.decode("#332012"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Sử dụng layout null để tùy chỉnh vị trí các thành phần

        // Title
        JLabel lblTitle = new JLabel("THÔNG TIN TÀI KHOẢN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitle.setBounds(0, 20, screenSize.width, 50);
        lblTitle.setForeground(Color.decode("#f9e6cd"));
        add(lblTitle);

        btnAvatar = new JButton("");
        btnAvatar.setBounds(900, 100, 300, 420);  // Đặt avatar cạnh trường đầu tiên
        add(btnAvatar);

        btnChoose = new JButton("Chọn ảnh");
        btnChoose.setBounds(950, 550, 200, 30);  // Nút "Chọn ảnh" nằm ngay dưới avatar
        add(btnChoose);

        // Các trường nhập liệu
        JLabel lblId = new JLabel("Nhân viên ID");
        lblId.setBounds(400, 100, 150, 30);
        lblId.setFont(new Font("Arial", Font.BOLD, 15));
        lblId.setForeground(Color.decode("#ffdbac"));
        add(lblId);

        txtId.setBounds(600, 100, 250, 30);
        add(txtId);
        txtId.setEnabled(false);

        JLabel lblNameAC = new JLabel("Tên tài khoản");
        lblNameAC.setFont(new Font("Arial", Font.BOLD, 15));
        lblNameAC.setForeground(Color.decode("#ffdbac"));
        lblNameAC.setBounds(400, 150, 150, 30);
        add(lblNameAC);

        txtNameAC.setBounds(600, 150, 250, 30);
        add(txtNameAC);
        txtNameAC.setEnabled(false);

        JLabel lblPass = new JLabel("Mật khẩu");
        lblPass.setBounds(400, 200, 150, 30);
        lblPass.setFont(new Font("Arial", Font.BOLD, 15));
        lblPass.setForeground(Color.decode("#ffdbac"));
        add(lblPass);

        txtPass.setBounds(600, 200, 250, 30);
        add(txtPass);
        txtPass.setEnabled(false);

        JLabel lblRole = new JLabel("Role");
        lblRole.setFont(new Font("Arial", Font.BOLD, 15));
        lblRole.setForeground(Color.decode("#ffdbac"));
        lblRole.setBounds(400, 250, 150, 30);
        add(lblRole);

        txtRoll.setBounds(600, 250, 250, 30);
        add(txtRoll);
        txtRoll.setEnabled(false);

        JLabel lblTen = new JLabel("Tên Nhân Viên");
        lblTen.setBounds(400, 300, 150, 30);
        lblTen.setFont(new Font("Arial", Font.BOLD, 15));
        lblTen.setForeground(Color.decode("#ffdbac"));
        add(lblTen);

        txtTen.setBounds(600, 300, 250, 30);
        add(txtTen);
        txtTen.setEnabled(false);

        JLabel lblCCCD = new JLabel("CCCD");
        lblCCCD.setBounds(400, 350, 150, 30);
        lblCCCD.setFont(new Font("Arial", Font.BOLD, 15));
        lblCCCD.setForeground(Color.decode("#ffdbac"));
        add(lblCCCD);

        txtCCCD.setBounds(600, 350, 250, 30);
        add(txtCCCD);
        txtCCCD.setEnabled(false);

        JLabel lblNamSinh = new JLabel("Năm Sinh");
        lblNamSinh.setBounds(400, 400, 150, 30);
        lblNamSinh.setFont(new Font("Arial", Font.BOLD, 15));
        lblNamSinh.setForeground(Color.decode("#ffdbac"));
        add(lblNamSinh);

        txtNamSinh.setBounds(600, 400, 250, 30);
        add(txtNamSinh);
        txtNamSinh.setEnabled(false);

        JLabel lblSDT = new JLabel("SĐT");
        lblSDT.setBounds(400, 450, 150, 30);
        lblSDT.setFont(new Font("Arial", Font.BOLD, 15));
        lblSDT.setForeground(Color.decode("#ffdbac"));
        add(lblSDT);

        txtSDT.setBounds(600, 450, 250, 30);
        add(txtSDT);
        txtSDT.setEnabled(false);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(400, 500, 150, 30);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        lblEmail.setForeground(Color.decode("#ffdbac"));
        add(lblEmail);

        txtEmail.setBounds(600, 500, 250, 30);
        add(txtEmail);
        txtEmail.setEnabled(false);

        JLabel lblGT = new JLabel("Giới Tính");
        lblGT.setBounds(400, 550, 150, 30);
        lblGT.setFont(new Font("Arial", Font.BOLD, 15));
        lblGT.setForeground(Color.decode("#ffdbac"));
        add(lblGT);
        rdoNam = new JRadioButton("Nam");
        rdoNam.setSize(200, 30);
        rdoNam.setLocation(600, 550);
        rdoNam.setFont(new Font("Arial", Font.BOLD, 15));
        rdoNam.setForeground(Color.decode("#ffdbac"));
        add(rdoNam);
        rdoNu = new JRadioButton("Nữ");
        rdoNu.setSize(200, 30);
        rdoNu.setLocation(800, 550);
        rdoNu.setFont(new Font("Arial", Font.BOLD, 15));
        rdoNu.setForeground(Color.decode("#ffdbac"));
        add(rdoNu);
        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(rdoNam);
        groupGioiTinh.add(rdoNu);
        rdoNam.setEnabled(false);
        rdoNu.setEnabled(false);

        // Các nút chức năng
        JButton btnSua = new JButton("Sửa thông tin");
        btnSua.setBounds(400, 600, 150, 40);
        btnSua.setBackground(new Color(245, 245, 220));
        btnSua.setForeground(new Color(101, 67, 33));
        btnSua.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnSua);

        JButton btnDMK = new JButton("Đổi mật khẩu");
        btnDMK.setBounds(600, 600, 150, 40);
        btnDMK.setBackground(new Color(245, 245, 220));
        btnDMK.setForeground(new Color(101, 67, 33));
        btnDMK.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnDMK);

        JButton btnXoa = new JButton("Close");
        btnXoa.setBounds(800, 600, 150, 40);
        btnXoa.setBackground(new Color(245, 245, 220));
        btnXoa.setForeground(new Color(101, 67, 33));
        btnXoa.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnXoa);

        JButton btnXN = new JButton("Xác Nhận");
        btnXN.setBounds(1000, 600, 150, 40);
        btnXN.setBackground(new Color(245, 245, 220));
        btnXN.setForeground(new Color(101, 67, 33));
        btnXN.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnXN);

        btnXN.setEnabled(false);
        btnChoose.setEnabled(false);
        setVisible(true);
        if (nhanVien != null) {
            updateForm(nhanVien);
        }
        btnSua.addActionListener(e -> {
            txtNameAC.setEnabled(true);
            txtTen.setEnabled(true);
            txtCCCD.setEnabled(true);
            txtNamSinh.setEnabled(true);
            txtSDT.setEnabled(true);
            txtEmail.setEnabled(true);
            btnChoose.setEnabled(true);
            btnXN.setEnabled(true);
            rdoNam.setEnabled(true);
            rdoNu.setEnabled(true);
        });
        btnXN.addActionListener(e -> {
            Integer idNV = Integer.parseInt(txtId.getText());
            String nameAC = txtNameAC.getText();
            String pass = new String(txtPass.getPassword());
            String role = txtRoll.getText();
            String tenNhanVien = txtTen.getText();
            String cccd = txtCCCD.getText();
            int namSinh = Integer.parseInt(txtNamSinh.getText());
            String sdt = txtSDT.getText();
            String email = txtEmail.getText();
            String gioiTinh = rdoNam.isSelected() ? "Nam" : "Nữ";
            String avatar = btnAvatar.getToolTipText();
            int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn sửa ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
            if (results == JOptionPane.YES_OPTION) {
                if (avatar == null || avatar.isEmpty()) {
                    avatar = nhanVien.getAvatar();
                }
                if (nameAC.isEmpty() || pass.isEmpty() || role.isEmpty() || tenNhanVien.isEmpty() || cccd.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                if (!cccd.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(null, "CCCD phải bao gồm đúng 12 chữ số.");
                    return;
                }

                try {
                    if (namSinh < 1900 || namSinh > java.time.Year.now().getValue()) {
                        JOptionPane.showMessageDialog(null, "Năm sinh không hợp lệ.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Năm sinh phải là số.");
                    return;
                }

                if (!sdt.matches("0\\d{9}")) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ.");
                    return;
                }

                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    JOptionPane.showMessageDialog(null, "Email không hợp lệ.");
                    return;
                }
                if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính.");
                    return;
                }
                if (DaoNhanVien.isCCCDExistsUPD(cccd, idNV)) {
                    JOptionPane.showMessageDialog(null, "CCCD đã tồn tại, vui lòng nhập CCCD khác.");
                    return;
                }

                if (DaoNhanVien.isSDTExistsUPD(sdt, idNV)) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại, vui lòng nhập SĐT khác.");
                    return;
                }
                if (DaoNhanVien.isEmailExistsUPD(email, idNV)) {
                    JOptionPane.showMessageDialog(null, "Email đã tồn tại, vui lòng nhập Email khác.");
                    return;
                }
                boolean success = DaoNhanVien.updateNhanVien(idNV, nameAC, pass, role, tenNhanVien, cccd, namSinh, sdt, email, gioiTinh, avatar);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            } else {
                return;
            }
        });

        btnXoa.addActionListener(e -> {
            dispose();
        });
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser choose = new JFileChooser();
                choose.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "gif"));
                int returnValue = choose.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = choose.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();
                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                    ImageIcon scale = new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                    btnAvatar.setIcon(scale); // Sử dụng ảnh đã scale
                    btnAvatar.setToolTipText(imagePath);
                }
            }
        });
        btnDMK.addActionListener(e -> {
            DMK dmk = new DMK(nhanVien, this);
            dmk.setVisible(true);
        });
    }

    public void updateForm(NhanVien nhanVien) {
        txtId.setText(String.valueOf(nhanVien.getNhanVienID()));
        txtNameAC.setText(nhanVien.getNameAC());
        txtPass.setText(nhanVien.getPass());
        txtRoll.setText(nhanVien.getRole());
        txtTen.setText(nhanVien.getTenNhanVien());
        txtCCCD.setText(nhanVien.getCccd());
        txtNamSinh.setText(String.valueOf(nhanVien.getNamSinh()));
        txtSDT.setText(nhanVien.getSdt());
        txtEmail.setText(nhanVien.getEmail());

        String gioiTinh = nhanVien.getGioiTinh();
        if ("Nam".equalsIgnoreCase(gioiTinh)) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        String avatarPath = nhanVien.getAvatar();
        ImageIcon avatarIcon = new ImageIcon(avatarPath);
        Image avatarImage = avatarIcon.getImage().getScaledInstance(btnAvatar.getWidth(), btnAvatar.getHeight(), Image.SCALE_SMOOTH);
        btnAvatar.setIcon(new ImageIcon(avatarImage));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(QLThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLThongTin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        NhanVien nhanVien = new NhanVien();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLThongTin(nhanVien).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
