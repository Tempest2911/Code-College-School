/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoDocGia;
import duantotnghiep.DAO.PhieuPhatDao;
import duantotnghiep.MODEL.DocGiaInFo;
import duantotnghiep.MODEL.PhieuPhat;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duck
 */
public class QuanLyPhieuPhat extends javax.swing.JFrame {

    private JButton btnThem, btnUpdate, btnTim, btnOut, btnTT;
    private JComboBox<String> cboTen, cboSDT, cboCCCD;
    private JTextField txtNgayLap, txtLyDo, txtSoTienPhat, txtTim, txtPhieuTraID;
    private JRadioButton rdoChuaThanhToan, rdoDaThanhToan;
    private JTable phieuPhatTable;
    private DefaultTableModel phieuPhatModel;

    /**
     * Creates new form QuanLyPhieuPhat
     */
    public QuanLyPhieuPhat() {
        initComponents();
        setTitle("Quản lý Phiếu Phạt");
        setSize(1040, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#FFF5EE"));

        formPhieuPhat();

        phieuPhatModel = new DefaultTableModel();
        phieuPhatModel.setColumnIdentifiers(new Object[]{
            "ID Phiếu Phạt", "Họ Tên", "SĐT", "CCCD", "Ngày Lập", "Lý Do", "Số Tiền Phạt", "Trạng Thái"
        });
        phieuPhatTable = new JTable(phieuPhatModel);
        JScrollPane scrollPane = new JScrollPane(phieuPhatTable);
        scrollPane.setBounds(0, 400, getWidth(), getHeight());
        add(scrollPane);

        loadTableData();
        MouseClick();
        loadComboBoxData();
        cbAction();
        themPhieuPhat();
        suaPhieuPhat();
        timKiemPhieuPhat();
        setVisible(true);
        cboTen.setEnabled(false);
        cboSDT.setEnabled(false);
        cboCCCD.setEnabled(false);
    }

    private void formPhieuPhat() {
        JLabel lblTitle = new JLabel("GIẤY QUẢN LÝ PHIẾU PHẠT", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.decode("#301d0e"));
        lblTitle.setBounds(300, 20, 500, 40);
        add(lblTitle);

        cboTen = addComboBox("Tên:", 300, 70);
        cboSDT = addComboBox("Số điện thoại:", 300, 105);
        cboCCCD = addComboBox("CCCD:", 300, 140);
        txtPhieuTraID = addInputField("PhieuTraID", 300, 175);
        txtPhieuTraID.setEnabled(false);
        txtNgayLap = addInputField("Ngày lập:", 300, 210);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtNgayLap.setText(today.format(formatter));
        txtLyDo = addInputField("Lý do:", 300, 245);
        txtSoTienPhat = addInputField("Số tiền phạt:", 300, 280);
        txtNgayLap.setEnabled(false);
        trangThai();
        addButtons();
        btnThem.setEnabled(false);

        
        
        txtPhieuTraID.getDocument().addDocumentListener(new DocumentListener() {
            private void checkInput() {
                String idText = txtPhieuTraID.getText().trim();
                btnThem.setEnabled(!idText.isEmpty()); // Chỉ enable nếu có nội dung
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInput();
            }
        });

    }

    private JComboBox<String> addSearchableComboBox(String label, int x, int y, String type) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(x, y, 120, 30);
        add(lbl);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(true);
        comboBox.setBounds(x + 130, y, 300, 30);
        add(comboBox);

        JTextField txtField = (JTextField) comboBox.getEditor().getEditorComponent();

        // Load dữ liệu ban đầu cho ComboBox
        ArrayList<String> dataList;
        if (type.equals("ten")) {
            dataList = DaoDocGia.getTenDocGia("");
        } else if (type.equals("sdt")) {
            dataList = PhieuPhatDao.getSDTDocGia("");
        } else {
            dataList = PhieuPhatDao.getCCCDDocGia("");
        }

        for (String data : dataList) {
            comboBox.addItem(data);
        }
        comboBox.setSelectedItem(null);

        // Lắng nghe sự kiện nhập liệu để gợi ý dữ liệu
        txtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String input = txtField.getText().trim();
                comboBox.removeAllItems();

                ArrayList<String> updatedList;
                if (type.equals("ten")) {
                    updatedList = PhieuPhatDao.getTenDocGia(input);
                } else if (type.equals("sdt")) {
                    updatedList = PhieuPhatDao.getSDTDocGia(input);
                } else {
                    updatedList = PhieuPhatDao.getCCCDDocGia(input);
                }

                for (String data : updatedList) {
                    comboBox.addItem(data);
                }

                comboBox.setSelectedItem(null);
                txtField.setText(input);
                txtField.setCaretPosition(input.length());

                if (comboBox.getItemCount() > 0) {
                    comboBox.setPopupVisible(true);
                }
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedData = (String) comboBox.getSelectedItem();
                if (selectedData != null && !selectedData.isEmpty()) {
                    PhieuPhatDao.layThongTinTheoSDT(
                            type.equals("sdt") ? selectedData : null,
                            type.equals("ten") ? selectedData : null,
                            type.equals("cccd") ? selectedData : null
                    );
                } else {
                    return;
                }
            }
        });

        return comboBox;
    }

    private JComboBox<String> addComboBox(String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(x, y, 120, 30);
        add(lbl);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(x + 130, y, 300, 30);
        comboBox.setEditable(true);
        add(comboBox);
        return comboBox;
    }

    private void loadComboBoxData() {
        ArrayList<DocGiaInFo> danhsach = PhieuPhatDao.getAl();
        for (DocGiaInFo docGiaInFo : danhsach) {
            cboTen.addItem(docGiaInFo.getHoTen());
            cboSDT.addItem(docGiaInFo.getSdt());
            cboCCCD.addItem(docGiaInFo.getCccd());
        }
    }

    private void cbAction() {
        cboTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) cboTen.getSelectedItem();
                String cccd = (String) cboCCCD.getSelectedItem();
                String sdt = (String) cboSDT.getSelectedItem();
                PhieuPhat phieuphat = PhieuPhatDao.timThongTin(selectedName, sdt, cccd);
                if (phieuphat != null) {
                    cboSDT.setSelectedItem(phieuphat.getSdt());
                    cboCCCD.setSelectedItem(phieuphat.getCccd());
                    // Cập nhật các trường khác nếu cần
                }
            }
        });
        cboTen.setSelectedItem(null);
        cboSDT.setSelectedItem(null);
        cboCCCD.setSelectedItem(null);
    }

    private void trangThai() {
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setBounds(300, 320, 100, 20);
        add(lblTrangThai);

        rdoChuaThanhToan = new JRadioButton("Chưa thanh toán");
        rdoDaThanhToan = new JRadioButton("Đã thanh toán");
        ButtonGroup groupTrangThai = new ButtonGroup();
        groupTrangThai.add(rdoChuaThanhToan);
        groupTrangThai.add(rdoDaThanhToan);
        rdoChuaThanhToan.setEnabled(false);
        rdoDaThanhToan.setEnabled(false);

        JPanel panelTrangThai = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 0));
        panelTrangThai.setBounds(380, 320, 300, 30);
        panelTrangThai.add(rdoChuaThanhToan);
        panelTrangThai.add(rdoDaThanhToan);
        add(panelTrangThai);
        panelTrangThai.setBackground(Color.decode("#FFF5EE"));
    }

    private void addButtons() {
        btnThem = button("Thêm", 30, 350);
        btnTim = button("Tìm", 490, 350);
        btnUpdate = button("Sửa", 630, 350);
        btnOut = button("Thoát", 760, 350);
        btnTT = button("Thanh Toán", 890, 350);
        txtTim = new JTextField();
        txtTim.setBounds(180, 350, 300, 40);
        add(txtTim);
        btnOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnTT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ThanhToan tt = new ThanhToan();
                tt.setVisible(true);
            }
        });
    }

    private JButton button(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 40);
        add(button);
        return button;
    }

    private void loadTableData() {
        phieuPhatModel.setRowCount(0);
        ArrayList<PhieuPhat> phieuPhats = PhieuPhatDao.getAll();
        for (PhieuPhat phieuPhat : phieuPhats) {
            phieuPhatModel.addRow(new Object[]{
                phieuPhat.getPhieuPhatID(),
                phieuPhat.getHoTen(),
                phieuPhat.getSdt(),
                phieuPhat.getCccd(),
                phieuPhat.getNgayLam(),
                phieuPhat.getLyDo(),
                phieuPhat.getSoTienPhat(),
                phieuPhat.getTrangThai(),});
        }
    }

    private JTextField addInputField(String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setBounds(x, y, 120, 30);
        add(lbl);

        JTextField textField = new JTextField();
        textField.setBounds(x + 130, y, 300, 30);
        add(textField);
        return textField;
    }

    private void MouseClick() {
        phieuPhatTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selectedRow = phieuPhatTable.getSelectedRow();
                cboTen.setSelectedItem(phieuPhatModel.getValueAt(selectedRow, 1).toString());
                cboSDT.setSelectedItem(phieuPhatModel.getValueAt(selectedRow, 2).toString());
                cboCCCD.setSelectedItem(phieuPhatModel.getValueAt(selectedRow, 3).toString());
                txtNgayLap.setText(phieuPhatModel.getValueAt(selectedRow, 4).toString());
                txtLyDo.setText(phieuPhatModel.getValueAt(selectedRow, 5).toString());
                txtSoTienPhat.setText(phieuPhatModel.getValueAt(selectedRow, 6).toString());
                String trangThai = phieuPhatModel.getValueAt(selectedRow, 7).toString();
                rdoChuaThanhToan.setSelected(trangThai.equals("Chưa thanh toán"));
                rdoDaThanhToan.setSelected(trangThai.equals("Đã thanh toán"));
            }
        });
    }

    private void themPhieuPhat() {
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenDocGia = cboTen.getSelectedItem().toString();
                String sdt = cboSDT.getSelectedItem().toString();
                String cccd = cboCCCD.getSelectedItem().toString();
                String ngayTao = txtNgayLap.getText();
                String lyDo = txtLyDo.getText();
                Integer idPhieuTra = Integer.parseInt(txtPhieuTraID.getText());
                String trangThai = rdoChuaThanhToan.isSelected() ? "Chưa thanh toán" : "Đã thanh toán";
                String soTienPhatStr = txtSoTienPhat.getText();
                if (!tenDocGia.isEmpty() && !sdt.isEmpty() && !cccd.isEmpty() && !lyDo.isEmpty() && !soTienPhatStr.isEmpty()) {
                    try {
                        double soTienPhat = Double.parseDouble(soTienPhatStr);
                        if (soTienPhat > 0) {
                            Integer idDocGia = PhieuPhatDao.layIdDocGia(tenDocGia, cccd, sdt);
                            if (idDocGia != null) {
                                Integer idPhieuPhat = PhieuPhatDao.themPhieuPhat(idDocGia, lyDo, soTienPhat);
                                if (idPhieuPhat != null) {
                                    loadTableData();
                                    clearForm();
                                    dispose();
                                    JOptionPane.showMessageDialog(rootPane, "Thêm phiếu phạt thành công!");
                                    boolean daCapNhat = PhieuPhatDao.capNhatPhieuPhatIDTrongPhieuTra(idPhieuTra, idPhieuPhat);
                                    ThanhToan(idPhieuPhat + "", tenDocGia, ngayTao, lyDo, soTienPhat + "");
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Thêm phiếu phạt không thành công!");
                                    return;
                                }

                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Không tìm thấy độc giả!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Số tiền phạt phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Số tiền phạt phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void suaPhieuPhat() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = phieuPhatTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn phiếu phạt cần sửa");
                    return;
                }

                int idPhieu = (int) phieuPhatTable.getValueAt(selectedRow, 0);
                String lyDo = txtLyDo.getText();
                double soTienPhat = Double.parseDouble(txtSoTienPhat.getText());
                String trangThai = rdoChuaThanhToan.isSelected() ? "Chưa thanh toán" : "Đã thanh toán";

                if (PhieuPhatDao.suaPhieuPhat(idPhieu, lyDo, soTienPhat, trangThai)) {
                    loadTableData();
                    clearForm();
                    JOptionPane.showMessageDialog(rootPane, "Sửa phiếu phạt thành công");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Sửa phiếu phạt không thành công");
                }
            }
        });
    }

    private void timKiemPhieuPhat() {
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = txtTim.getText().trim();

                if (search.isEmpty()) {
                    loadTableData();
                    return;
                }

                ArrayList<PhieuPhat> danhSach = PhieuPhatDao.timPhieuPhatTheoSDT(search);
                DefaultTableModel model = (DefaultTableModel) phieuPhatTable.getModel();
                model.setRowCount(0);  // Xóa dữ liệu cũ trong bảng

                if (danhSach.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu phạt với số điện thoại này.");
                } else {
                    for (PhieuPhat phieuPhat : danhSach) {
                        model.addRow(new Object[]{
                            phieuPhat.getPhieuPhatID(),
                            phieuPhat.getHoTen(),
                            phieuPhat.getSdt(),
                            phieuPhat.getCccd(),
                            phieuPhat.getNgayLam(),
                            phieuPhat.getLyDo(),
                            phieuPhat.getSoTienPhat(),
                            phieuPhat.getTrangThai(),
                            phieuPhat.getAnh()
                        });
                    }
                }
            }
        });

    }

    private void clearForm() {
        cboCCCD.setSelectedItem("");
        cboTen.setSelectedItem("");
        cboSDT.setSelectedItem("");
        txtNgayLap.setText("");
        txtLyDo.setText("");
        txtSoTienPhat.setText("");
        rdoChuaThanhToan.setSelected(false);
        rdoDaThanhToan.setSelected(false);
    }

    public void ThanhToan(String idPhieuPhat, String idDocGia, String ngayTao, String lyDo, String soTienPhat) {
        JFrame frameTT = new JFrame();
        frameTT.setTitle("Hệ thống quản lý thư viện");
        frameTT.setSize(650, 400);
        frameTT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTT.setLocationRelativeTo(null);
        frameTT.setLayout(null);
        JLabel lblTitle = new JLabel("Thanh Toán");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(255, 20, 300, 30);
        frameTT.add(lblTitle);
        JLabel lblId = new JLabel("Id Phiếu Phạt");
        lblId.setSize(80, 30);
        lblId.setLocation(205, 80);
        frameTT.add(lblId);
        JTextField txtId = new JTextField(idPhieuPhat);
        txtId.setSize(150, 30);
        txtId.setLocation(285, 80);
        frameTT.add(txtId);
        JLabel lblIdDocGia = new JLabel("Id Độc Giả");
        lblIdDocGia.setSize(80, 30);
        lblIdDocGia.setLocation(205, 120);
        frameTT.add(lblIdDocGia);
        JTextField txtDocGia = new JTextField(idDocGia);
        txtDocGia.setSize(150, 30);
        txtDocGia.setLocation(285, 120);
        frameTT.add(txtDocGia);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        JLabel lblINgayTao = new JLabel("Ngày Tạo");
        lblINgayTao.setSize(80, 30);
        lblINgayTao.setLocation(205, 160);
        frameTT.add(lblINgayTao);
        JTextField txtNgayTao = new JTextField(ngayTao);
        txtNgayTao.setSize(150, 30);
        txtNgayTao.setLocation(285, 160);
        frameTT.add(txtNgayTao);
        txtNgayTao.setText(today.format(formatter));
        JLabel lblLyDo = new JLabel("Lý do");
        lblLyDo.setSize(80, 30);
        lblLyDo.setLocation(205, 200);
        frameTT.add(lblLyDo);
        JTextField txtLyDo = new JTextField(lyDo);
        txtLyDo.setSize(150, 30);
        txtLyDo.setLocation(285, 200);
        frameTT.add(txtLyDo);
        txtNgayTao.setText(today.format(formatter));
        JLabel lblSTP = new JLabel("Số Tiền Phạt");
        lblSTP.setSize(80, 30);
        lblSTP.setLocation(205, 240);
        frameTT.add(lblSTP);
        JTextField txtSTP = new JTextField(soTienPhat + " VND");
        txtSTP.setSize(150, 30);
        txtSTP.setLocation(285, 240);
        frameTT.add(txtSTP);
        txtId.setEnabled(false);
        txtDocGia.setEnabled(false);
        txtLyDo.setEnabled(false);
        txtNgayTao.setEnabled(false);
        txtSTP.setEnabled(false);
        JButton btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setSize(100, 30);
        btnThanhToan.setLocation(330, 280);
        frameTT.add(btnThanhToan);
        JButton btnHuy = new JButton("Huỷ");
        btnHuy.setSize(100, 30);
        btnHuy.setLocation(200, 280);
        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = phieuPhatTable.getSelectedRow();

                String id = txtId.getText();
                int confirm = JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = PhieuPhatDao.updateTrangThai(id, "Đã thanh toán");
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                        phieuPhatModel.setRowCount(0);
                        frameTT.dispose();
                        QuanLyPhieuPhat qlpp = new QuanLyPhieuPhat();
                        qlpp.setVisible(true);
                        ArrayList<PhieuPhat> phieuPhats = PhieuPhatDao.getAllPP();
                        for (PhieuPhat phieuPhat : phieuPhats) {
                            phieuPhatModel.addRow(new Object[]{
                                phieuPhat.getPhieuPhatID(),
                                phieuPhat.getIdDocGia(),
                                phieuPhat.getNgayLam(),
                                phieuPhat.getLyDo(),
                                phieuPhat.getSoTienPhat(),
                                phieuPhat.getTrangThai(),});
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Thanh toán thất bại!");
                        return;
                    }
                }
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameTT.dispose();
            }
        });
        frameTT.add(btnHuy);
        frameTT.setVisible(true);
    }

    public void setDocGia(String ten, String sdt, String cccd, String lydo, Integer PhieuPhatID) {
        cboTen.setSelectedItem(ten);
        cboSDT.setSelectedItem(sdt);
        cboCCCD.setSelectedItem(cccd);
        txtLyDo.setText(lydo);
        txtPhieuTraID.setText(String.valueOf(PhieuPhatID));
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
            java.util.logging.Logger.getLogger(QuanLyPhieuPhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhieuPhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhieuPhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyPhieuPhat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyPhieuPhat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
