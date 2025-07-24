/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.Sach1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuyz
 */
public class CRUD {

    public static String connectionUrl = "jdbc:sqlserver://TEMPEST:1433;databaseName=DuAnTotNghiep ;trustServerCertificate=true;user=sa;password=123456";

    public static Connection getConnect() throws Exception {
        return DriverManager.getConnection(connectionUrl);
    }

    public static Object executeQuery(String query, List<Object> params) {
        try {
            Connection conn = getConnect();
            PreparedStatement stm = conn.prepareStatement(query);
            // Truyền các params vào trong statement
            // số lượng dấu ? = số lượng phần tử của params
            for (int i = 0; i < params.size(); i++) { // truyền từng phần tử
                stm.setObject(i + 1, params.get(i)); // truyền giá trị
            }
            // Thực thi statement
            if (query.trim().toLowerCase().startsWith("select")) {
                ResultSet rs = stm.executeQuery();
                return rs;
            } else {
                int row = stm.executeUpdate(); // chạy và trả về số dòng bị tác động (row affected)
                System.out.println("Bạn đã " + query.split(" ")[0] + " " + row + " bản ghi");
                // query.split(" ") sẽ cắt chuỗi thành 1 mảng theo dấu cách " ", lúc này mỗi phần tử là 1 từ
                // query.split(" ")[0] sẽ chính là loại truy vấn
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
