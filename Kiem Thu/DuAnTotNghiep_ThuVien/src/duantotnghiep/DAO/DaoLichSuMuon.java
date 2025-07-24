/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import static duantotnghiep.DAO.DaoDocGia.searchByName;
import duantotnghiep.MODEL.DocGia;
import duantotnghiep.MODEL.LichSuMuon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MINHDUC
 */
public class DaoLichSuMuon {

    static String searchByName = "SELECT L.*, D.HoTen, D.SDT, D.CCCD "
            + "FROM LichSuMuon L "
            + "JOIN DocGia D ON L.ID_DocGia = D.ID_DocGia WHERE HoTen LIKE ? OR CCCD LIKE ? OR SDT LIKE ?";

    public static ArrayList<LichSuMuon> getAllLichSuMuon() {
        ArrayList<LichSuMuon> list = new ArrayList<>();
        String getAll = "SELECT L.*, D.HoTen, D.SDT, D.CCCD "
                + "FROM LichSuMuon L "
                + "JOIN DocGia D ON L.ID_DocGia = D.ID_DocGia";

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAll)) {

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LichSuMuon lichSu = new LichSuMuon();
                lichSu.idLichSuMuon = rs.getInt("ID_LichSuMuon");
                lichSu.sachID = rs.getInt("SachID");
                lichSu.chiTietID = rs.getInt("ChiTietID");
                lichSu.idDocGia = rs.getInt("ID_DocGia");
                lichSu.HoTen = rs.getString("HoTen");
                lichSu.SDT = rs.getString("SDT");
                lichSu.CCCD = rs.getString("CCCD");
                lichSu.ngayMuon = rs.getDate("NgayMuon");
                lichSu.ngayTra = rs.getDate("NgayTra");
                lichSu.trangThaiSach = rs.getString("TrangThaiSach");

                list.add(lichSu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean createLichSuMuon(int sachID, int chiTietID, int idDocGia, Date ngayMuon, Date ngayTra, String trangThaiSach) {
        String createSQL = "INSERT INTO LichSuMuon (SachID, ChiTietID, ID_DocGia, NgayMuon, NgayTra, TrangThaiSach) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(createSQL)) {

            stm.setInt(1, sachID);
            stm.setInt(2, chiTietID);
            stm.setInt(3, idDocGia);
            stm.setDate(4, ngayMuon);
            stm.setDate(5, ngayTra);
            stm.setString(6, trangThaiSach);

            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<LichSuMuon> searchDocGia(String keyword) {
        ArrayList<LichSuMuon> lichSuMuons = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(searchByName)) {
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + keyword + "%");
            stm.setString(3, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LichSuMuon lichSu = new LichSuMuon();
                lichSu.idLichSuMuon = rs.getInt(1);
                lichSu.sachID = rs.getInt(2);
                lichSu.chiTietID = rs.getInt(3);
                lichSu.idDocGia = rs.getInt(4);
                lichSu.HoTen = rs.getString(8);
                lichSu.SDT = rs.getString(9);
                lichSu.CCCD = rs.getString(10);
                lichSu.ngayMuon = rs.getDate(5);
                lichSu.ngayTra = rs.getDate(6);
                lichSu.trangThaiSach = rs.getString(7);

                lichSuMuons.add(lichSu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lichSuMuons;
    }
}
