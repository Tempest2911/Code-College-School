/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duantotnghiep.VIEW.ThongKe;

import com.toedter.calendar.JDateChooser;
import duantotnghiep.DAO.CRUD;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thuyz
 */
public class ThongKeTongTienPhatPanel extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeTongTienPhatPanel
     */
    private JTable table;
    private DefaultTableModel tableModel;
    private JDateChooser dateChooserStart, dateChooserEnd;
    private JButton btnFilter;
    private JLabel lblTongTienPhat;
    JPanel filterPanel = new JPanel();
    public ThongKeTongTienPhatPanel() {
        initComponents();
        // Panel chọn điều kiện
        setLayout(new BorderLayout());
        
        // Panel chứa bộ lọc ngày
        
        filterPanel.add(new JLabel("Từ ngày:"));
        
        dateChooserStart = new JDateChooser();
        dateChooserStart.setDateFormatString("yyyy-MM-dd");
        filterPanel.add(dateChooserStart);
        
        filterPanel.add(new JLabel("Đến ngày:"));
        
        dateChooserEnd = new JDateChooser();
        dateChooserEnd.setDateFormatString("yyyy-MM-dd");
        filterPanel.add(dateChooserEnd);
        
        btnFilter = new JButton("Lọc dữ liệu");
        filterPanel.add(btnFilter);
        
        add(filterPanel, BorderLayout.NORTH);
        
        lblTongTienPhat = new JLabel("Tổng tiền phạt: 0 VND");
        lblTongTienPhat.setFont(new Font("Arial", Font.BOLD, 14));
        lblTongTienPhat.setForeground(Color.RED);


        // Thêm vào panel hoặc layout chính
        JPanel panelSummary = new JPanel();
        panelSummary.setLayout(new GridLayout(2, 1));
        filterPanel.add(lblTongTienPhat);

        // Thêm panel vào vị trí phù hợp
        add(panelSummary, BorderLayout.SOUTH);
        // Tạo bảng dữ liệu
        String[] columnNames = {"Mã phiếu", "Ngày lập", "Số tiền phạt", "Lý do"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        add(scrollPane, BorderLayout.CENTER);
        // Gắn sự kiện cho nút lọc dữ liệu
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startDate = dateChooserStart.getDate();
                Date endDate = dateChooserEnd.getDate();
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể sau ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
                updateTotal();
                loadTableData();
            }
        });
    }
private void updateTotal() {
    
    NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
    try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
        String sql = "SELECT COALESCE(SUM(SoTienPhat), 0) AS TongTien, COALESCE(AVG(SoTienPhat), 0) AS TrungBinh FROM PhieuPhat WHERE NgayLam BETWEEN ? AND ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, new java.sql.Date(dateChooserStart.getDate().getTime()));
        stmt.setDate(2, new java.sql.Date(dateChooserEnd.getDate().getTime()));

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String total = format.format(rs.getLong("TongTien")) + " VND";
            String avg = format.format(rs.getDouble("TrungBinh")) + " VND";

            lblTongTienPhat.setText("Tổng tiền phạt: " + total);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi kết nối database: " + e.getMessage(), "Lỗi nghiêm trọng", JOptionPane.ERROR_MESSAGE);
    }
}


private void loadTableData() {
    // Khởi tạo lại model với đúng số cột
    String[] columnNames = {"Mã phiếu", "Ngày lập", "Số tiền phạt", "Lý do"};
    tableModel = new DefaultTableModel(columnNames, 0);
    table.setModel(tableModel);

    try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
        String query = "SELECT PhieuPhatID, NgayLam, SoTienPhat, LyDo FROM PhieuPhat WHERE NgayLam BETWEEN ? AND ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setDate(1, new java.sql.Date(dateChooserStart.getDate().getTime()));
        pstmt.setDate(2, new java.sql.Date(dateChooserEnd.getDate().getTime()));

        ResultSet rs = pstmt.executeQuery();
        
        // Định dạng tiền tệ
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        
        while (rs.next()) {
            Object[] row = {
                rs.getInt("PhieuPhatID"),
                rs.getDate("NgayLam"),
                vnFormat.format(rs.getDouble("SoTienPhat")),
                rs.getString("LyDo")
            };
            tableModel.addRow(row);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, 
            "Lỗi database:\n" + ex.getMessage() + 
            "\nMã lỗi: " + ex.getErrorCode(), 
            "Lỗi nghiêm trọng", 
            JOptionPane.ERROR_MESSAGE);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
