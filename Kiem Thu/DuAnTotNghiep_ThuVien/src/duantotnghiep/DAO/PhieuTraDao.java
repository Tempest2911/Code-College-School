/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.PhieuMuon;
import duantotnghiep.MODEL.PhieuTra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MINHDUC
 */
public class PhieuTraDao {

    static String getAll = "SELECT * FROM PhieuTra";

    public static ArrayList<PhieuTra> getAllPhieuTra() {
        ArrayList<PhieuTra> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAll)) {

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PhieuTra phieuTra = new PhieuTra();
                phieuTra.setPhieuTraID(rs.getInt("PhieuTraID"));
                phieuTra.setChiTietMuonID(rs.getInt("ChiTietMuon_ID"));
                phieuTra.setPhieuPhatID(rs.getInt("PhieuPhatID"));
                phieuTra.setNhanVienID(rs.getInt("NhanVienID"));
                phieuTra.setIdDocGia(rs.getInt("ID_DocGia"));
                phieuTra.setSachID(rs.getInt("SachID"));
                phieuTra.setChiTietID(rs.getInt("ChiTietID"));
                phieuTra.setNgayTra(rs.getDate("NgayTra"));
                phieuTra.setGhiChu(rs.getString("GhiChu"));
                phieuTra.setTrangThaiSach(rs.getString("TrangThaiSach"));
                list.add(phieuTra);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean createPhieuTra(int chiTietMuonID, int nhanVienID, int idDocGia,
            int sachID, int chiTietID, Date ngayTra, String ghiChu, String trangThaiSach) {
        String createSP = "INSERT INTO PhieuTra (ChiTietMuon_ID, NhanVienID, ID_DocGia, SachID, ChiTietID,NgayTra, GhiChu, TrangThaiSach) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String updatePhieuMuon = "UPDATE ChiTietMuon SET TrangThai = N'Đã trả' WHERE ChiTietMuon_ID = ?";
        String updateSachChiTiet = "UPDATE SachChiTiet SET TrangThai = N'Đang có sẵn' WHERE ChiTietID = ?";
        String updatePointDocGia = "UPDATE DocGia SET PointKhachHang = PointKhachHang + 50 WHERE ID_DocGia = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(createSP); PreparedStatement updatePhieuMuonStm = con.prepareStatement(updatePhieuMuon); PreparedStatement updateSachChiTietStm = con.prepareStatement(updateSachChiTiet); PreparedStatement updatePointStm = con.prepareStatement(updatePointDocGia)) {

            stm.setInt(1, chiTietMuonID);
            stm.setInt(2, nhanVienID);
            stm.setInt(3, idDocGia);
            stm.setInt(4, sachID);
            stm.setInt(5, chiTietID);
            stm.setDate(6, ngayTra);
            stm.setString(7, ghiChu);
            stm.setString(8, trangThaiSach);

            int row = stm.executeUpdate();

            if (row > 0) {
                updatePhieuMuonStm.setInt(1, chiTietMuonID);
                updatePhieuMuonStm.executeUpdate();

                updateSachChiTietStm.setInt(1, chiTietID);
                updateSachChiTietStm.executeUpdate();

                updatePointStm.setInt(1, idDocGia);
                updatePointStm.executeUpdate();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static PhieuTra getPhieuTraByID(int id) {
        PhieuTra phieuTra = null;
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "SELECT * FROM PhieuTra WHERE PhieuTraID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                phieuTra = new PhieuTra();
                phieuTra.setGhiChu(rs.getString("GhiChu"));
                phieuTra.setPhieuTraID(rs.getInt("PhieuTraID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phieuTra;
    }

    public static PhieuMuon getChiTietMuonByID(int id) {
        PhieuMuon pm = null;
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "SELECT * FROM ChiTietMuon WHERE ChiTietMuon_ID = ? AND TrangThai=N'Đang Mượn'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pm = new PhieuMuon();
                pm.setChiTietMuonID(rs.getInt("ChiTietMuon_ID"));
                pm.setNhanVienID(rs.getInt("NhanVienID"));
                pm.setDocGiaID(rs.getInt("ID_DocGia"));
                pm.setSachID(rs.getInt("Sach_ID"));
                pm.setChiTietID(rs.getInt("ChiTietID"));
                pm.setNgayMuon(rs.getDate("NgayMuon"));
                pm.setNgayTra(rs.getDate("NgayTra"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

}
