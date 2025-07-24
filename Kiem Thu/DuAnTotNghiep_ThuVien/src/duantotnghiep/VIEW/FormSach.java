/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoChiTietSach;
import duantotnghiep.DAO.DaoSach;
import duantotnghiep.MODEL.ChiTietSach;
import duantotnghiep.MODEL.Sach;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class FormSach extends javax.swing.JFrame {

    /**
     * Creates new form FormSach
     */
    int idSelected = 0;
    ArrayList<Sach> danhSachSach;

    public FormSach() {
        initComponents();
        Form();
    }

    public void Form() {
        setTitle("Hệ thống quản lý thư viện");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#FAF5EF"));

        JLabel lblTitle = new JLabel("QUẢN LÝ SÁCH");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitle.setBounds(600, 30, 500, 50);
        lblTitle.setForeground(Color.decode("#301d0e"));
        add(lblTitle);

        JLabel lblId = new JLabel("STT");
        lblId.setBounds(120, 100, 200, 30);
        lblId.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblId.setForeground(Color.decode("#5a2e0b"));
        add(lblId);
        JTextField txtId = new JTextField("");
        txtId.setBounds(220, 100, 300, 30);
        add(txtId);
        txtId.setEditable(false);

        JLabel lblTen = new JLabel("Tên sách");
        lblTen.setBounds(120, 140, 200, 30);
        lblTen.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblTen.setForeground(Color.decode("#5a2e0b"));
        add(lblTen);
        JTextField txtTen = new JTextField("");
        txtTen.setBounds(220, 140, 300, 30);
        add(txtTen);

        JLabel lblTacGia = new JLabel("Tác giả");
        lblTacGia.setBounds(120, 180, 200, 30);
        lblTacGia.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblTacGia.setForeground(Color.decode("#5a2e0b"));
        add(lblTacGia);
        JTextField txtTacGia = new JTextField("");
        txtTacGia.setBounds(220, 180, 300, 30);
        add(txtTacGia);

        JLabel lblNXB = new JLabel("Năm XB");
        lblNXB.setBounds(120, 220, 200, 30);
        lblNXB.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblNXB.setForeground(Color.decode("#5a2e0b"));
        add(lblNXB);
        JTextField txtNXB = new JTextField("");
        txtNXB.setBounds(220, 220, 300, 30);
        add(txtNXB);

        JLabel lblSL = new JLabel("Số lượng");
        lblSL.setBounds(120, 260, 200, 30);
        lblSL.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblSL.setForeground(Color.decode("#5a2e0b"));
        add(lblSL);
        JTextField txtSL = new JTextField("");
        txtSL.setBounds(220, 260, 300, 30);
        add(txtSL);
        txtSL.setEditable(false);

        JLabel lblST = new JLabel("Số trang");
        lblST.setBounds(120, 300, 200, 30);
        lblST.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblST.setForeground(Color.decode("#5a2e0b"));
        add(lblST);
        JTextField txtST = new JTextField("");
        txtST.setBounds(220, 300, 300, 30);
        add(txtST);

        JLabel lblGia = new JLabel("Giá tiền");
        lblGia.setBounds(650, 100, 200, 30);
        lblGia.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblGia.setForeground(Color.decode("#5a2e0b"));
        add(lblGia);
        JTextField txtGia = new JTextField("");
        txtGia.setBounds(750, 100, 300, 30);
        add(txtGia);

        JLabel lblNhaXB = new JLabel("Nhà XB");
        lblNhaXB.setBounds(650, 140, 200, 30);
        lblNhaXB.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblNhaXB.setForeground(Color.decode("#5a2e0b"));
        add(lblNhaXB);
        JTextField txtNhaXB = new JTextField("");
        txtNhaXB.setBounds(750, 140, 300, 30);
        add(txtNhaXB);

        JLabel lblRank = new JLabel("Rank");
        lblRank.setBounds(650, 180, 200, 30);
        lblRank.setForeground(Color.decode("#5a2e0b"));
        lblRank.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        add(lblRank);

        String[] items = {"Bronze", "Silver", "Gold", "Platinum", "Diamond"};
        JComboBox<String> cboRank = new JComboBox<>(items);
        cboRank.setSize(300, 30);
        cboRank.setLocation(750, 180);
        add(cboRank);
        JLabel lblTT = new JLabel("Trạng thái");
        lblTT.setForeground(Color.decode("#5a2e0b"));
        lblTT.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblTT.setBounds(650, 220, 200, 30);
        add(lblTT);
        JRadioButton rdoCon = new JRadioButton("Còn");
        rdoCon.setBounds(750, 220, 300, 30);
        add(rdoCon);

        JRadioButton rdoHet = new JRadioButton("Hết");
        rdoHet.setBounds(900, 220, 300, 30);
        add(rdoHet);
        ButtonGroup groupTrangThai = new ButtonGroup();
        groupTrangThai.add(rdoCon);
        groupTrangThai.add(rdoHet);
        rdoCon.setEnabled(false);
        rdoHet.setEnabled(false);
        JLabel lblMoTa = new JLabel("Mô tả");
        lblMoTa.setForeground(Color.decode("#5a2e0b"));
        lblMoTa.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));

        lblMoTa.setBounds(650, 260, 200, 30);
        add(lblMoTa);
        JTextField txtMoTa = new JTextField("");
        txtMoTa.setBounds(750, 260, 300, 30);
        add(txtMoTa);
        JLabel lblNMTD = new JLabel("NM tối đa");
        lblNMTD.setForeground(Color.decode("#5a2e0b"));
        lblNMTD.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));

        lblNMTD.setBounds(650, 300, 200, 30);
        add(lblNMTD);
        JTextField txtNMTD = new JTextField("");
        txtNMTD.setBounds(750, 300, 300, 30);
        add(txtNMTD);
        txtNMTD.setEditable(false);

        cboRank.addActionListener(e -> {
            String selectedRank = (String) cboRank.getSelectedItem();
            int soNgayToiDa = switch (selectedRank) {
                case "Bronze" ->
                    7;
                case "Silver" ->
                    10;
                case "Gold" ->
                    14;
                case "Platinum" ->
                    21;
                case "Diamond" ->
                    30;
                default ->
                    0;
            };
            txtNMTD.setText(String.valueOf(soNgayToiDa));
        });
        txtSL.setEnabled(false);
        txtNMTD.setEnabled(false);
        JButton btnAvatar = new JButton("");
        btnAvatar.setSize(210, 240);
        btnAvatar.setLocation(1170, 100);
        add(btnAvatar);
        JButton btnChoose = new JButton("Chọn ảnh");
        btnChoose.setSize(100, 30);
        btnChoose.setLocation(1220, 360);
        add(btnChoose);
        JButton btnThem = new JButton("Thêm");
        btnThem.setSize(100, 30);
        btnThem.setLocation(150, 360);
        add(btnThem);
        JButton btnSua = new JButton("Sửa");
        btnSua.setSize(100, 30);
        btnSua.setLocation(300, 360);
        add(btnSua);
        JButton btnXoa = new JButton("Xoá");
        btnXoa.setSize(100, 30);
        btnXoa.setLocation(450, 360);
        add(btnXoa);
        JButton btnClose = new JButton("Close");
        btnClose.setSize(100, 30);
        btnClose.setLocation(600, 360);
        add(btnClose);

        btnChoose.setBackground(Color.white);
        btnThem.setBackground(Color.decode("#4d2913"));
        btnThem.setForeground(Color.WHITE);
        btnXoa.setBackground(Color.decode("#4d2913"));
        btnXoa.setForeground(Color.WHITE);
        btnSua.setBackground(Color.decode("#4d2913"));
        btnSua.setForeground(Color.WHITE);
        btnClose.setBackground(Color.decode("#4d2913"));
        btnClose.setForeground(Color.WHITE);

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

        btnSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSua.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSua.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });

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

        String[] columnNames = {"STT", "Tên sách", "Tác giả", "Năm sản xuất", "Số trang", "Giá tiền", "Nhà xuất bản", "Rank", "TrangThai", "Mota", "SoNgayToiDa", "Avatar", "Số lượng"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(
                new Dimension(getWidth(), 350));
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setLocation(0, 400);
        scrollPane.setSize(getWidth(),400);
        model.setRowCount(0);
        danhSachSach = DaoSach.getAll();
        table.getColumnModel().getColumn(8).setCellRenderer(new FormSach.TrangThaiTableCellRenderer());

        int STT = 1;
        for (Sach sach : danhSachSach) {
            Object[] rowData = {
                STT++ + "",
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
                sach.getSoLuong(),};
            model.addRow(rowData);

        }
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
                    ImageIcon scale = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    btnAvatar.setIcon(scale); // Sử dụng ảnh đã scale
                    btnAvatar.setToolTipText(imagePath);
                }
            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QLSach qls = new QLSach();
                dispose();
                qls.setVisible(true);
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenSach = txtTen.getText();
                String tacGia = txtTacGia.getText();
                Integer namXuatBan = Integer.parseInt(txtNXB.getText());
                Integer soTrang = Integer.parseInt(txtST.getText());
                Double giaTien = Double.parseDouble(txtGia.getText());
                String nhaXuatBan = txtNhaXB.getText();
                String rank = cboRank.getSelectedItem().toString();
                String trangthai = rdoCon.isSelected() ? "Còn" : "Hết";
                String MoTa = txtMoTa.getText();
                Integer SoNgayToiDa = Integer.parseInt(txtNMTD.getText());
                String avatar = btnAvatar.getToolTipText();

                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thêm ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    if (tenSach.isEmpty() || tacGia.isEmpty() || namXuatBan == null
                            || soTrang == null || giaTien == null
                            || nhaXuatBan.isEmpty() || MoTa.isEmpty() || SoNgayToiDa == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!rdoCon.isSelected() && !rdoHet.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (DaoSach.checkSachExistsNoID(tenSach)) {
                        JOptionPane.showMessageDialog(null, "Tên sách này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (namXuatBan < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Năm xuất bản không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (soTrang <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Số trang phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (giaTien < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Giá tiền không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (SoNgayToiDa <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Số ngày tối đa phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean result = DaoSach.createSach(tenSach, tacGia, namXuatBan, soTrang, giaTien, nhaXuatBan, rank, trangthai, MoTa, SoNgayToiDa, avatar);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhSachSach = DaoSach.getAll();
                        int STT = 1;
                        for (Sach sach : danhSachSach) {
                            Object[] rowData = {
                                STT++ + "",
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
                                sach.getSoLuong(),};
                            model.addRow(rowData);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {

                }
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = idSelected;
                String tenSach = txtTen.getText();
                String tacGia = txtTacGia.getText();
                Integer namXuatBan = Integer.parseInt(txtNXB.getText());
                Integer soTrang = Integer.parseInt(txtST.getText());
                Double giaTien = Double.parseDouble(txtGia.getText());
                String nhaXuatBan = txtNhaXB.getText();
                String rank = cboRank.getSelectedItem().toString();
                String trangthai = rdoCon.isSelected() ? "Còn" : "Hết";
                String MoTa = txtMoTa.getText();
                Integer SoNgayToiDa = Integer.parseInt(txtNMTD.getText());
                String avatar = btnAvatar.getToolTipText();
                boolean result = DaoSach.updateSach(id, tenSach, tacGia, namXuatBan, soTrang, giaTien, nhaXuatBan, rank, trangthai, MoTa, SoNgayToiDa, avatar);
                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn sửa ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    if (idSelected == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một sách để sửa.");
                        return;
                    }
                    if (tenSach.isEmpty() || tacGia.isEmpty() || namXuatBan == null
                            || soTrang == null || giaTien == null
                            || nhaXuatBan.isEmpty() || MoTa.isEmpty() || SoNgayToiDa == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!rdoCon.isSelected() && !rdoHet.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (DaoSach.checkSachExists(tenSach, id)) {
                        JOptionPane.showMessageDialog(null, "Tên sách này đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (namXuatBan < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Năm xuất bản không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (soTrang <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Số trang phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (giaTien < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Giá tiền không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        if (SoNgayToiDa <= 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Số ngày tối đa phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Sửa sách thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhSachSach = DaoSach.getAll();
                        int STT = 1;
                        for (Sach sach : danhSachSach) {
                            Object[] rowData = {
                                STT++ + "",
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
                                sach.getSoLuong(),};
                            model.addRow(rowData);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        );
        btnXoa.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                String trangthai = rdoCon.isSelected() ? "Còn" : "Hết";
                if (idSelected == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một sách để xoá.");
                    return;
                }
                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xoá ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    if (trangthai.equalsIgnoreCase("Còn")) {
                        JOptionPane.showMessageDialog(null, "Sách vẫn còn không thể xoá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int id = idSelected;
                    boolean result = DaoSach.deleteSach(id);

                    if (result) {
                        JOptionPane.showMessageDialog(null, "Xoá sách thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);

                        danhSachSach = DaoSach.getAll();
                        int STT = 1;
                        for (Sach sach : danhSachSach) {
                            Object[] rowData = {
                                STT++ + "",
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
                                sach.getSoLuong(),};
                            model.addRow(rowData);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Xoá sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        );
        table.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e
            ) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtId.setText(table.getValueAt(selectedRow, 0).toString());
                    txtTen.setText(table.getValueAt(selectedRow, 1).toString());
                    txtTacGia.setText(table.getValueAt(selectedRow, 2).toString());
                    txtNXB.setText(table.getValueAt(selectedRow, 3).toString());
                    txtST.setText(table.getValueAt(selectedRow, 4).toString());
                    txtGia.setText(table.getValueAt(selectedRow, 5).toString());
                    txtNhaXB.setText(table.getValueAt(selectedRow, 6).toString());
                    cboRank.setSelectedItem(table.getValueAt(selectedRow, 7).toString());
                    String trangThai = table.getValueAt(selectedRow, 8).toString();
                    if (trangThai.equals("Còn")) {
                        rdoCon.setSelected(true);
                    } else {
                        rdoHet.setSelected(true);
                    }
                    txtMoTa.setText(table.getValueAt(selectedRow, 9).toString());
                    txtNMTD.setText(table.getValueAt(selectedRow, 10).toString());
                    String imagePath = table.getValueAt(selectedRow, 11).toString();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        ImageIcon icon = new ImageIcon(imagePath);
                        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                        btnAvatar.setIcon(scaledIcon);
                        btnAvatar.setToolTipText(imagePath);
                    } else {
                        btnAvatar.setIcon(null);
                        btnAvatar.setToolTipText(null);
                    }
                    txtSL.setText(table.getValueAt(selectedRow, 12).toString());
                    idSelected = danhSachSach.get(selectedRow).getSachID();
                }
            }
        }
        );
    }

    public class TrangThaiTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                String trangThai = value.toString();
                if (trangThai.equalsIgnoreCase("Còn")) {
                    cell.setForeground(Color.GREEN);
                } else if (trangThai.equalsIgnoreCase("Hết")) {
                    cell.setForeground(Color.RED);
                } else {
                    cell.setForeground(Color.BLACK);
                }
            }

            return cell;
        }
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
            java.util.logging.Logger.getLogger(FormSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
