/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoDocGia;
import duantotnghiep.DAO.DaoLichSuMuon;
import duantotnghiep.DAO.PhieuMuonDao;
import duantotnghiep.DAO.PhieuPhatDao;
import duantotnghiep.DAO.PhieuTraDao;
import duantotnghiep.MODEL.DocGia;
import duantotnghiep.MODEL.PhieuMuon;
import duantotnghiep.MODEL.PhieuPhat;
import duantotnghiep.MODEL.PhieuTra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author MINHDUC
 */
public class QLPhieuTra extends javax.swing.JFrame {

    /**
     * Creates new form QLPhieuTra
     */
    private JTable phieuTraTable, phieuMuonTable;
    private DefaultTableModel phieuTraModel, phieuMuonModel;
    
    public QLPhieuTra() {
        initComponents();
        Form();
    }

public void Form() {
        setTitle("Hệ thống quản lý thư viện");
        setSize(1150, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
                getContentPane().setBackground(Color.decode("#FFF5EE"));

        JLabel lblTitle = new JLabel("PHIẾU TRẢ");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#301d0e"));
        lblTitle.setBounds(275, 50, 300, 30);
        add(lblTitle);
        JLabel lblIdCT = new JLabel("ID");
        lblIdCT.setSize(70, 30);
        lblIdCT.setLocation(205, 120);
        lblIdCT.setFont(new Font("Arial", Font.BOLD, 13));
        lblIdCT.setForeground(Color.decode("#301d0e"));
        add(lblIdCT);
        JTextField txtCT = new JTextField("");
        txtCT.setSize(150, 30);
        txtCT.setLocation(285, 120);
        add(txtCT);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        txtNgayTao.setText(today.format(formatter));
        JLabel lblNV = new JLabel("NhanVienID");
        lblNV.setSize(80, 30);
        lblNV.setLocation(205, 160);
        lblNV.setFont(new Font("Arial", Font.BOLD, 13));
        lblNV.setForeground(Color.decode("#301d0e"));
        add(lblNV);
        JTextField txtNV = new JTextField("");
        txtNV.setSize(150, 30);
        txtNV.setLocation(285, 160);
        add(txtNV);
        JLabel lblIDDG = new JLabel("ID_DocGia");
        lblIDDG.setSize(80, 30);
        lblIDDG.setLocation(205, 200);
        lblIDDG.setFont(new Font("Arial", Font.BOLD, 13));
        lblIDDG.setForeground(Color.decode("#301d0e"));
        add(lblIDDG);
        JTextField txtIDDG = new JTextField("");
        txtIDDG.setSize(150, 30);
        txtIDDG.setLocation(285, 200);
        add(txtIDDG);
        JLabel lblIDS = new JLabel("SachID");
        lblIDS.setSize(80, 30);
        lblIDS.setLocation(205, 240);
        lblIDS.setFont(new Font("Arial", Font.BOLD, 13));
        lblIDS.setForeground(Color.decode("#301d0e"));
        add(lblIDS);
        JTextField txtIDS = new JTextField("");
        txtIDS.setSize(150, 30);
        txtIDS.setLocation(285, 240);
        add(txtIDS);
        JLabel lblIDSCT = new JLabel("ChiTietID");
        lblIDSCT.setSize(80, 30);
        lblIDSCT.setLocation(205, 280);
        lblIDSCT.setFont(new Font("Arial", Font.BOLD, 13));
        lblIDSCT.setForeground(Color.decode("#301d0e"));
        add(lblIDSCT);
        JTextField txtIDSCT = new JTextField("");
        txtIDSCT.setSize(150, 30);
        txtIDSCT.setLocation(285, 280);
        add(txtIDSCT);
        JLabel lblNgayMuon = new JLabel("Ngày Mượn");
        lblNgayMuon.setSize(80, 30);
        lblNgayMuon.setLocation(205, 320);
        lblNgayMuon.setFont(new Font("Arial", Font.BOLD, 13));
        lblNgayMuon.setForeground(Color.decode("#301d0e"));
        add(lblNgayMuon);
        JTextField txtNgayMuon = new JTextField("");
        txtNgayMuon.setSize(150, 30);
        txtNgayMuon.setLocation(285, 320);
        add(txtNgayMuon);
        txtNgayMuon.setEnabled(false);
        JLabel lblHanTra = new JLabel("Hạn Trả");
        lblHanTra.setSize(80, 30);
        lblHanTra.setLocation(205, 360);
        lblHanTra.setFont(new Font("Arial", Font.BOLD, 13));
        lblHanTra.setForeground(Color.decode("#301d0e"));
        add(lblHanTra);
        JTextField txtHanTra = new JTextField("");
        txtHanTra.setSize(150, 30);
        txtHanTra.setLocation(285, 360);
        add(txtHanTra);
        JLabel lblNgayTra = new JLabel("Ngày Trả");
        lblNgayTra.setSize(80, 30);
        lblNgayTra.setLocation(205, 400);
        lblNgayTra.setFont(new Font("Arial", Font.BOLD, 13));
        lblNgayTra.setForeground(Color.decode("#301d0e"));
        add(lblNgayTra);
        JTextField txtNgayTra = new JTextField("");
        txtNgayTra.setSize(150, 30);
        txtNgayTra.setLocation(285, 400);
        add(txtNgayTra);
        txtNgayTra.setText(today.format(formatter));
        JLabel lblGC = new JLabel("Ghi Chú");
        lblGC.setSize(80, 30);
        lblGC.setLocation(205, 440);
        lblGC.setFont(new Font("Arial", Font.BOLD, 13));
        lblGC.setForeground(Color.decode("#301d0e"));
        add(lblGC);
        JTextField txtGC = new JTextField("");
        txtGC.setSize(150, 30);
        txtGC.setLocation(285, 440);
        add(txtGC);
        JLabel lblTrangThaiSach = new JLabel("Trạng Thái");
        lblTrangThaiSach.setSize(80, 30);
        lblTrangThaiSach.setLocation(205, 480);
        lblTrangThaiSach.setFont(new Font("Arial", Font.BOLD, 13));
        lblTrangThaiSach.setForeground(Color.decode("#301d0e"));
        add(lblTrangThaiSach);
        JRadioButton rdoTot = new JRadioButton("Tốt");
        rdoTot.setSize(50, 30);
        rdoTot.setLocation(285, 480);
        add(rdoTot);
        JRadioButton rdoXau = new JRadioButton("Xấu");
        rdoXau.setSize(150, 30);
        rdoXau.setLocation(385, 480);
        add(rdoXau);
        ButtonGroup group = new ButtonGroup();
        group.add(rdoXau);
        group.add(rdoTot);
        JButton btnTra = new JButton("Trả Sách");
        btnTra.setSize(100, 30);
        btnTra.setLocation(330, 520);
        add(btnTra);
        JButton btnHuy = new JButton("Huỷ");
        btnHuy.setSize(100, 30);
        btnHuy.setLocation(200, 520);
        add(btnHuy);
        JLabel lblBPM = new JLabel("2.Bảng Phiếu Mượn");
        lblBPM.setBounds(520, 275, 600, 20);
        lblBPM.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblBPM);
        phieuMuonModel = new DefaultTableModel();
        phieuMuonModel.setColumnIdentifiers(new Object[]{
            "STT", "ChiTietMuon_ID", "PhieuMuonID", "NhanVienID", "DocGiaID", "SachID", "ChiTietID", "NgayMuon", "SoNgayMuon", "NgayTra", "TrangThai", "GhiChu"
        });
        phieuMuonTable = new JTable(phieuMuonModel);
        JScrollPane scrollPanePM = new JScrollPane(phieuMuonTable);
        scrollPanePM.setBounds(500, 300, 600, 150);
        add(scrollPanePM);
        phieuMuonModel.setRowCount(0);
        ArrayList<PhieuMuon> phieuMuons = PhieuMuonDao.getAllChiTietMuon();
        int STT = 1;
        for (PhieuMuon phieuMuon : phieuMuons) {
            Object[] row = new Object[]{
                STT++ + "",
                phieuMuon.chiTietMuonID,
                phieuMuon.phieuMuonID,
                phieuMuon.nhanVienID,
                phieuMuon.docGiaID,
                phieuMuon.sachID,
                phieuMuon.chiTietID,
                phieuMuon.ngayMuon,
                phieuMuon.soNgayMuon,
                phieuMuon.ngayTra,
                phieuMuon.trangThai,
                phieuMuon.ghiChu
            };
            phieuMuonModel.addRow(row);
        }
        txtNV.setEnabled(false);
        txtIDDG.setEnabled(false);
        txtIDS.setEnabled(false);
        txtIDSCT.setEnabled(false);
        txtHanTra.setEnabled(false);
        txtNgayTra.setEnabled(false);
        phieuMuonTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selectedRow = phieuMuonTable.getSelectedRow();
                txtCT.setText(phieuMuonModel.getValueAt(selectedRow, 1).toString());
                txtNV.setText(phieuMuonModel.getValueAt(selectedRow, 3).toString());
                txtIDDG.setText(phieuMuonModel.getValueAt(selectedRow, 4).toString());
                txtIDS.setText(phieuMuonModel.getValueAt(selectedRow, 5).toString());
                txtIDSCT.setText(phieuMuonModel.getValueAt(selectedRow, 6).toString());
                txtNgayMuon.setText(phieuMuonModel.getValueAt(selectedRow, 7).toString());
                txtHanTra.setText(phieuMuonModel.getValueAt(selectedRow, 9).toString());
            }
        });
        phieuMuonTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = phieuMuonTable.getColumnModel();
        columnModel.getColumn(9).setPreferredWidth(120);
        JLabel lblBPT = new JLabel("1.Bảng Phiếu Trả");
        lblBPT.setBounds(520, 95, 600, 20);
        lblBPT.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblBPT);
        phieuTraModel = new DefaultTableModel();
        phieuTraModel.setColumnIdentifiers(new Object[]{
            "STT", "PhieuTraID", "ChiTietMuon_ID", "PhieuPhatID", "NhanVienID", "ID_DocGia", "SachID", "ChiTietID", "NgayTra", "GhiChu", "TrangThaiSach"
        });
        phieuTraTable = new JTable(phieuTraModel);
        JScrollPane scrollPane = new JScrollPane(phieuTraTable);
        scrollPane.setBounds(500, 120, 600, 150);
        add(scrollPane);
        phieuTraTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        phieuTraModel.setRowCount(0);
        ArrayList<PhieuTra> phieuTras = PhieuTraDao.getAllPhieuTra();
        int STTPT = 1;
        for (PhieuTra phieuTra : phieuTras) {
            phieuTraModel.addRow(new Object[]{
                STTPT++ + "",
                phieuTra.getPhieuTraID(),
                phieuTra.getChiTietMuonID(),
                phieuTra.getPhieuPhatID(),
                phieuTra.getNhanVienID(),
                phieuTra.getIdDocGia(),
                phieuTra.getSachID(),
                phieuTra.getChiTietID(),
                phieuTra.getNgayTra(),
                phieuTra.getGhiChu(),
                phieuTra.getTrangThaiSach()
            });
        }
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtCT.getText().trim().isEmpty()
                        || txtNV.getText().trim().isEmpty()
                        || txtIDDG.getText().trim().isEmpty()
                        || txtIDS.getText().trim().isEmpty()
                        || txtIDSCT.getText().trim().isEmpty()
                        || txtNgayTra.getText().trim().isEmpty()
                        || txtGC.getText().trim().isEmpty()
                        || (!rdoTot.isSelected() && !rdoXau.isSelected())) {

                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int selectedRow = phieuMuonTable.getSelectedRow();
                int chiTietMuonID = Integer.parseInt(txtCT.getText());
                int nhanVienID = Integer.parseInt(txtNV.getText());
                int idDocGia = Integer.parseInt(txtIDDG.getText());
                int sachID = Integer.parseInt(txtIDS.getText());
                int chiTietID = Integer.parseInt(txtIDSCT.getText());
                Date ngayMuon = Date.valueOf(txtNgayMuon.getText());
                String ghiChu = txtGC.getText();
                Date ngayTra = Date.valueOf(txtNgayTra.getText());
                String trangThaiSach = rdoTot.isSelected() ? "Tốt" : "Xấu";
                int results = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn trả ko", "Xac nhan", JOptionPane.YES_NO_OPTION);
                if (results == JOptionPane.YES_OPTION) {
                    if (txtCT == null || ghiChu.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!rdoTot.isSelected() && !rdoXau.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean isCreated = PhieuTraDao.createPhieuTra(chiTietMuonID, nhanVienID, idDocGia, sachID, chiTietID, ngayTra, ghiChu, trangThaiSach);

                    if (isCreated) {
                        DaoLichSuMuon.createLichSuMuon(sachID, chiTietID, idDocGia, ngayMuon, ngayTra, trangThaiSach);
                        JOptionPane.showMessageDialog(null, "Trả sách thành công!");
                        phieuMuonModel.setRowCount(0);
                        ArrayList<PhieuMuon> phieuMuons = PhieuMuonDao.getAllChiTietMuon();
                        int STT = 1;
                        for (PhieuMuon phieuMuon : phieuMuons) {
                            Object[] row = new Object[]{
                                STT++ + "",
                                phieuMuon.chiTietMuonID,
                                phieuMuon.phieuMuonID,
                                phieuMuon.nhanVienID,
                                phieuMuon.docGiaID,
                                phieuMuon.sachID,
                                phieuMuon.chiTietID,
                                phieuMuon.ngayMuon,
                                phieuMuon.soNgayMuon,
                                phieuMuon.ngayTra,
                                phieuMuon.trangThai,
                                phieuMuon.ghiChu
                            };
                            phieuMuonModel.addRow(row);
                            phieuTraModel.setRowCount(0);
                            ArrayList<PhieuTra> phieuTras = PhieuTraDao.getAllPhieuTra();
                            int STTPT = 1;
                            for (PhieuTra phieuTra : phieuTras) {
                                phieuTraModel.addRow(new Object[]{
                                    STTPT++ + "",
                                    phieuTra.getPhieuTraID(),
                                    phieuTra.getChiTietMuonID(),
                                    phieuTra.getPhieuPhatID(),
                                    phieuTra.getNhanVienID(),
                                    phieuTra.getIdDocGia(),
                                    phieuTra.getSachID(),
                                    phieuTra.getChiTietID(),
                                    phieuTra.getNgayTra(),
                                    phieuTra.getGhiChu(),
                                    phieuTra.getTrangThaiSach()
                                });
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi khi trả sách!");
                        return;
                    }
                } else {
                    return;
                }
            }
        });
        phieuTraTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = phieuTraTable.getSelectedRow();
                if (selectedRow != -1) {
                    String idDocGia = phieuTraTable.getValueAt(selectedRow, 5).toString();
                    String phieuTraID = phieuTraTable.getValueAt(selectedRow, 1).toString();
                    String trangThai = phieuTraTable.getValueAt(selectedRow, 10).toString().trim();

                    DocGia docGia = DaoDocGia.getDocGiaByID(Integer.parseInt(idDocGia));
                    PhieuTra phieuTra = PhieuTraDao.getPhieuTraByID(Integer.parseInt(phieuTraID));
                    PhieuPhat phieuPhat = PhieuPhatDao.getPhieuPhatByPhieuTraID(Integer.parseInt(phieuTraID));

                    if (trangThai.equalsIgnoreCase("Tốt")) {
                        JOptionPane.showMessageDialog(null, "Không cần xử lý phạt.");
                        return;
                    }

                    if (phieuPhat != null
                            && (phieuPhat.getTrangThai().equalsIgnoreCase("Đã thanh toán")
                            || phieuPhat.getTrangThai().equalsIgnoreCase("Chưa thanh toán"))) {

                        JOptionPane.showMessageDialog(null, "Phiếu phạt đã tồn tại.");
                        return;
                    }

                    if (docGia != null) {
                        dispose();
                        QuanLyPhieuPhat qldg = new QuanLyPhieuPhat();
                        qldg.setDocGia(
                                docGia.getHoTen(),
                                docGia.getSdt(),
                                docGia.getCccd(),
                                phieuTra.getGhiChu(),
                                phieuTra.getPhieuTraID()
                        );
                        qldg.setVisible(true);
                    }
                }
            }
        }
        );
        txtCT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtCT.getText().trim());
                    PhieuMuon pm = PhieuTraDao.getChiTietMuonByID(id);
                    if (pm != null) {
                        txtNV.setText(String.valueOf(pm.getNhanVienID()));
                        txtIDDG.setText(String.valueOf(pm.getDocGiaID()));
                        txtIDS.setText(String.valueOf(pm.getSachID()));
                        txtIDSCT.setText(String.valueOf(pm.getChiTietID()));
                        txtNgayMuon.setText(String.valueOf(pm.getNgayMuon()));
                        txtHanTra.setText(String.valueOf(pm.getNgayTra()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy Chi Tiết Mượn với ID này.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID phải là số!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi tìm chi tiết mượn!");
                }
            }
        }
        );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
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
            java.util.logging.Logger.getLogger(QLPhieuTra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLPhieuTra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLPhieuTra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLPhieuTra.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLPhieuTra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
