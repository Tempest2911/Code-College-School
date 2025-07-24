/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoNhanVien;
import duantotnghiep.MODEL.NhanVien;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author MINHDUC
 */
public class QLNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form QLNhanVien
     */
    ArrayList<NhanVien> nhanViens;

    public QLNhanVien() {
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
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.white);

        JPanel sachPanel = new JPanel(new BorderLayout());
        JPanel docGiaPanel = new JPanel(new BorderLayout());
        JPanel thongKePanel = new JPanel(new BorderLayout());
        JPanel nhanVienPanel = new JPanel(new BorderLayout());
        sachPanel.setBackground(Color.white);
        docGiaPanel.setBackground(Color.white);
        thongKePanel.setBackground(Color.white);
        nhanVienPanel.setBackground(Color.white);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sách", new JPanel());
        tabbedPane.addTab("Độc giả", new JPanel());
        tabbedPane.addTab("Nhân viên", nhanVienPanel);
        tabbedPane.addTab("Phiếu mượn", new JPanel());
        tabbedPane.addTab("Phiếu trả", new JPanel());
        tabbedPane.addTab("Phiếu phạt", new JPanel());
        tabbedPane.addTab("Lịch Sử Mượn", new JPanel());
        tabbedPane.addTab("Đăng Ký Độc Giả", new JPanel());

        tabbedPane.setSelectedIndex(2);

        JTabbedPane tabThongKe = new JTabbedPane();
        tabThongKe.addTab("Thống kê phiếu phạt", new JScrollPane(new ThongKeTongTienPhatPanel()));
        tabThongKe.addTab("Thống kê Tỉ lệ trả sách đúng hạn và trễ hạn", new JScrollPane(new TyLeTraDungHanvaMuon()));
        tabThongKe.addTab("Thống kê số lượng sách đang mượn", new JScrollPane(new ThongKeSoSachDangMuon()));
        tabThongKe.addTab("Thống kê số lượng sách được mượn nhiều nhất", new JScrollPane(new ThongKeSachDuocMuonNhieuNhat()));
        tabThongKe.addTab("Thống kê số lượng sách mượn theo ngày", new JScrollPane(new ThongKeMuonSachPanel()));

        tabbedPane.addTab("Thống kê", tabThongKe);
        tabbedPane.addTab("Đăng xuất", new JPanel());
        add(tabbedPane, BorderLayout.NORTH);
        tabbedPane.setBackground(Color.white);
        JPanel filterPanel = new JPanel();
        nhanVienPanel.add(filterPanel, BorderLayout.NORTH);
        filterPanel.setBackground(Color.white);
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField searchField = new JTextField(20);
        ImageIcon icon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/KL.png"));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton csButton = new JButton("Chỉnh Sửa");
        filterPanel.add(csButton);
        JButton searchButton = new JButton(scaledIcon);
        searchPanel.setBackground(Color.white);
        filterPanel.add(searchField);
        filterPanel.add(searchButton);
        nhanVienPanel.add(searchPanel, BorderLayout.SOUTH);

        JPanel arrowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/out-removebg-preview.png"));
        Image arrowimage = arrowIcon.getImage();
        Image scaledarrowImage = arrowimage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledarrowIcon = new ImageIcon(scaledarrowImage);
        JButton arrowButton = new JButton(scaledarrowIcon);
        filterPanel.add(arrowButton);
        sachPanel.add(arrowPanel, BorderLayout.SOUTH);
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        arrowPanel.setBackground(Color.white);
        searchField.setText("Tìm kiếm...");
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Tìm kiếm...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().trim().isEmpty()) {
                    searchField.setText("Tìm kiếm...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        // Tạo bảng cho người dùng
        String[] docGiacolumnNames = {"STT", "ID Nhân Viên", "NameAC", "Pass", "Role", "Tên Nhân Viên", "CCCD", "Năm Sinh", "SĐT", "Email", "Giới Tính", "Avatar"};
        DefaultTableModel docGiamodel = new DefaultTableModel(docGiacolumnNames, 0);
        JTable docGiatable = new JTable(docGiamodel) {
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

// Set Renderer để hiển thị hình ảnh trong cột Avatar
        docGiatable.getColumnModel().getColumn(11).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setOpaque(true);

                // Giữ màu xen kẽ (trắng hoặc #FFF5EE) nếu không được chọn
                if (!isSelected) {
                    Color seashell = new Color(0xFF, 0xF5, 0xEE);
                    label.setBackground(row % 2 == 0 ? Color.WHITE : seashell);
                } else {
                    label.setBackground(table.getSelectionBackground());
                }

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

                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                return label;
            }
        });

// Các thiết lập bảng
        docGiatable.setFillsViewportHeight(true);
        docGiatable.setRowHeight(60); // Tăng chiều cao để ảnh rõ hơn
        docGiatable.setShowGrid(true);
        docGiatable.setGridColor(Color.LIGHT_GRAY);

        JScrollPane docGiascrollPane = new JScrollPane(docGiatable);
        docGiascrollPane.setPreferredSize(new Dimension(780, 350));
        nhanVienPanel.add(docGiascrollPane, BorderLayout.CENTER);
        docGiatable.setRowHeight(60);
        TableColumnModel columnModel = docGiatable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);

        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(9).setPreferredWidth(150);
        // Lấy dữ liệu từ Database và đổ vào bảng
        ArrayList<NhanVien> danhSachNhanVien = DaoNhanVien.getAll();
        int stt = 1;
        for (NhanVien nv : danhSachNhanVien) {
            Object[] rowData = {
                stt++ + "",
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
            docGiamodel.addRow(rowData);
        }
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
                } else if ("Phiếu phạt".equals(tabname)) {
                    QuanLyPP qlpp = new QuanLyPP();
                    qlpp.setVisible(true);
                    dispose();
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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (searchText.equals("Tìm kiếm...")) {
                    searchText = "";
                }
                ArrayList<NhanVien> nhanViens = DaoNhanVien.searchNhanVien(searchText);
                DefaultTableModel model = (DefaultTableModel) docGiatable.getModel();
                model.setRowCount(0);
                int STT = 1;
                for (NhanVien nv : nhanViens) {
                    model.addRow(new Object[]{
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
                    });
                }
            }

        });
        csButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FormNhanVien fnv = new FormNhanVien();
                fnv.setVisible(true);
            }
        });
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
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
