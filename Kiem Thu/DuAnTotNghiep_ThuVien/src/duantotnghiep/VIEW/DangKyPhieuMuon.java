/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.CRUD;
import duantotnghiep.DAO.DaoDocGia;
import duantotnghiep.DAO.PhieuMuonDao;
import duantotnghiep.MODEL.ChiTietSach;
import duantotnghiep.MODEL.Sach;
import duantotnghiep.MODEL.SachMuon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.*;
import javax.swing.BorderFactory;

/**
 *
 * @author duck
 */
public class DangKyPhieuMuon extends javax.swing.JFrame {

    private JButton btnChonAnh, btnDangKy;
    private JComboBox<String> comboBox;
    private JTextField txtTen, txtCCCD, txtSDT, txtRank, txtTenSach, txtIDNhanVien, txtIDDocGia, txtNgayMuon, txtNgayTra, txtMaSach, txtTrangThai, txtLuuY, txtRankduoi, txtChiTietID, txtSoNgayMuon, txtIPhieuMuonID;
    private JTable table;
    private DefaultTableModel model;
    private JPanel sachPanel;
    ArrayList<ChiTietSach> danhsachchitiet;
    ArrayList<Sach> danhSachSach;

    /**
     * Creates new form DangKySach
     */
    public DangKyPhieuMuon() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Giấy Đăng Ký Mượn Sách");
        setSize(700, 600);
        getContentPane().setBackground(Color.decode("#FFF5EE"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("GIẤY ĐĂNG KÝ MƯỢN SÁCH");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitle.setBounds(530, 30, 500, 50);
        lblTitle.setForeground(Color.decode("#301d0e"));
        add(lblTitle);

        JLabel lblTen = new JLabel("Tên:");
        lblTen.setBounds(150, 140, 100, 25);
        lblTen.setFont(new Font("Arial", Font.BOLD, 15));
        lblTen.setForeground(Color.decode("#301d0e"));
        add(lblTen);
        comboBox = new JComboBox<>();
        comboBox.setEditable(true);
        comboBox.setBounds(280, 140, 400, 25);
        add(comboBox);
        txtTen = (JTextField) comboBox.getEditor().getEditorComponent();
        for (String ten : DaoDocGia.getTenDocGia("")) {
            comboBox.addItem(ten);
        }
        comboBox.setSelectedItem(null);
        txtTen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_ENTER) {
                    String selectedName = txtTen.getText().trim();
                    if (!selectedName.isEmpty()) {
                        DaoDocGia.layThongTinTheoTen(selectedName, txtIDDocGia, txtCCCD, txtSDT, txtRank);
                        comboBox.setPopupVisible(false);
                    }
                    return;
                }
                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_TAB) {
                    return;
                }

                String input = txtTen.getText();
                comboBox.removeAllItems();
                for (String ten : DaoDocGia.getTenDocGia(input.trim().isEmpty() ? "" : input.trim())) {
                    comboBox.addItem(ten);
                }

                comboBox.setSelectedItem(null);
                txtTen.setText(input);
                txtTen.setCaretPosition(input.length());

                if (comboBox.getItemCount() > 0) {
                    comboBox.setPopupVisible(true);
                }
            }
        });
        comboBox.addActionListener(e -> {
            String selectedName = (String) comboBox.getSelectedItem();
            if (selectedName != null && !selectedName.isEmpty()) {
                DaoDocGia.layThongTinTheoTen(selectedName, txtIDDocGia, txtCCCD, txtSDT, txtRank);
            } else {
                txtCCCD.setText("");
                txtSDT.setText("");
                txtRank.setText("");
            }
        });
        JLabel lblCCCD = new JLabel("CCCD:");
        lblCCCD.setBounds(150, 100, 100, 25);
        lblCCCD.setFont(new Font("Arial", Font.BOLD, 15));
        lblCCCD.setForeground(Color.decode("#301d0e"));
        add(lblCCCD);
        JComboBox<String> comboBoxCCCD = new JComboBox<>();
        comboBoxCCCD.setEditable(true);
        comboBoxCCCD.setBounds(280, 100, 400, 25);
        add(comboBoxCCCD);
        txtCCCD = (JTextField) comboBoxCCCD.getEditor().getEditorComponent();
        for (String cccd : DaoDocGia.getCCCDDocGia("")) {
            comboBoxCCCD.addItem(cccd);
        }
        comboBoxCCCD.setSelectedItem(null);

        txtCCCD.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_ENTER) {
                    String selectedCCCD = txtCCCD.getText().trim();
                    if (!selectedCCCD.isEmpty()) {
                        DaoDocGia.layThongTinTheoCCCD(selectedCCCD, txtIDDocGia, txtTen, txtSDT, txtRank);
                        comboBoxCCCD.setPopupVisible(false);
                    }
                    return;
                }
                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_TAB) {
                    return;
                }

                String input = txtCCCD.getText().trim();
                comboBoxCCCD.removeAllItems();
                for (String cccd : DaoDocGia.getCCCDDocGia(input)) {
                    comboBoxCCCD.addItem(cccd);
                }

                comboBoxCCCD.setSelectedItem(null);
                txtCCCD.setText(input);
                txtCCCD.setCaretPosition(input.length());

                if (comboBoxCCCD.getItemCount() > 0) {
                    comboBoxCCCD.setPopupVisible(true);
                }
            }
        });
        comboBoxCCCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCCCD = (String) comboBoxCCCD.getSelectedItem();
                if (selectedCCCD != null && !selectedCCCD.isEmpty()) {
                    DaoDocGia.layThongTinTheoCCCD(selectedCCCD, txtIDDocGia, txtTen, txtSDT, txtRank);
                } else {
                    // Xử lý trường hợp không có số điện thoại được chọn
                    txtTen.setText("");
                    txtSDT.setText("");
                    txtRank.setText("");

                }
            }
        });

        JLabel lblSDT = new JLabel("SĐT:");
        lblSDT.setBounds(150, 180, 100, 25);
        lblSDT.setForeground(Color.decode("#301d0e"));
        add(lblSDT);
        lblSDT.setFont(new Font("Arial", Font.BOLD, 15));

        JComboBox<String> comboBoxSDT = new JComboBox<>();
        comboBoxSDT.setEditable(true);
        comboBoxSDT.setBounds(280, 180, 400, 25);
        add(comboBoxSDT);
        txtSDT = (JTextField) comboBoxSDT.getEditor().getEditorComponent();
        for (String SDT : DaoDocGia.getSDTDocGia("")) {
            comboBoxSDT.addItem(SDT);
        }
        comboBoxSDT.setSelectedItem(null);

        txtSDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_ENTER) {
                    String selectedSDT = txtSDT.getText().trim();
                    if (!selectedSDT.isEmpty()) {
                        DaoDocGia.layThongTinTheoSDT(selectedSDT, txtIDDocGia, txtTen, txtCCCD, txtRank);
                        comboBoxSDT.setPopupVisible(false);
                    }
                    return;
                }
                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_TAB) {
                    return;
                }

                String input = txtSDT.getText().trim();
                comboBoxSDT.removeAllItems();
                for (String sdt : DaoDocGia.getSDTDocGia(input)) {
                    comboBoxSDT.addItem(sdt);
                }

                comboBoxSDT.setSelectedItem(null);
                txtSDT.setText(input);
                txtSDT.setCaretPosition(input.length());

                if (comboBoxSDT.getItemCount() > 0) {
                    comboBoxSDT.setPopupVisible(true);
                }
            }
        });
        comboBoxSDT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSDT = (String) comboBoxSDT.getSelectedItem();
                if (selectedSDT != null && !selectedSDT.isEmpty()) {
                    DaoDocGia.layThongTinTheoSDT(selectedSDT, txtIDDocGia, txtTen, txtCCCD, txtRank);
                } else {
                    // Xử lý trường hợp không có số điện thoại được chọn
                    txtTen.setText("");
                    txtCCCD.setText("");
                    txtRank.setText("");
                }
            }
        });

        txtRank = addInputField("Rank:", 150, 220);
        txtIPhieuMuonID = addInputField("Phiếu mượn ID:", 150, 260);
        txtIPhieuMuonID.setText("1");
        txtIDNhanVien = addInputField("Nhân viên ID:", 150, 300);
        txtIDDocGia = addInputField("Độc giả ID:", 150, 380);
        txtTenSach = addInputField("Tên sách:", 150, 420);
        txtChiTietID = addInputField("Chi tiết ID:", 150, 460);
        txtNgayMuon = addInputField("Ngày Mượn:", 150, 500);
        txtSoNgayMuon = addInputField("Số Ngày Mượn:", 150, 540);
        txtNgayTra = addInputField("Ngày Trả:", 150, 580);
        txtLuuY = addInputField("Ghi chú:", 150, 620);

        Font txtFont = txtTen.getFont();

        txtIPhieuMuonID.setFont(txtFont);
        txtCCCD.setFont(txtFont);
        txtSDT.setFont(txtFont);
        txtRank.setFont(txtFont);
        txtTenSach.setFont(txtFont);
        txtIDDocGia.setFont(txtFont);
        txtIDNhanVien.setFont(txtFont);
        txtNgayMuon.setFont(txtFont);
        txtNgayTra.setFont(txtFont);
//       txtMaSach.setFont(txtFont);
        txtLuuY.setFont(txtFont);
        txtChiTietID.setFont(txtFont);
        txtIDNhanVien.setText(String.valueOf(Login.currentUserId));
        txtRank.setEnabled(false);

        txtIPhieuMuonID.setEnabled(false);
        txtChiTietID.setEnabled(false);
        txtTenSach.setEnabled(false);
        txtIDDocGia.setEnabled(false);
        txtIDNhanVien.setEnabled(false);
        txtNgayMuon.setEnabled(false);
        txtNgayTra.setEnabled(false);

        btnDangKy = new JButton("Đăng ký");
        btnDangKy.setBounds(230, 690, 120, 40);

        btnDangKy.setBackground(Color.decode("#4d2913"));
        btnDangKy.setForeground(Color.WHITE);

        btnDangKy.setFocusPainted(false);
        btnDangKy.setBorderPainted(false);
        btnDangKy.setFont(new Font("Arial", Font.PLAIN, 15));
        btnDangKy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDangKy.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDangKy.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });

        add(btnDangKy);

        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/out-removebg-preview.png"));
        Image arrowimage = arrowIcon.getImage();
        Image scaledarrowImage = arrowimage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledarrowIcon = new ImageIcon(scaledarrowImage);
        JButton arrowButton = new JButton(scaledarrowIcon);
        arrowButton.setBounds(405, 690, 120, 40);
        add(arrowButton);
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtSoNgayMuon.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                tinhNgayTra();
            }
        });
        txtNgayMuon.setText(today.format(formatter));
        setVisible(true);

        JPanel panel_LichSuMuon = new JPanel();
        panel_LichSuMuon.setBackground(Color.decode("#4d2913"));
        panel_LichSuMuon.setBounds(720, 100, 700, 40);
        add(panel_LichSuMuon);

        JLabel lblLsuMuon = new JLabel("1.Lịch sử mượn");
        lblLsuMuon.setBounds(20, 20, 60, 30);
        lblLsuMuon.setForeground(Color.white);
        lblLsuMuon.setFont(new Font("Arial", Font.BOLD, 15));
        panel_LichSuMuon.add(lblLsuMuon);

        String[] columnNames = {"Sách ID", "Tên Sách", "Tác Giả", "Năm Xuất Bản", "Số Trang", "Giá Tiền", "Nhà Xuất Bản",
            "Chi Tiết ID", "Ngày Mượn", "Ngày Trả", "Trạng Thái", "Ghi Chú"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);  // Màu chữ tùy ý
        table.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 350));
        scrollPane.setBounds(720, 150, 700, 150);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);

        comboBox.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String selectedSDT = (String) comboBox.getSelectedItem();
                if (selectedSDT == null || selectedSDT.trim().isEmpty()) {
                    return;
                }

                DaoDocGia.layThongTinTheoTen(selectedSDT, txtIDDocGia, txtCCCD, txtSDT, txtRank);
                int idDocGia = Integer.parseInt(txtIDDocGia.getText().trim());
                ArrayList<SachMuon> danhSachSachMuon = PhieuMuonDao.getAllTen(idDocGia);
                model.setRowCount(0);

                for (SachMuon sm : danhSachSachMuon) {
                    Object[] rowData = {
                        sm.sachID,
                        sm.tenSach,
                        sm.tacGia,
                        sm.namXuatBan,
                        sm.soTrang,
                        sm.giaTien,
                        sm.nhaXuatBan,
                        sm.chiTietID,
                        sm.ngayMuon,
                        sm.hanTra,
                        sm.trangThai,
                        sm.ghiChu
                    };
                    model.addRow(rowData);
                }
            }
        }
        );
        comboBoxSDT.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String selectedSDT = (String) comboBoxSDT.getSelectedItem();
                if (selectedSDT == null || selectedSDT.trim().isEmpty()) {
                    return;
                }

                DaoDocGia.layThongTinTheoSDT(selectedSDT, txtIDDocGia, txtTen, txtCCCD, txtRank);
                ArrayList<SachMuon> danhSachSachMuon = PhieuMuonDao.getAllSDT(selectedSDT);
                model.setRowCount(0);

                for (SachMuon sm : danhSachSachMuon) {
                    Object[] rowData = {
                        sm.sachID,
                        sm.tenSach,
                        sm.tacGia,
                        sm.namXuatBan,
                        sm.soTrang,
                        sm.giaTien,
                        sm.nhaXuatBan,
                        sm.chiTietID,
                        sm.ngayMuon,
                        sm.hanTra,
                        sm.trangThai,
                        sm.ghiChu
                    };
                    model.addRow(rowData);
                }
            }
        }
        );
        comboBoxCCCD.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String selectedSDT = (String) comboBoxCCCD.getSelectedItem();
                if (selectedSDT == null || selectedSDT.trim().isEmpty()) {
                    return;
                }

                DaoDocGia.layThongTinTheoCCCD(selectedSDT, txtIDDocGia, txtTen, txtSDT, txtRank);
                ArrayList<SachMuon> danhSachSachMuon = PhieuMuonDao.getAllCCCD(selectedSDT);
                model.setRowCount(0);

                for (SachMuon sm : danhSachSachMuon) {
                    Object[] rowData = {
                        sm.sachID,
                        sm.tenSach,
                        sm.tacGia,
                        sm.namXuatBan,
                        sm.soTrang,
                        sm.giaTien,
                        sm.nhaXuatBan,
                        sm.chiTietID,
                        sm.ngayMuon,
                        sm.hanTra,
                        sm.trangThai,
                        sm.ghiChu
                    };
                    model.addRow(rowData);
                }
            }
        }
        );

        JPanel panel_bangSach = new JPanel();
        panel_bangSach.setBackground(Color.decode("#4d2913"));
        panel_bangSach.setBounds(720, 320, 700, 40);
        add(panel_bangSach);

        JLabel lblBSach = new JLabel("2.Bảng Sách");
        lblBSach.setBounds(20, 20, 60, 40);
        lblBSach.setForeground(Color.white);
        lblBSach.setFont(new Font("Arial", Font.BOLD, 15));
        panel_bangSach.add(lblBSach);

        String[] columnSach = {"STT", "SachID", "Tên sách", "Tác giả", "Năm sản xuất", "Số trang", "Giá tiền", "Nhà xuất bản", "Rank", "TrangThai", "MoTa", "SoNgayToiDa", "Avatar", "Số lượng"};
        DefaultTableModel modelSach = new DefaultTableModel(columnSach, 0);
        JTable tableSach = new JTable(modelSach);

        tableSach.setBackground(Color.WHITE);
        tableSach.setForeground(Color.BLACK);
        tableSach.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPaneSach = new JScrollPane(tableSach);
        scrollPaneSach.setPreferredSize(new Dimension(780, 350));
        scrollPaneSach.setBounds(720, 370, 700, 150);

        scrollPaneSach.getViewport().setBackground(Color.WHITE);
        add(scrollPaneSach, BorderLayout.CENTER);
        tableSach.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableSach.setRowHeight(60);
        tableSach.getColumnModel().getColumn(12).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                if (value != null) {
                    String imagePath = value.toString();
                    File file = new File(imagePath);
                    if (file.exists()) {
                        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                        label.setIcon(icon);
                    } else {
                        label.setText("Không tìm thấy ảnh");
                    }
                }
                return label;
            }
        });
        setVisible(true);

        JPanel panel_BangSachChiTiet = new JPanel();
        panel_BangSachChiTiet.setBackground(Color.decode("#4d2913"));
        panel_BangSachChiTiet.setBounds(720, 540, 700, 40);
        add(panel_BangSachChiTiet);

        JLabel lblBCTS = new JLabel("3.Bảng Sách Chi Tiết");
        lblBCTS.setBounds(20, 20, 60, 30);
        lblBCTS.setForeground(Color.white);
        lblBCTS.setFont(new Font("Arial", Font.BOLD, 15));
        panel_BangSachChiTiet.add(lblBCTS);

        String[] columnSachChiTiet = {"STT", "ChiTietID", "Sách ID", "Trạng thái", "Tình trạng sách", "Ngày nhập"};
        DefaultTableModel modelSachChiTiet = new DefaultTableModel(columnSachChiTiet, 0);
        JTable tableSachChiTiet = new JTable(modelSachChiTiet);
        tableSachChiTiet.setBackground(Color.WHITE);
        tableSachChiTiet.setForeground(Color.BLACK);
        tableSachChiTiet.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPaneSachChiTiet = new JScrollPane(tableSachChiTiet);
        scrollPaneSachChiTiet.setPreferredSize(new Dimension(780, 350));
        scrollPaneSachChiTiet.setBounds(720, 590, 700, 150);

        scrollPaneSachChiTiet.getViewport().setBackground(Color.WHITE);
        add(scrollPaneSachChiTiet, BorderLayout.CENTER);
        tableSachChiTiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModelSachCT = tableSachChiTiet.getColumnModel();
        columnModelSachCT.getColumn(3).setPreferredWidth(150);
/////

        JLabel lblSachID = new JLabel("SachID:");
        lblSachID.setBounds(150, 340, 300, 25);
        lblSachID.setFont(new Font("Arial", Font.BOLD, 15));
        lblSachID.setForeground(Color.decode("#301d0e"));
        add(lblSachID);

        txtMaSach = new JTextField();
        txtMaSach.setEditable(true);
        txtMaSach.setBounds(280, 340, 400, 30);
        add(txtMaSach);

        txtMaSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int sachID = Integer.parseInt(txtMaSach.getText());

                    // Cập nhật danh sách sách
                    DaoDocGia.layThongTinTheoSachID(sachID, txtTenSach);
                    ArrayList<Sach> danhSachSach = PhieuMuonDao.getAll(sachID);
                    modelSach.setRowCount(0);  // Xóa dữ liệu cũ trong bảng
                    int STT = 1;
                    for (Sach sach : danhSachSach) {
                        Object[] rowData = {
                            STT++ + "",
                            sach.getSachID(),
                            sach.getTenSach(),
                            sach.getTacGia(),
                            sach.getNamXuatBan(),
                            sach.getSoTrang(),
                            sach.getGiaTien(),
                            sach.getNhaXuatBan(),
                            sach.getRank(),
                            sach.getTrangThai(),
                            sach.getMota(),
                            sach.getSoNgayMuon(),
                            sach.getAvatar(),
                            sach.getSoLuong()
                        };
                        modelSach.addRow(rowData);
                    }

                    // Cập nhật chi tiết sách
                    ArrayList<ChiTietSach> danhSachChiTietSach = PhieuMuonDao.getChiTietSachBySachID(sachID);
                    modelSachChiTiet.setRowCount(0);  // Xóa dữ liệu cũ trong bảng chi tiết
                    int STTCT = 1;
                    for (ChiTietSach chiTietSach : danhSachChiTietSach) {
                        Object[] rowData = {
                            STTCT++ + "",
                            chiTietSach.getChiTietID(),
                            chiTietSach.getSachID(),
                            chiTietSach.getTrangThai(),
                            chiTietSach.getTinhTrangSach(),
                            chiTietSach.getNgayNhap()
                        };
                        modelSachChiTiet.addRow(rowData);
                    }

                    // Chọn chi tiết ID cho sách nếu có
                    DefaultTableModel model = (DefaultTableModel) tableSachChiTiet.getModel();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        int idTrongBang = Integer.parseInt(model.getValueAt(i, 2).toString()); // Sách ID
                        String trangThai = model.getValueAt(i, 3).toString().trim(); // Trạng thái

                        if (idTrongBang == sachID && trangThai.equalsIgnoreCase("Đang có sẵn")) {
                            int chiTietID = Integer.parseInt(model.getValueAt(i, 1).toString()); // ChiTietID
                            txtChiTietID.setText(String.valueOf(chiTietID));
                            break;
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sách hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tableSachChiTiet.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e
            ) {
                int selectedRow = tableSachChiTiet.getSelectedRow();
                if (selectedRow != -1) {
                    txtChiTietID.setText(tableSachChiTiet.getValueAt(selectedRow, 1).toString());
                }
            }
        }
        );
        btnDangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strPhieuMuonID = txtIPhieuMuonID.getText().trim();
                String strNhanVienID = txtIDNhanVien.getText().trim();
                String strDocGiaIDText = txtIDDocGia.getText().trim();
                String strChiTietID = txtChiTietID.getText().trim();
                String strSoNgayMuon = txtSoNgayMuon.getText().trim();
                String strNgayMuon = txtNgayMuon.getText().trim();
                String ghiChu = txtLuuY.getText().trim();
                String tensach = txtTenSach.getText().trim();
                String strSachIDText = txtMaSach.getText().trim();

                // Kiểm tra nếu ID độc giả và sách không phải là chuỗi rỗng
                if (strPhieuMuonID.isEmpty() || strNhanVienID.isEmpty() || strDocGiaIDText.isEmpty()
                        || tensach.isEmpty() || strNgayMuon.isEmpty() || strSachIDText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                    return;
                }

                try {
                    Integer strDocGiaID = Integer.parseInt(strDocGiaIDText);
                    Integer sachID = Integer.parseInt(strSachIDText);

                    // Kiểm tra xem độc giả có hoạt động không
                    if (!PhieuMuonDao.isDocGiaHoatDong(strDocGiaID)) {
                        JOptionPane.showMessageDialog(null, "Độc giả không hoạt động, không thể mượn sách!");
                        return;
                    } else if (strChiTietID.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Sách đã hết!");
                        return;
                    }

                    int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đăng ký không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (results == JOptionPane.YES_OPTION) {
                        int PhieuMuonID = Integer.parseInt(strPhieuMuonID);
                        int nhanVienID = Integer.parseInt(strNhanVienID);
                        int chiTietID = Integer.parseInt(strChiTietID);
                        int soNgayMuon = Integer.parseInt(strSoNgayMuon);
                        Date ngayMuon = Date.valueOf(strNgayMuon); // yyyy-MM-dd

                        if (kiemTraRank(strDocGiaID, sachID)) {
                            if (kiemTraSoNgayMuonHopLe(sachID, soNgayMuon)) {
                                boolean result = PhieuMuonDao.createPhieuMuon(
                                        PhieuMuonID, nhanVienID, strDocGiaID, sachID, chiTietID, ngayMuon, soNgayMuon, ghiChu
                                );

                                if (result) {
                                    JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                                    ArrayList<SachMuon> danhSachSachMuon = PhieuMuonDao.getAllTen(strDocGiaID);
                                    model.setRowCount(0);

                                    for (SachMuon sm : danhSachSachMuon) {
                                        Object[] rowData = {
                                            sm.sachID,
                                            sm.tenSach,
                                            sm.tacGia,
                                            sm.namXuatBan,
                                            sm.soTrang,
                                            sm.giaTien,
                                            sm.nhaXuatBan,
                                            sm.chiTietID,
                                            sm.ngayMuon,
                                            sm.hanTra,
                                            sm.trangThai,
                                            sm.ghiChu
                                        };
                                        model.addRow(rowData);
                                    }

                                    ArrayList<ChiTietSach> danhSachChiTietSach = PhieuMuonDao.getChiTietSachBySachID(sachID);
                                    modelSachChiTiet.setRowCount(0);
                                    int STTCT = 1;
                                    for (ChiTietSach chiTietSach : danhSachChiTietSach) {
                                        Object[] rowData = {
                                            STTCT++ + "",
                                            chiTietSach.getChiTietID(),
                                            chiTietSach.getSachID(),
                                            chiTietSach.getTrangThai(),
                                            chiTietSach.getTinhTrangSach(),
                                            chiTietSach.getNgayNhap()
                                        };
                                        modelSachChiTiet.addRow(rowData);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Đăng ký thất bại!");
                                    return;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Số ngày mượn vượt quá giới hạn cho phép.");
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Rank của độc giả không đủ để mượn sách này.");
                            return;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                    return;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                    return;
                }
            }
        });
    }

    public JTextField addInputField(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 120, 25);
        label.setForeground(Color.decode("#301d0e"));
        label.setFont(new Font("Arial", Font.BOLD, 15));
        add(label);

        JTextField textField = new JTextField();
        textField.setBounds(x + 130, y, 400, 30);
        add(textField);

        return textField;
    }

    private void tinhNgayTra() {
        try {
            int soNgay = Integer.parseInt(txtSoNgayMuon.getText());
            LocalDate ngayMuon = LocalDate.now(); // Lấy ngày hiện tại
            LocalDate ngayTra = ngayMuon.plusDays(soNgay);
            txtNgayTra.setText(ngayTra.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (NumberFormatException ex) {
            txtNgayTra.setText("");
        }
    }

    public static boolean kiemTraRank(int docGiaID, int sachID) {
        String query = """
            SELECT 
                (CASE 
                    WHEN d.Rank = 'Bronze' THEN 1
                    WHEN d.Rank = 'Silver' THEN 2
                    WHEN d.Rank = 'Gold' THEN 3
                    WHEN d.Rank = 'Platinum' THEN 4
                    WHEN d.Rank = 'Diamond' THEN 5
                END) AS RankDocGia,
                (CASE 
                    WHEN s.Rank = 'Bronze' THEN 1
                    WHEN s.Rank = 'Silver' THEN 2
                    WHEN s.Rank = 'Gold' THEN 3
                    WHEN s.Rank = 'Platinum' THEN 4
                    WHEN s.Rank = 'Diamond' THEN 5
                END) AS RankSach
            FROM DocGia d
            JOIN Sach s ON s.SachID = ?
            WHERE d.ID_DocGia = ?;
        """;

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, sachID);
            stmt.setInt(2, docGiaID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int rankDocGia = rs.getInt("RankDocGia");
                int rankSach = rs.getInt("RankSach");
                return rankDocGia >= rankSach;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean kiemTraSoNgayMuonHopLe(int sachID, int soNgayMuon) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            String query = "SELECT SoNgayToiDa FROM Sach WHERE SachID = ?";
            try (PreparedStatement stm = con.prepareStatement(query)) {
                stm.setInt(1, sachID);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    int soNgayToiDa = rs.getInt("SoNgayToiDa");
                    return soNgayMuon <= soNgayToiDa;
                }
            }
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
            java.util.logging.Logger.getLogger(DangKyPhieuMuon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKyPhieuMuon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKyPhieuMuon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKyPhieuMuon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKyPhieuMuon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
