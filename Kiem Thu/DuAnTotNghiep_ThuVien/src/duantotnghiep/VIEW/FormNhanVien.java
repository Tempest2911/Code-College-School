/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoNhanVien;
import duantotnghiep.MODEL.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MINHDUC
 */
public class FormNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form FormNhanVien
     */
    int idSelected = 0;
    private String selectedFilePath = null;
    ArrayList<NhanVien> danhNhanViens;

    public FormNhanVien() {
        initComponents();
        Form();
    }

    public void Form() {
        setTitle("Hệ thống quản lý nhân viên");

        // Lấy kích thước màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#FAF5EF"));
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitle.setBounds(530, 30, 500, 50);
        lblTitle.setForeground(Color.decode("#301d0e"));
        add(lblTitle);

        JLabel lblId = new JLabel("ID nhân viên");
        lblId.setBounds(120, 100, 200, 30);
        lblId.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblId.setForeground(Color.decode("#5a2e0b"));
        add(lblId);

        JTextField txtId = new JTextField("");
        txtId.setBounds(250, 100, 300, 30);
        add(txtId);

        txtId.setEditable(false);
        JLabel lblNameAC = new JLabel("Tên tài khoản");
        lblNameAC.setBounds(120, 140, 200, 30);
        lblNameAC.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblNameAC.setForeground(Color.decode("#5a2e0b"));
        add(lblNameAC);

        JTextField txtNameAC = new JTextField("");
        txtNameAC.setBounds(250, 140, 300, 30);
        add(txtNameAC);

        JLabel lblPass = new JLabel("Mật khẩu");
        lblPass.setBounds(120, 180, 200, 30);
        lblPass.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblPass.setForeground(Color.decode("#5a2e0b"));
        add(lblPass);
        JPasswordField txtPass = new JPasswordField("");
        txtPass.setBounds(250, 180, 300, 30);

        add(txtPass);

        txtNameAC.setEnabled(true);
        txtPass.setEnabled(true);

        JLabel lblRole = new JLabel("Role");
        lblRole.setBounds(120, 220, 200, 30);
        lblRole.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblRole.setForeground(Color.decode("#5a2e0b"));
        add(lblRole);

        JTextField txtRole = new JTextField("");
        txtRole.setBounds(250, 220, 300, 30);
        add(txtRole);

        txtRole.setEnabled(false);

        JLabel lblTen = new JLabel("Tên Nhân Viên");
        lblTen.setBounds(120, 260, 200, 30);
        lblTen.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblTen.setForeground(Color.decode("#5a2e0b"));
        add(lblTen);

        JTextField txtTen = new JTextField("");
        txtTen.setBounds(250, 260, 300, 30);

        add(txtTen);

        JLabel lblCCCD = new JLabel("CCCD");
        lblCCCD.setBounds(650, 100, 200, 30);
        lblCCCD.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblCCCD.setForeground(Color.decode("#5a2e0b"));
        add(lblCCCD);

        JTextField txtCCCD = new JTextField("");
        txtCCCD.setBounds(750, 100, 300, 30);
        add(txtCCCD);
        txtCCCD.setEnabled(false);

        ////////////////////////////////     
        JLabel lblNamSinh = new JLabel("Năm Sinh");
        lblNamSinh.setBounds(650, 140, 200, 30);
        lblNamSinh.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblNamSinh.setForeground(Color.decode("#5a2e0b"));
        add(lblNamSinh);
        JTextField txtNamSinh = new JTextField("");
        txtNamSinh.setBounds(750, 140, 300, 30);
        add(txtNamSinh);

        JLabel lblSDT = new JLabel("SĐT");
        lblSDT.setBounds(650, 180, 200, 30);
        lblSDT.setForeground(Color.decode("#5a2e0b"));
        lblSDT.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        add(lblSDT);
        JTextField txtSDT = new JTextField("");
        txtSDT.setBounds(750, 180, 300, 30);
        add(txtSDT);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.decode("#5a2e0b"));
        lblEmail.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblEmail.setBounds(650, 220, 200, 30);
        add(lblEmail);
        JTextField txtEmail = new JTextField("");
        txtEmail.setBounds(750, 220, 300, 30);
        add(txtEmail);

        JLabel lblGioiTinh = new JLabel("Giới Tính");
        lblGioiTinh.setForeground(Color.decode("#5a2e0b"));
        lblGioiTinh.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblGioiTinh.setBounds(650, 260, 200, 30);
        add(lblGioiTinh);
        JRadioButton rdoNam = new JRadioButton("Nam");
        rdoNam.setBounds(750, 260, 100, 30);
        rdoNam.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));
        add(rdoNam);
        JRadioButton rdoNu = new JRadioButton("Nữ");
        rdoNu.setBounds(900, 260, 200, 30);
        rdoNu.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));

        add(rdoNu);
        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(rdoNam);
        groupGioiTinh.add(rdoNu);
        JButton btnAvatar = new JButton("");
        btnAvatar.setBounds(1140, 100, 200, 200);
        add(btnAvatar);
        JButton btnChoose = new JButton("Chọn ảnh");
        btnChoose.setBounds(1190, 330, 100, 30);
        btnChoose.setBackground(Color.white);
        add(btnChoose);

        JButton btnThem = new JButton("Thêm");
        btnThem.setBounds(180, 330, 200, 30);
        add(btnThem);

        btnThem.setBackground(Color.decode("#4d2913"));
        btnThem.setForeground(Color.WHITE);

        btnThem.setFocusPainted(false);
        btnThem.setBorderPainted(false);
        btnThem.setFont(new Font("Arial", Font.PLAIN, 15));
        btnThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnThem.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnThem.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });

        JButton btnXoa = new JButton("Xoá");
        btnXoa.setBounds(400, 330, 200, 30);
        add(btnXoa);
        btnXoa.setBackground(Color.decode("#4d2913"));
        btnXoa.setForeground(Color.WHITE);

        btnXoa.setFocusPainted(false);
        btnXoa.setBorderPainted(false);
        btnXoa.setFont(new Font("Arial", Font.PLAIN, 15));
        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnXoa.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(620, 330, 200, 30);
        add(btnClose);
        btnClose.setBackground(Color.decode("#4d2913"));
        btnClose.setForeground(Color.WHITE);

        btnClose.setFocusPainted(false);
        btnClose.setBorderPainted(false);
        btnClose.setFont(new Font("Arial", Font.PLAIN, 15));
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });

        String[] columnNames = {"STT", "ID Nhân Viên", "NameAC", "Pass", "Role", "Tên Nhân Viên", "CCCD", "Năm Sinh", "SĐT", "Email", "Giới Tính", "Avatar"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);
        table.setRowHeight(30);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.decode("#4d2913"));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE); // 👈 nền bên trong scroll
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : Color.decode("#FAF5EF")); // nền xen kẽ trắng và be nhạt
                }
                return c;
            }
        });

        scrollPane.setBounds(0, 400, getWidth(), 400);
        add(scrollPane);

        danhNhanViens = DaoNhanVien.getAll();

        int STT = 1;
        for (NhanVien nv : danhNhanViens) {
            Object[] rowData = {
                STT++ + "",
                nv.nhanVienID,
                nv.NameAC,
                "******",
                nv.Role,
                nv.tenNhanVien,
                nv.cccd,
                nv.namSinh,
                nv.sdt,
                nv.email,
                nv.gioiTinh,
                nv.Avatar
            };
            model.addRow(rowData);
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtId.setText(table.getValueAt(selectedRow, 1).toString());
                    txtNameAC.setText(table.getValueAt(selectedRow, 2).toString());
                    txtPass.setText(table.getValueAt(selectedRow, 3).toString());
                    txtRole.setText(table.getValueAt(selectedRow, 4).toString());
                    txtTen.setText(table.getValueAt(selectedRow, 5).toString());
                    txtCCCD.setText(table.getValueAt(selectedRow, 6).toString());
                    txtNamSinh.setText(table.getValueAt(selectedRow, 7).toString());
                    txtSDT.setText(table.getValueAt(selectedRow, 8).toString());
                    txtEmail.setText(table.getValueAt(selectedRow, 9).toString());

                    // Xử lý Giới Tính
                    String gioiTinh = table.getValueAt(selectedRow, 10).toString();
                    if (gioiTinh.equalsIgnoreCase("Nam")) {
                        rdoNam.setSelected(true);
                    } else {
                        rdoNu.setSelected(true);
                    }
                    Object value = table.getValueAt(selectedRow, 11);
                    if (value != null && !value.toString().isEmpty()) {
                        String imagePath = value.toString();
                        File file = new File(imagePath);
                        if (file.exists()) {
                            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                            btnAvatar.setIcon(icon);
                            btnAvatar.setText("");
                        } else {
                            btnAvatar.setIcon(null);
                            btnAvatar.setText("Không có ảnh");
                        }
                    } else {
                        btnAvatar.setIcon(null);
                        btnAvatar.setText("Không có ảnh");
                    }

                    idSelected = danhNhanViens.get(selectedRow).getNhanVienID();
                }
            }
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
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QLNhanVien qlnv = new QLNhanVien();
                dispose();
                qlnv.setVisible(true);
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenNhanVien = txtTen.getText();
                String NameAC = txtNameAC.getText();
                String Pass = String.valueOf(txtPass.getPassword());
                String cccd = txtCCCD.getText();
                Integer namSinh = Integer.parseInt(txtNamSinh.getText());
                String sdt = txtSDT.getText();
                String email = txtEmail.getText();
                String gioiTinh = rdoNam.isSelected() ? "Nam" : "Nữ";
                String avatar = btnAvatar.getToolTipText();

                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thêm ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    if (tenNhanVien.isEmpty() || NameAC.isEmpty() || Pass.isEmpty() || cccd.isEmpty() || namSinh == null
                            || sdt.isEmpty() || email.isEmpty() || gioiTinh.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
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
                    if (DaoNhanVien.isCCCDExists(cccd)) {
                        JOptionPane.showMessageDialog(null, "CCCD đã tồn tại, vui lòng nhập CCCD khác.");
                        return;
                    }

                    if (DaoNhanVien.isSDTExists(sdt)) {
                        JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại, vui lòng nhập SĐT khác.");
                        return;
                    }
                    if (DaoNhanVien.isNameACExists(NameAC)) {
                        JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại, vui lòng nhập tên khác.");
                        return;
                    }

                    boolean result = DaoNhanVien.createNhanVien(NameAC, Pass, tenNhanVien, cccd, namSinh, sdt, email, gioiTinh, avatar);

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhNhanViens = DaoNhanVien.getAll();
                        int STT = 1;
                        for (NhanVien nv : danhNhanViens) {
                            Object[] rowData = {
                                STT++ + "",
                                nv.nhanVienID,
                                nv.NameAC,
                                "******",
                                nv.Role,
                                nv.tenNhanVien,
                                nv.cccd,
                                nv.namSinh,
                                nv.sdt,
                                nv.email,
                                nv.gioiTinh,
                                nv.Avatar
                            };
                            model.addRow(rowData);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = idSelected;
                if (idSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để xoá.");
                    return;
                }
                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xoá ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    boolean result = DaoNhanVien.deleteNhanVien(id);

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhNhanViens = DaoNhanVien.getAll();
                        int STT = 1;
                        for (NhanVien nv : danhNhanViens) {
                            Object[] rowData = {
                                STT++ + "",
                                nv.nhanVienID,
                                nv.NameAC,
                                "******",
                                nv.Role,
                                nv.tenNhanVien,
                                nv.cccd,
                                nv.namSinh,
                                nv.sdt,
                                nv.email,
                                nv.gioiTinh,
                                nv.Avatar
                            };
                            model.addRow(rowData);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
        });

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
            java.util.logging.Logger.getLogger(FormNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
