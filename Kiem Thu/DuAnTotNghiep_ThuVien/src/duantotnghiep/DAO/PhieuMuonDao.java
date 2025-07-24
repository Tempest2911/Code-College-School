/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.ChiTietSach;
import duantotnghiep.MODEL.PhieuMuon;
import duantotnghiep.MODEL.Sach;
import duantotnghiep.MODEL.SachMuon;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author duck
 */
public class PhieuMuonDao {

    static String getAllSDT = "SELECT S.SachID, S.TenSach, S.TacGia, S.NamXuatBan, S.SoTrang, S.GiaTien, "
            + "S.NhaXuatBan, S.SoNgayToiDa, SC.ChiTietID, CM.NgayMuon, CM.NgayTra, CM.TrangThai, CM.GhiChu "
            + "FROM Sach S "
            + "LEFT JOIN SachChiTiet SC ON S.SachID = SC.SachID "
            + "LEFT JOIN ChiTietMuon CM ON SC.ChiTietID = CM.ChiTietID "
            + "LEFT JOIN DocGia DG ON CM.ID_DocGia = DG.ID_DocGia "
            + "WHERE DG.SDT = ?";

    static String getAllCCCD = "SELECT S.SachID, S.TenSach, S.TacGia, S.NamXuatBan, S.SoTrang, S.GiaTien, "
            + "S.NhaXuatBan,S.SoNgayToiDa, SC.ChiTietID, CM.NgayMuon, CM.NgayTra, CM.TrangThai, CM.GhiChu "
            + "FROM Sach S "
            + "LEFT JOIN SachChiTiet SC ON S.SachID = SC.SachID "
            + "LEFT JOIN ChiTietMuon CM ON SC.ChiTietID = CM.ChiTietID "
            + "LEFT JOIN DocGia DG ON CM.ID_DocGia = DG.ID_DocGia "
            + "WHERE DG.CCCD = ?";
    static String getAllTen = "SELECT S.SachID, S.TenSach, S.TacGia, S.NamXuatBan, S.SoTrang, S.GiaTien, "
            + "S.NhaXuatBan, S.SoNgayToiDa, SC.ChiTietID, CM.NgayMuon, CM.NgayTra, CM.TrangThai, CM.GhiChu "
            + "FROM Sach S "
            + "LEFT JOIN SachChiTiet SC ON S.SachID = SC.SachID "
            + "LEFT JOIN ChiTietMuon CM ON SC.ChiTietID = CM.ChiTietID "
            + "WHERE CM.ID_DocGia = ?";

    static String getAllSach = "Select * From Sach_SoLuong where SachID=?";
    static String updateTrangThai = "UPDATE SachChiTiet SET TrangThai = N'Đang được Mượn' WHERE ChiTietID = ?";

    static String createPhieuMuon = "INSERT INTO ChiTietMuon (PhieuMuonID, NhanVienID, ID_DocGia, Sach_ID, ChiTietID, NgayMuon, SoNgayMuon, TrangThai, GhiChu) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, N'Đang Mượn', ?)";
    static String getAllPM = "select * from ChiTietMuon";

    public static ArrayList<SachMuon> getAllTen(int idDocGia) {
        ArrayList<SachMuon> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAllTen)) {
            stm.setInt(1, idDocGia);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SachMuon sach = new SachMuon();
                sach.sachID = rs.getInt("SachID");
                sach.tenSach = rs.getString("TenSach");
                sach.tacGia = rs.getString("TacGia");
                sach.namXuatBan = rs.getInt("NamXuatBan");
                sach.soTrang = rs.getInt("SoTrang");
                sach.giaTien = rs.getDouble("GiaTien");
                sach.nhaXuatBan = rs.getString("NhaXuatBan");
                sach.SoNgayToiDa = rs.getInt("SoNgayToiDa");
                sach.chiTietID = rs.getInt("ChiTietID");
                sach.ngayMuon = rs.getDate("NgayMuon");
                sach.hanTra = rs.getDate("NgayTra");
                sach.trangThai = rs.getString("TrangThai");
                sach.ghiChu = rs.getString("GhiChu");
                sachs.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sachs;
    }

    public static ArrayList<SachMuon> getAllSDT(String SDT) {
        ArrayList<SachMuon> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAllSDT)) {
            stm.setString(1, SDT);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SachMuon sach = new SachMuon();
                sach.sachID = rs.getInt("SachID");
                sach.tenSach = rs.getString("TenSach");
                sach.tacGia = rs.getString("TacGia");
                sach.namXuatBan = rs.getInt("NamXuatBan");
                sach.soTrang = rs.getInt("SoTrang");
                sach.giaTien = rs.getDouble("GiaTien");
                sach.nhaXuatBan = rs.getString("NhaXuatBan");
                sach.SoNgayToiDa = rs.getInt("SoNgayToiDa");
                sach.chiTietID = rs.getInt("ChiTietID");
                sach.ngayMuon = rs.getDate("NgayMuon");
                sach.hanTra = rs.getDate("NgayTra");
                sach.trangThai = rs.getString("TrangThai");
                sach.ghiChu = rs.getString("GhiChu");
                sachs.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sachs;
    }

    public static ArrayList<SachMuon> getAllCCCD(String CCCD) {
        ArrayList<SachMuon> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAllCCCD)) {
            stm.setString(1, CCCD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SachMuon sach = new SachMuon();
                sach.sachID = rs.getInt("SachID");
                sach.tenSach = rs.getString("TenSach");
                sach.tacGia = rs.getString("TacGia");
                sach.namXuatBan = rs.getInt("NamXuatBan");
                sach.soTrang = rs.getInt("SoTrang");
                sach.giaTien = rs.getDouble("GiaTien");
                sach.nhaXuatBan = rs.getString("NhaXuatBan");
                sach.SoNgayToiDa = rs.getInt("SoNgayToiDa");
                sach.chiTietID = rs.getInt("ChiTietID");
                sach.ngayMuon = rs.getDate("NgayMuon");
                sach.hanTra = rs.getDate("NgayTra");
                sach.trangThai = rs.getString("TrangThai");
                sach.ghiChu = rs.getString("GhiChu");
                sachs.add(sach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sachs;
    }

    public static ArrayList<Sach> getAll(int SachID) {
        ArrayList<Sach> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAllSach)) {
            stm.setInt(1, SachID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setSachID(rs.getInt("SachID"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setTacGia(rs.getString("TacGia"));
                sach.setNamXuatBan(rs.getInt("NamXuatBan"));
                sach.setSoTrang(rs.getInt("SoTrang"));
                sach.setGiaTien(rs.getDouble("GiaTien"));
                sach.setNhaXuatBan(rs.getString("NhaXuatBan"));
                sach.setRank(rs.getString("Rank"));
                sach.setTrangThai(rs.getString("TrangThai"));
                sach.setMota(rs.getString("MoTa"));
                sach.setSoNgayMuon(rs.getInt("SoNgayToiDa"));
                sach.setAvatar(rs.getString("Avatar"));
                sach.setSoLuong(rs.getInt("SoLuongSach"));
                sachs.add(sach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sachs;
    }

    public static ArrayList<ChiTietSach> getChiTietSachBySachID(int SachID) {
        ArrayList<ChiTietSach> danhSachChiTietSach = new ArrayList<>();
        String sql = "SELECT * FROM SachChiTiet WHERE SachID = ? AND TrangThai=N'Đang có sẵn'";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, SachID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChiTietSach chiTietSach = new ChiTietSach();
                chiTietSach.setChiTietID(rs.getInt("ChiTietID"));
                chiTietSach.setSachID(rs.getInt("SachID"));
                chiTietSach.setTrangThai(rs.getString("TrangThai"));
                chiTietSach.setTinhTrangSach(rs.getString("TinhTrangSach"));
                chiTietSach.setNgayNhap(rs.getDate("NgayNhap"));
                danhSachChiTietSach.add(chiTietSach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachChiTietSach;
    }

    public static boolean createPhieuMuon(int PhieuMuonID, int nhanVienID, int docGiaID, int sachID, int chiTietID, Date NgayMuon, int soNgayMuon, String ghiChu) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(createPhieuMuon); PreparedStatement updateStm = con.prepareStatement(updateTrangThai)) {
            stm.setInt(1, PhieuMuonID);
            stm.setInt(2, nhanVienID);
            stm.setInt(3, docGiaID);
            stm.setInt(4, sachID);
            stm.setInt(5, chiTietID);
            stm.setDate(6, NgayMuon);
            stm.setInt(7, soNgayMuon);
            stm.setString(8, ghiChu);
            int row = stm.executeUpdate();
            updateStm.setInt(1, chiTietID);
            updateStm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<PhieuMuon> getAllChiTietMuon() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietMuon where TrangThai =N'Đang mượn'";

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                PhieuMuon ct = new PhieuMuon();
                ct.chiTietMuonID = rs.getInt("ChiTietMuon_ID");
                ct.phieuMuonID = rs.getInt("PhieuMuonID");
                ct.nhanVienID = rs.getInt("NhanVienID");
                ct.docGiaID = rs.getInt("ID_DocGia");
                ct.sachID = rs.getInt("Sach_ID");
                ct.chiTietID = rs.getInt("ChiTietID");
                ct.ngayMuon = rs.getDate("NgayMuon");
                ct.soNgayMuon = rs.getInt("SoNgayMuon");
                ct.ngayTra = rs.getDate("NgayTra");
                ct.trangThai = rs.getString("TrangThai");
                ct.ghiChu = rs.getString("GhiChu");

                list.add(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<PhieuMuon> getAll() {
        ArrayList<PhieuMuon> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAllPM)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PhieuMuon ct = new PhieuMuon();
                ct.chiTietMuonID = rs.getInt("ChiTietMuon_ID");
                ct.phieuMuonID = rs.getInt("PhieuMuonID");
                ct.nhanVienID = rs.getInt("NhanVienID");
                ct.docGiaID = rs.getInt("ID_DocGia");
                ct.sachID = rs.getInt("Sach_ID");
                ct.chiTietID = rs.getInt("ChiTietID");
                ct.ngayMuon = rs.getDate("NgayMuon");
                ct.soNgayMuon = rs.getInt("SoNgayMuon");
                ct.ngayTra = rs.getDate("NgayTra");
                ct.trangThai = rs.getString("TrangThai");
                ct.ghiChu = rs.getString("GhiChu");
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean isDocGiaHoatDong(int docGiaID) {
        String sql = "SELECT TrangThai FROM DocGia WHERE ID_DocGia = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, docGiaID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String trangThai = rs.getString("TrangThai");
                return trangThai.equalsIgnoreCase("Hoạt động");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
