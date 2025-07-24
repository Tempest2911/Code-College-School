/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package duantotnghiep.VIEW.ThongKe;

import com.toedter.calendar.JDateChooser;
import duantotnghiep.DAO.CRUD;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

/**
 *
 * @author thuyz
 */
public class ThongKeMuonSachPanel extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeMuonSachPanel
     */
    private JPanel chartPanel;
    private JDateChooser dateChooserStart, dateChooserEnd;
    private JButton btnDatePicker;
    public ThongKeMuonSachPanel() {
        initComponents();
        //initComponents();

        dateChooserStart = new JDateChooser();
        dateChooserEnd = new JDateChooser();
        btnDatePicker = new JButton("Chọn ngày");

        setSize(900, 600);

        setLayout(new BorderLayout());

        // Panel chọn loại thống kê
        JPanel topPanel = new JPanel();
        //topPanel.add(new JLabel("Thống kê:"));

        dateChooserStart = new JDateChooser();
        dateChooserEnd = new JDateChooser();
        btnDatePicker = new JButton("Chọn ngày");
        dateChooserStart.setPreferredSize(new Dimension(150, 25));
        dateChooserEnd.setPreferredSize(new Dimension(150, 25));
        topPanel.add(new JLabel("Từ ngày:"));
        topPanel.add(dateChooserStart);
        topPanel.add(new JLabel("Đến ngày:"));
        topPanel.add(dateChooserEnd);
        topPanel.add(btnDatePicker);
        add(topPanel, BorderLayout.NORTH);

        // Panel chứa biểu đồ
        chartPanel = new JPanel();
        add(chartPanel, BorderLayout.CENTER);

        // Sự kiện khi click nút xem thống kê
        btnDatePicker.addActionListener(new ActionListener() {
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
                // Nếu ngày hợp lệ, thực hiện thống kê
                loadChartByDate();
            }
        });

        setVisible(true);
    }

    private void loadChartByDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //format lay ngay thang nam
        String startDate = sdf.format(dateChooserStart.getDate()); //Ngay bat dau
        String endDate = sdf.format(dateChooserEnd.getDate());  //Ngay ket thuc

        List<String> ngay = new ArrayList<>(); //List luu  danh sach ngay
        List<Number> phieumuon = new ArrayList<>();  //List luu so phieu muon tuong ung tung ngay

        try {
            Connection conn = DriverManager.getConnection(CRUD.connectionUrl);

            String sql = "SELECT CONVERT(VARCHAR, NgayMuon, 23) AS ngay, COUNT(*) AS so_luong "
                    + "FROM ChiTietMuon "
                    + "WHERE NgayMuon BETWEEN ? AND ? "
                    + "GROUP BY CONVERT(VARCHAR, NgayMuon, 23) "
                    + "ORDER BY ngay";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ngay.add(rs.getString(1));   //Ngay muon
                phieumuon.add(rs.getInt(2));       //So luong phieu muon
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ngay.isEmpty() || phieumuon.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu trong khoảng thời gian này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Vẽ biểu đồ
        CategoryChart chart = new CategoryChartBuilder()
                .width(getWidth()).height(getHeight() - 200)
                .title("Thống kê theo số lần mượn sách")
                .xAxisTitle("Ngày")
                .yAxisTitle("Số lượt mượn")
                .build();

        chart.addSeries("Số lượt mượn", ngay, phieumuon); //gan ngay va so luong muon vo bieu do
        chart.getStyler().setLabelsVisible(true);
        chartPanel.removeAll();
        chartPanel.add(new XChartPanel<>(chart));
        chartPanel.validate();
        chartPanel.repaint();
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
