/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package duantotnghiep.VIEW;

import duantotnghiep.DAO.DaoDocGia;
import duantotnghiep.MODEL.DocGia;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author MINHDUC
 */
public class QLDocGiaNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form QLDocGia
     */
    public QLDocGiaNhanVien() {
        initComponents();
        Form();
    }

    private JPanel createInputGroup(String labelText, JComponent inputComponent) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false); // Giữ nền trong suốt nếu panel cha đã có màu

        JLabel label = new JLabel(labelText);
        label.setFont(label.getFont().deriveFont(Font.BOLD)); // IN ĐẬM
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.decode("#5a2e0b"));

        panel.add(label, BorderLayout.NORTH);
        panel.add(inputComponent, BorderLayout.CENTER);

        return panel;
    }

    String imagePath;

    public void Form() {
        setTitle("Hệ thống quản lý thư viện");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#FAF5EF"));

        JPanel sachPanel = new JPanel(new BorderLayout());
        JPanel docGiaPanel = new JPanel();
        sachPanel.setBackground(Color.decode("#FAF5EF"));
        docGiaPanel.setBackground(Color.decode("#FAF5EF"));
        docGiaPanel.setLayout(new BoxLayout(docGiaPanel, BoxLayout.Y_AXIS));  // Dùng BoxLayout để xếp các phần theo chiều dọc
        add(docGiaPanel, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JButton btnCapNhat = new JButton("Cập Nhật Thông Tin");
        filterPanel.setBackground(Color.decode("#FAF5EF"));
        filterPanel.add(btnCapNhat);
        btnCapNhat.setBackground(Color.decode("#4d2913"));
        btnCapNhat.setForeground(Color.WHITE);
        btnCapNhat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCapNhat.setFont(new Font("Arial", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCapNhat.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });
        

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField searchField = new JTextField(20);
        ImageIcon icon = new ImageIcon(getClass().getResource("/duantotnghiep/IMAGE/KL.png"));
        Image image = icon.getImage();
        searchPanel.setBackground(Color.decode("#FAF5EF"));
        Image scaledImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        searchField.setText("Tìm kiếm...");
        searchField.setForeground(Color.GRAY);
        docGiaPanel.add(filterPanel, BorderLayout.NORTH);
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
        JButton searchButton = new JButton(scaledIcon);
        filterPanel.add(searchField);
        filterPanel.add(searchButton);
        docGiaPanel.add(searchPanel, BorderLayout.SOUTH);

        JPanel arrowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        arrowPanel.setBackground(Color.decode("#FAF5EF"));
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
        // Các Trường nhập liệu
        JPanel mainInputPanel = new JPanel(new BorderLayout(10, 10)); // Panel chính
        JPanel inputFieldsPanel = new JPanel(new GridLayout(2, 3, 10, 10)); // 2 hàng, 3 cột
        mainInputPanel.setBackground(Color.decode("#FAF5EF"));
        inputFieldsPanel.setBackground(Color.decode("#FAF5EF"));
        inputFieldsPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Thêm padding xung quanh panel
        mainInputPanel.setPreferredSize(new Dimension(800, 150));
        // Các trường nhập liệu
        JTextField tfHoTen = new JTextField();
        JTextField tfSDT = new JTextField();
        JTextField tfCCCD = new JTextField();
        JTextField tfPoint = new JTextField();

        tfHoTen.setPreferredSize(new Dimension(200, 305)); // Chiều rộng 200, cao 25

        // ComboBox cho Rank
        JComboBox<String> cbRank = new JComboBox<>(new String[]{"Bronze", "Silver", "Gold", "Platinum", "Diamond"});
        // ComboBox cho TrangThai
        cbRank.setEnabled(false);
        JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Hoạt động", "Không Hoạt Động"});
        cbRank.setPreferredSize(new Dimension(200, 10));
        // Thêm các trường vào hàng 1
        JPanel row1Panel = new JPanel(new GridLayout(1, 3, 5, 5));
        row1Panel.setBackground(Color.decode("#FAF5EF"));
        row1Panel.setPreferredSize(new Dimension(800, 60)); // Giới hạn chiều cao
        row1Panel.add(createInputGroup("Họ Tên:", tfHoTen));
        row1Panel.add(createInputGroup("SĐT:", tfSDT));
        row1Panel.add(createInputGroup("CCCD:", tfCCCD));

        // Thêm các trường vào hàng 2
        JPanel row2Panel = new JPanel(new GridLayout(1, 3, 10, 10));
        row2Panel.setBackground(Color.decode("#FAF5EF"));
        row2Panel.setPreferredSize(new Dimension(800, 40)); // Giới hạn chiều cao
        row2Panel.add(createInputGroup("Point:", tfPoint));
        row2Panel.add(createInputGroup("Trạng Thái:", cbTrangThai));
        row2Panel.add(createInputGroup("Rank:", cbRank)); // Sử dụng ComboBox

        // Thêm các hàng vào panel chính
        inputFieldsPanel.add(row1Panel);
        inputFieldsPanel.add(row2Panel);

        mainInputPanel.add(inputFieldsPanel, BorderLayout.CENTER);
        mainInputPanel.setBackground(Color.decode("#FAF5EF"));
        // Thêm mainInputPanel vào 1 Panel de de kiem soat kich thước
        docGiaPanel.add(inputFieldsPanel, BorderLayout.CENTER);

        String[] docGiacolumnNames = {"STT", "Họ Tên", "SĐT", "CCCD", "Point Khách Hàng", "Trạng Thái", "Rank"};
        DefaultTableModel docGiamodel = new DefaultTableModel(docGiacolumnNames, 0);
        JTable docGiatable = new JTable(docGiamodel);

        JScrollPane docGiascrollPane = new JScrollPane(docGiatable);
        docGiascrollPane.setPreferredSize(new Dimension(100, 600));
        docGiaPanel.add(docGiascrollPane, BorderLayout.SOUTH);
        TableColumnModel columnModel = docGiatable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        docGiascrollPane.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
        docGiatable.setBackground(Color.WHITE); // Nền bảng trắng
        docGiatable.setForeground(Color.BLACK); // Chữ màu đen (nếu cần)

        docGiascrollPane.getViewport().setBackground(Color.WHITE); // Nền vùng cuộn cũng trắng

        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);

        docGiatable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

        // Lấy dữ liệu từ Database và đổ vào bảng
        ArrayList<DocGia> docGias = DaoDocGia.getAll();
        int docGiaSTT = 1;
        for (DocGia docGia : docGias) {
            Object[] rowData = {
                docGiaSTT++,
                docGia.hoTen,
                docGia.sdt,
                docGia.cccd,
                docGia.pointKhachHang,
                docGia.trangThai,
                docGia.rank,};
            docGiamodel.addRow(rowData);
        }

        docGiatable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = docGiatable.getSelectedRow();
                if (selectedRow != -1) {
                    tfHoTen.setText(docGiamodel.getValueAt(selectedRow, 1).toString());
                    tfSDT.setText(docGiamodel.getValueAt(selectedRow, 2).toString());
                    tfCCCD.setText(docGiamodel.getValueAt(selectedRow, 3).toString());
                    tfPoint.setText(docGiamodel.getValueAt(selectedRow, 4).toString());
                    cbTrangThai.setSelectedItem(docGiamodel.getValueAt(selectedRow, 5).toString());
                    cbRank.setSelectedItem(docGiamodel.getValueAt(selectedRow, 6).toString());
                }
            }
        });
        //Cập nhật
        btnCapNhat.addActionListener(e -> {
            int selectedRow = docGiatable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một độc giả từ bảng!");
                return;
            }
            try {
                int idDocGia = Integer.parseInt(docGiamodel.getValueAt(selectedRow, 0).toString());
                String hoTen = tfHoTen.getText();
                String sdt = tfSDT.getText();
                String cccd = tfCCCD.getText();
                int point = Integer.parseInt(tfPoint.getText());
                String trangThai = cbTrangThai.getSelectedItem().toString();
                String rank = cbRank.getSelectedItem().toString();
                if (tfHoTen.getText().trim().isEmpty()
                        || tfSDT.getText().trim().isEmpty()
                        || tfCCCD.getText().trim().isEmpty()
                        || tfPoint.getText().trim().isEmpty()
                        || cbTrangThai.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng để đầy đủ thông tin!");
                    return;
                }
                if (DaoDocGia.KiemTraTrungCCCD(cccd, idDocGia)) {
                    JOptionPane.showMessageDialog(rootPane, "CCCD Độc giả đã tồn tại trong hệ thống!");
                    return;
                }
                if (DaoDocGia.KiemTraTrungSDT(sdt, idDocGia)) {
                    JOptionPane.showMessageDialog(rootPane, "SDT đã tồn tại trong hệ thống!");
                    return;
                }
                DocGia docGia = new DocGia();
                docGia.setIdDocGia(idDocGia);
                docGia.setHoTen(hoTen);
                docGia.setSdt(sdt);
                docGia.setCccd(cccd);
                docGia.setPointKhachHang(point);
                docGia.setTrangThai(trangThai);
                docGia.setRank(rank);

                // Gọi phương thức cập nhật từ DAO
                boolean success = DaoDocGia.updateDocGia(docGia);

                if (success) {
                    // Cập nhật lại dữ liệu trong bảng
                    ArrayList<DocGia> docGiass = DaoDocGia.getAll(); // Lấy danh sách mới từ database
                    docGiamodel.setRowCount(0); // Xóa dữ liệu cũ
                    int stt = 1;
                    for (DocGia dg : docGiass) {
                        docGiamodel.addRow(new Object[]{
                            stt++,
                            dg.getHoTen(),
                            dg.getSdt(),
                            dg.getCccd(),
                            dg.getPointKhachHang(),
                            dg.getTrangThai(),
                            dg.getRank(),});
                    }
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi hệ thống!");
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (searchText.equals("Tìm kiếm...")) {
                    searchText = "";
                }
                ArrayList<DocGia> docGias = DaoDocGia.searchDocGia(searchText);
                DefaultTableModel model = (DefaultTableModel) docGiatable.getModel();
                model.setRowCount(0);
                int docGiaSTT = 1;
                for (DocGia docGia : docGias) {
                    Object[] rowData = {
                        docGiaSTT++,
                        docGia.hoTen,
                        docGia.sdt,
                        docGia.cccd,
                        docGia.pointKhachHang,
                        docGia.trangThai,
                        docGia.rank,};
                    docGiamodel.addRow(rowData);
                }
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
            java.util.logging.Logger.getLogger(QLDocGiaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDocGiaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDocGiaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDocGiaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLDocGiaNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
