/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.PhieuPhatDao;
import duantotnghiep.MODEL.PhieuPhat;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MINHDUC
 */
public class ThanhToan extends javax.swing.JFrame {

    /**
     * Creates new form ThanhToan
     */
    private JTable phieuPhatTable;
    private DefaultTableModel phieuPhatModel;

    public ThanhToan() {
        initComponents();
        Form();
    }

    public void Form() {
        setTitle("Hệ thống quản lý thư viện");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#FFF5EE"));

        JLabel lblTitle = new JLabel("THANH TOÁN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setBounds(215, 20, 300, 30);
        add(lblTitle);
        JLabel lblId = new JLabel("Id phiếu phạt");
        lblId.setBounds(120, 80, 120, 30);
        lblId.setFont(new Font("Arial", Font.PLAIN, 15));
        lblId.setForeground(Color.decode("#301d0e"));
        add(lblId);
        JTextField txtId = new JTextField("");
        txtId.setBounds(220, 80, 300, 30);
        add(txtId);
        JLabel lblIdDocGia = new JLabel("Id độc giả");
        lblIdDocGia.setBounds(120, 120, 120, 30);
        lblIdDocGia.setFont(new Font("Arial", Font.PLAIN, 15));
        lblIdDocGia.setForeground(Color.decode("#301d0e"));
        add(lblIdDocGia);
        JTextField txtDocGia = new JTextField("");
        txtDocGia.setBounds(220, 120, 300, 30);
        add(txtDocGia);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        JLabel lblINgayTao = new JLabel("Ngày tạo");
        lblINgayTao.setBounds(120, 160, 120, 30);
        lblINgayTao.setFont(new Font("Arial", Font.PLAIN, 15));
        lblINgayTao.setForeground(Color.decode("#301d0e"));
        add(lblINgayTao);
        JTextField txtNgayTao = new JTextField("");
        txtNgayTao.setBounds(220, 160, 300, 30);
        add(txtNgayTao);
        txtNgayTao.setText(today.format(formatter));
        JLabel lblLyDo = new JLabel("Lý do");
        lblLyDo.setBounds(120, 200, 120, 30);
        lblLyDo.setFont(new Font("Arial", Font.PLAIN, 15));
        lblLyDo.setForeground(Color.decode("#301d0e"));
        add(lblLyDo);
        JTextField txtLyDo = new JTextField("");
        txtLyDo.setBounds(220, 200, 300, 30);
        add(txtLyDo);
        txtNgayTao.setText(today.format(formatter));
        JLabel lblSTP = new JLabel("Số tiền phạt");
        lblSTP.setFont(new Font("Arial", Font.PLAIN, 15));
        lblSTP.setForeground(Color.decode("#301d0e"));
        lblSTP.setBounds(120, 240, 120, 30);
        add(lblSTP);
        JTextField txtSTP = new JTextField("");
        txtSTP.setBounds(220, 240, 300, 30);
        add(txtSTP);

        JButton btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setSize(150, 30);
        btnThanhToan.setLocation(330, 285);
        add(btnThanhToan);
        JButton btnHuy = new JButton("Huỷ");
        btnHuy.setSize(150, 30);
        btnHuy.setLocation(170, 285);
        add(btnHuy);

        btnThanhToan.setBackground(new Color(245, 245, 220));
        btnThanhToan.setForeground(new Color(101, 67, 33));
        btnThanhToan.setFont(new Font("Arial", Font.BOLD, 14));

        btnHuy.setBackground(new Color(245, 245, 220));
        btnHuy.setForeground(new Color(101, 67, 33));
        btnHuy.setFont(new Font("Arial", Font.BOLD, 14));

        txtDocGia.setEnabled(false);
        txtId.setEnabled(false);
        txtLyDo.setEnabled(false);
        txtNgayTao.setEnabled(false);
        txtSTP.setEnabled(false);
        phieuPhatModel = new DefaultTableModel();
        phieuPhatModel.setColumnIdentifiers(new Object[]{
            "PhieuPhatID", "ID_DocGia", "NgayLam", "LyDo", "SoTienPhat", "TrangThai"
        });
        phieuPhatModel = new DefaultTableModel();
        phieuPhatModel.setColumnIdentifiers(new Object[]{
            "PhieuPhatID", "ID_DocGia", "NgayLam", "LyDo", "SoTienPhat", "TrangThai"
        });

// Tạo JTable với custom màu dòng
        phieuPhatTable = new JTable(phieuPhatModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240)); // Trắng và xám nhạt
                } else {
                    c.setBackground(getSelectionBackground()); // Màu khi chọn dòng
                }
                return c;
            }
        };

// BẮT BUỘC: Xóa hiệu ứng mặc định từ LookAndFeel
        phieuPhatTable.setOpaque(false);
        phieuPhatTable.setBackground(Color.WHITE); // Nền trắng cho toàn bảng
        phieuPhatTable.setFillsViewportHeight(true); // Đảm bảo toàn vùng bảng được tô màu

// ScrollPane
        JScrollPane scrollPane = new JScrollPane(phieuPhatTable);
        scrollPane.setBounds(0, 330, getWidth(), getHeight());
        scrollPane.getViewport().setBackground(Color.WHITE); // Nền trắng khi không có dữ liệu

        add(scrollPane);

        phieuPhatModel.setRowCount(0);
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
        phieuPhatTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selectedRow = phieuPhatTable.getSelectedRow();
                txtId.setText(phieuPhatModel.getValueAt(selectedRow, 0).toString());
                txtDocGia.setText(phieuPhatModel.getValueAt(selectedRow, 1).toString());
                txtNgayTao.setText(phieuPhatModel.getValueAt(selectedRow, 2).toString());
                txtLyDo.setText(phieuPhatModel.getValueAt(selectedRow, 3).toString());
                txtSTP.setText(phieuPhatModel.getValueAt(selectedRow, 4).toString());
            }
        });
        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = phieuPhatTable.getSelectedRow();

                String id = txtId.getText();
                int confirm = JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một độc giả bị phạt.");
                        return;
                    }
                    boolean success = PhieuPhatDao.updateTrangThai(id, "Đã thanh toán");
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                        phieuPhatModel.setRowCount(0);
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
                dispose();
                QuanLyPhieuPhat qlpp = new QuanLyPhieuPhat();
                qlpp.setVisible(true);
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
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThanhToan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
