/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.ChiTietSach;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MINHDUC
 */
public class DaoChiTietSach {

    static String getAll = "SELECT * FROM SachChiTiet Where SachID=?";
    static String createSP = "INSERT INTO SachChiTiet (SachID, TrangThai, TinhTrangSach, NgayNhap) "
            + "VALUES (?, N'Đang có sẵn', N'Mới', ?)";
    static String updateSP = "UPDATE SachChiTiet SET SachID=? ,TrangThai = ?, TinhTrangSach = ?, NgayNhap = ? "
            + "WHERE chiTietID = ?";
    static String deleteSP = "DELETE FROM SachChiTiet WHERE chiTietID = ?";
    static String updateSachTrangThai = "UPDATE Sach SET TrangThai = CASE "
            + "WHEN EXISTS (SELECT 1 FROM SachChiTiet WHERE SachID = Sach.SachID) "
            + "THEN N'Còn' ELSE N'Hết' END WHERE SachID = ?";

    public static ArrayList<ChiTietSach> getBySachID(int sachID) {
        ArrayList<ChiTietSach> list = new ArrayList<>();
        String query = "SELECT * FROM SachChiTiet WHERE SachID = ?";

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, sachID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ChiTietSach chiTiet = new ChiTietSach();
                chiTiet.setChiTietID(rs.getInt("ChiTietID"));
                chiTiet.setSachID(rs.getInt("SachID"));
                chiTiet.setTrangThai(rs.getString("TrangThai"));
                chiTiet.setTinhTrangSach(rs.getString("TinhTrangSach"));
                chiTiet.setNgayNhap(rs.getDate("NgayNhap"));
                list.add(chiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean createSachChiTiet(int sachID, String trangThai, String tinhTrangSach,Date NgayNhap) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(createSP);PreparedStatement updateSach = con.prepareStatement(updateSachTrangThai)) {
            stm.setInt(1, sachID);
            stm.setDate(2, NgayNhap);
            int row = stm.executeUpdate();
            if (row > 0) {
                updateSach.setInt(1, sachID);
                updateSach.executeUpdate();
            }
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateSachChiTiet(int chiTietID, int sachID, String trangThai, String tinhTrangSach, Date ngaynhap) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(updateSP)) {
            stm.setInt(1, sachID);
            stm.setString(2, trangThai);
            stm.setString(3, tinhTrangSach);
            stm.setDate(4, ngaynhap);
            stm.setInt(5, chiTietID);

            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteSachChiTiet(int chiTietID,int sachID) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(deleteSP);PreparedStatement updateSach = con.prepareStatement(updateSachTrangThai)) {
            stm.setInt(1, chiTietID);
            int row = stm.executeUpdate();
            if (row > 0) {
                updateSach.setInt(1, sachID);
                updateSach.executeUpdate();
            }
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
