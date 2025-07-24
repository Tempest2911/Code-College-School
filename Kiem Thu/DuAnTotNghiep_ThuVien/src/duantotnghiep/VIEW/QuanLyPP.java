/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.PhieuMuonDao;
import duantotnghiep.DAO.PhieuPhatDao;
import duantotnghiep.MODEL.PhieuMuon;
import duantotnghiep.MODEL.PhieuPhat;
import duantotnghiep.VIEW.ThongKe.ThongKeMuonSachPanel;
import duantotnghiep.VIEW.ThongKe.ThongKeTongTienPhatPanel;
import duantotnghiep.View.ThongKe.ThongKeSachDuocMuonNhieuNhat;
import duantotnghiep.View.ThongKe.ThongKeSoSachDangMuon;
import duantotnghiep.View.ThongKe.TyLeTraDungHanvaMuon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MINHDUC
 */
public class QuanLyPP extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyPP
     */
    private JTable phieuPhatTable;
    private DefaultTableModel phieuPhatModel;

    public QuanLyPP() {
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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.white);
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Sách", new JPanel());
        tabbedPane.addTab("Độc giả", new JPanel());
        tabbedPane.addTab("Nhân viên", new JPanel());
        tabbedPane.addTab("Phiếu mượn", new JPanel());
        tabbedPane.addTab("Phiếu trả", new JPanel());
        tabbedPane.addTab("Phiếu phạt", new JPanel());
        tabbedPane.addTab("Lịch Sử Mượn", new JPanel());
        tabbedPane.addTab("Đăng Ký Độc Giả", new JPanel());
        tabbedPane.setBackground(Color.white);
        tabbedPane.setSelectedIndex(5);

        JTabbedPane tabThongKe = new JTabbedPane();
        tabThongKe.addTab("Thống kê phiếu phạt", new JScrollPane(new ThongKeTongTienPhatPanel()));
        tabThongKe.addTab("Thống kê Tỉ lệ trả sách đúng hạn và trễ hạn", new JScrollPane(new TyLeTraDungHanvaMuon()));
        tabThongKe.addTab("Thống kê số lượng sách đang mượn", new JScrollPane(new ThongKeSoSachDangMuon()));
        tabThongKe.addTab("Thống kê số lượng sách được mượn nhiều nhất", new JScrollPane(new ThongKeSachDuocMuonNhieuNhat()));
        tabThongKe.addTab("Thống kê số lượng sách mượn theo ngày", new JScrollPane(new ThongKeMuonSachPanel()));

        tabThongKe.setBackground(Color.white);

        tabbedPane.addTab("Thống kê", tabThongKe);
        tabbedPane.addTab("Đăng xuất", new JPanel());

        JPanel arrowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/out-removebg-preview.png"));
        Image arrowimage = arrowIcon.getImage();
        Image scaledarrowImage = arrowimage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledarrowIcon = new ImageIcon(scaledarrowImage);
        JButton arrowButton = new JButton(scaledarrowIcon);
        arrowPanel.add(arrowButton);
        arrowPanel.setBackground(Color.white);
        arrowButton.addActionListener(e -> dispose());
        JButton btnCS = new JButton("Chỉnh Sửa");
        btnCS.setPreferredSize(new Dimension(100, 30));

        phieuPhatModel = new DefaultTableModel();
        phieuPhatModel.setColumnIdentifiers(new Object[]{
            "PhieuPhatID", "ID_DocGia", "NgayLam", "LyDo", "SoTienPhat", "TrangThai"
        });

// Tạo bảng với màu nền xen kẽ trắng và #FFF5EE
        phieuPhatTable = new JTable(phieuPhatModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);

                if (!isRowSelected(row)) {
                    Color seashell = new Color(0xFF, 0xF5, 0xEE); // Màu #FFF5EE
                    comp.setBackground(row % 2 == 0 ? Color.WHITE : seashell);
                } else {
                    comp.setBackground(getSelectionBackground());
                }

                return comp;
            }
        };

// Tuỳ chỉnh thêm cho đẹp
        phieuPhatTable.setFillsViewportHeight(true);
        phieuPhatTable.setShowGrid(true);
        phieuPhatTable.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(phieuPhatTable);
        scrollPane.setBounds(20, 350, 600, 150);
        add(scrollPane);

// Đổ dữ liệu vào bảng
        phieuPhatModel.setRowCount(0);
        ArrayList<PhieuPhat> phieuPhats = PhieuPhatDao.getAllPPN();
        for (PhieuPhat phieuPhat : phieuPhats) {
            phieuPhatModel.addRow(new Object[]{
                phieuPhat.getPhieuPhatID(),
                phieuPhat.getIdDocGia(),
                phieuPhat.getNgayLam(),
                phieuPhat.getLyDo(),
                phieuPhat.getSoTienPhat(),
                phieuPhat.getTrangThai(),});
        }

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(arrowButton);
        topPanel.setBackground(Color.white);
        topPanel.add(btnCS);
        btnCS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuanLyPhieuPhat qlpp = new QuanLyPhieuPhat();
                qlpp.setVisible(true);
            }
        });
        JPanel phieuMuonPanel = new JPanel(new BorderLayout());
        phieuMuonPanel.add(topPanel, BorderLayout.NORTH); // thêm nút phía trên bảng
        phieuMuonPanel.add(scrollPane, BorderLayout.CENTER);
        phieuMuonPanel.setBackground(Color.white);
        tabbedPane.setComponentAt(5, phieuMuonPanel);
        tabbedPane.setSelectedIndex(5);

        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selected = tabbedPane.getSelectedIndex();
                String tabname = tabbedPane.getTitleAt(selected);
                if ("Sách".equals(tabname)) {
                    QLSach qls = new QLSach();
                    qls.setVisible(true);
                    dispose();
                } else if ("Độc giả".equals(tabname)) {
                    QLDocGia qldg = new QLDocGia();
                    qldg.setVisible(true);
                    dispose();
                } else if ("Nhân viên".equals(tabname)) {
                    QLNhanVien qlnv = new QLNhanVien();
                    qlnv.setVisible(true);
                    dispose();
                } else if ("Phiếu mượn".equals(tabname)) {
                    QLPMuon qlpm = new QLPMuon();
                    qlpm.setVisible(true);
                    dispose();
                } else if ("Phiếu trả".equals(tabname)) {
                    QLPhieuTra qldg = new QLPhieuTra();
                    qldg.setVisible(true);
                } else if ("Lịch Sử Mượn".equals(tabname)) {
                    QLLichSuMuon qllsm = new QLLichSuMuon();
                    qllsm.setVisible(true);
                } else if ("Đăng Ký Độc Giả".equals(tabname)) {
                    DangKyDocGia dk = new DangKyDocGia();
                    dk.setVisible(true);
                } else if ("Đăng xuất".equals(tabname)) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn đăng xuất ?",
                            "Xác nhận đăng xuất",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        dispose();
                        new Login().setVisible(true);
                    }
                }
            }
        });
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(arrowPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
        setVisible(true);
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
            java.util.logging.Logger.getLogger(QuanLyPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyPP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
