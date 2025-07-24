/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoDocGia;
import static duantotnghiep.DAO.DaoDocGia.searchDocGia;
import duantotnghiep.DAO.DaoLichSuMuon;
import duantotnghiep.MODEL.DocGia;
import duantotnghiep.MODEL.LichSuMuon;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author MINHDUC
 */
public class QLLichSuMuon extends javax.swing.JFrame {

    /**
     * Creates new form QLLichSuMuon
     */
    public QLLichSuMuon() {
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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#FAF5EF"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        // Nút quay về
        JPanel arrowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        arrowPanel.setBackground(Color.decode("#FAF5EF"));
// Nút quay lại
        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/out-removebg-preview.png"));
        Image arrowimage = arrowIcon.getImage();
        Image scaledarrowImage = arrowimage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledarrowIcon = new ImageIcon(scaledarrowImage);
        JButton arrowButton = new JButton(scaledarrowIcon);
        arrowPanel.add(arrowButton);

        JTextField searchField = new JTextField(20);
        ImageIcon icon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/KL.png"));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        searchField.setText("Tìm kiếm...");
        arrowPanel.add(searchField);
        JButton searchButton = new JButton(scaledIcon);
        arrowPanel.add(searchButton);
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

        // Đặt arrowPanel lên trên bảng
        mainPanel.add(arrowPanel, BorderLayout.NORTH);
        mainPanel.setBackground(Color.decode("#FAF5EF"));
        // Panel Lịch sử mượn
        JPanel lichSuPanel = new JPanel(new BorderLayout());
        lichSuPanel.setBackground(Color.decode("#FAF5EF"));
        // Bảng Lịch sử mượn
        String[] columnNames = {"STT", "Mã LS Mượn", "Mã Sách", "Chi Tiết ID", "Mã Độc Giả", "Họ Tên", "SĐT", "CCCD", "Ngày Mượn", "Ngày Trả", "Trạng Thái"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable tableLichSu = new JTable(model);

        tableLichSu.setBackground(Color.WHITE); // Nền bảng trắng
        tableLichSu.setForeground(Color.BLACK); // Chữ màu đen (tùy chọn)
        tableLichSu.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        ArrayList<LichSuMuon> lichSuList = DaoLichSuMuon.getAllLichSuMuon();
        int stt = 1;
        for (LichSuMuon ls : lichSuList) {
            Object[] row = {
                stt++,
                ls.idLichSuMuon,
                ls.sachID,
                ls.chiTietID,
                ls.idDocGia,
                ls.HoTen,
                ls.SDT,
                ls.CCCD,
                ls.ngayMuon,
                ls.ngayTra,
                ls.trangThaiSach
            };
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(tableLichSu);
        lichSuPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // Chuyển trực tiếp đến panel Lịch sử mượn
        mainPanel.add(lichSuPanel, BorderLayout.CENTER);

        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (searchText.equals("Tìm kiếm...")) {
                    searchText = "";
                }
                ArrayList<LichSuMuon> lichSuMuons = DaoLichSuMuon.searchDocGia(searchText);
                DefaultTableModel model = (DefaultTableModel) tableLichSu.getModel();
                model.setRowCount(0);
                int docGiaSTT = 1;
                for (LichSuMuon ls : lichSuMuons) {
                    Object[] row = {
                        docGiaSTT++,
                        ls.idLichSuMuon,
                        ls.sachID,
                        ls.chiTietID,
                        ls.idDocGia,
                        ls.HoTen,
                        ls.SDT,
                        ls.CCCD,
                        ls.ngayMuon,
                        ls.ngayTra,
                        ls.trangThaiSach
                    };
                    model.addRow(row);
                }
            }

        });
        // Thiết lập màn hình
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
            java.util.logging.Logger.getLogger(QLLichSuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLLichSuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLLichSuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLLichSuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLLichSuMuon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
