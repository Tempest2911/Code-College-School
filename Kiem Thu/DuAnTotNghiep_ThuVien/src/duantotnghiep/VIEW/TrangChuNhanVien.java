/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.MODEL.NhanVien;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author MINHDUC
 */
public class TrangChuNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form TrangChuNhanVien
     */
    private NhanVien nhanVien;

    public TrangChuNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        setTitle("Hệ Thống Quản Lý Thư Viện");
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height); // Đặt JFrame kích thước bằng màn hình
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Mở rộng cửa sổ tối đa

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sách", new JPanel());
        tabbedPane.addTab("Phiếu trả", new JPanel());
        tabbedPane.addTab("Phiếu phạt", new JPanel());
        tabbedPane.addTab("Lịch Sử Mượn", new JPanel());
        tabbedPane.addTab("Đăng Ký Độc Giả", new JPanel());
        tabbedPane.addTab("Quản Lý Độc Giả", new JPanel());
        tabbedPane.addTab("Thông tin tài khoản", new JPanel());

        tabbedPane.addTab("Đăng xuất", new JPanel());
        tabbedPane.setSelectedIndex(-1);
        add(tabbedPane, BorderLayout.NORTH);

ImageIcon logoIcon = new ImageIcon("C:\\Users\\Admin\\Documents\\SOF3032\\ThuVien\\DuAnTotNghiep\\Images\\logo-removebg-preview.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel, BorderLayout.CENTER);

        setVisible(true);

        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selected = tabbedPane.getSelectedIndex();
                String tabname = tabbedPane.getTitleAt(selected);
                if ("Sách".equals(tabname)) {
                    TrangChuDangKySach qls = new TrangChuDangKySach();
                    qls.setVisible(true);
                } else if ("Phiếu trả".equals(tabname)) {
                    QLPhieuTra qldg = new QLPhieuTra();
                    qldg.setVisible(true);
                } else if ("Phiếu phạt".equals(tabname)) {
                    QuanLyPhieuPhat qlpp = new QuanLyPhieuPhat();
                    qlpp.setVisible(true);
                } else if ("Lịch Sử Mượn".equals(tabname)) {
                    QLLichSuMuon qllsm = new QLLichSuMuon();
                    qllsm.setVisible(true);
                } else if ("Đăng Ký Độc Giả".equals(tabname)) {
                    DangKyDocGia dk = new DangKyDocGia();
                    dk.setVisible(true);
                } else if ("Quản Lý Độc Giả".equals(tabname)) {
                    QLDocGiaNhanVien qldgnv = new QLDocGiaNhanVien();
                    qldgnv.setVisible(true);
                } else if ("Thông tin tài khoản".equals(tabname)) {
                    QLThongTin thongTin = new QLThongTin(nhanVien);
                    thongTin.setVisible(true);
                } else if ("Đăng xuất".equals(tabname)) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn đăng xuất ?",
                            "Xác nhận đăng xuất",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        dispose(); // Đóng cửa sổ hiện tại
                        new Login().setVisible(true); // Mở lại màn hình đăng nhập nếu có
                    }
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
            java.util.logging.Logger.getLogger(TrangChuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        NhanVien nhanVien = new NhanVien();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuNhanVien(nhanVien).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
