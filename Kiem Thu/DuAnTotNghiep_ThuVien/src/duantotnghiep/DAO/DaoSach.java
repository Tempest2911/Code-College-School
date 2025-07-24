/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.Sach;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MINHDUC
 */
public class DaoSach {

    static String getAll = "SELECT * FROM Sach_SoLuong";
    static String getAllCon = "SELECT * FROM Sach_SoLuong where TrangThai='Còn'";
    static String getAllHet = "SELECT * FROM Sach_SoLuong WHERE TrangThai = N'Hết';";
    static String createSP = "INSERT INTO Sach (TenSach, TacGia, NamXuatBan, SoTrang, GiaTien, NhaXuatBan, Rank,TrangThai,MoTa,SoNgayToiDa, Avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    static String updateSP = "UPDATE Sach SET TenSach=?, TacGia=?, NamXuatBan=?, SoTrang=?, GiaTien=?, NhaXuatBan=?, Rank=?, TrangThai=?,MoTa=?,SoNgayToiDa=?, Avatar=? WHERE SachID=?";
    static String deleteSSP = "DELETE FROM Sach WHERE SachID=?";
    static String searchByName = "Select * from Sach_SoLuong Where TenSach like ? or TacGia like ?";
    static String updateTrangThai = "UPDATE Sach SET TrangThai = CASE "
            + "WHEN NOT EXISTS (SELECT 1 FROM SachChiTiet WHERE SachID = Sach.SachID) "
            + "THEN N'Hết' ELSE N'Còn' END;";

    public static ArrayList<Sach> getAll() {
        ArrayList<Sach> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAll); PreparedStatement updateStm = con.prepareStatement(updateTrangThai)) {
            ResultSet rs = stm.executeQuery();
            updateStm.executeUpdate();
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

    public static ArrayList<Sach> getAllCon() {
        ArrayList<Sach> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAllCon)) {
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

    public static ArrayList<Sach> getAllHet() {
        ArrayList<Sach> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAllHet)) {
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

    public static boolean createSach(String tenSach, String tacGia, int namXuatBan,
            int soTrang, double giaTien, String nhaXuatBan, String rank, String trangthai, String mota, int SoNgayToiDa, String avatar) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(createSP); PreparedStatement updateStm = con.prepareStatement(updateTrangThai)) {
            stm.setString(1, tenSach);
            stm.setString(2, tacGia);
            stm.setInt(3, namXuatBan);
            stm.setInt(4, soTrang);
            stm.setDouble(5, giaTien);
            stm.setString(6, nhaXuatBan);
            stm.setString(7, rank);
            stm.setString(8, trangthai);
            stm.setString(9, mota);
            stm.setInt(10, SoNgayToiDa);
            stm.setString(11, avatar);
            int row = stm.executeUpdate();
            updateStm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateSach(Integer IDSach, String tenSach, String tacGia, int namXuatBan,
            int soTrang, double giaTien, String nhaXuatBan, String rank, String trangthai, String mota, int SoNgayToiDa, String avatar) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(updateSP); PreparedStatement updateStm = con.prepareStatement(updateTrangThai)) {
            stm.setString(1, tenSach);
            stm.setString(2, tacGia);
            stm.setInt(3, namXuatBan);
            stm.setInt(4, soTrang);
            stm.setDouble(5, giaTien);
            stm.setString(6, nhaXuatBan);
            stm.setString(7, rank);
            stm.setString(8, trangthai);
            stm.setString(9, mota);
            stm.setInt(10, SoNgayToiDa);
            stm.setString(11, avatar);
            stm.setInt(12, IDSach);
            int row = stm.executeUpdate();
            updateStm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteSach(Integer IDSach) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(deleteSSP)) {
            stm.setInt(1, IDSach);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Sach> getPBByName(String name) {
        ArrayList<Sach> sachs = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(searchByName)) {
            stm.setString(1, "%" + name + "%");
            stm.setString(2, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setSachID(rs.getInt(1));
                sach.setTenSach(rs.getString(2));
                sach.setTacGia(rs.getString(3));
                sach.setNamXuatBan(rs.getInt(4));
                sach.setSoLuong(rs.getInt(11));
                sach.setSoTrang(rs.getInt(5));
                sach.setGiaTien(rs.getDouble(6));
                sach.setNhaXuatBan(rs.getString(7));
                sach.setRank(rs.getString(8));
                sach.setTrangThai(rs.getString(9));
                sach.setMota(rs.getString(10));
                sach.setSoNgayMuon(rs.getInt(11));
                sach.setAvatar(rs.getString(12));
                sachs.add(sach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sachs;
    }

    public static ArrayList<Sach> getPBByNameCon(String name) {
        ArrayList<Sach> sachs = new ArrayList<>();
        String query = "SELECT * FROM Sach_SoLuong WHERE (TenSach LIKE ? OR TacGia LIKE ?) AND TrangThai='Còn'";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, "%" + name + "%");
            stm.setString(2, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setSachID(rs.getInt(1));
                sach.setTenSach(rs.getString(2));
                sach.setTacGia(rs.getString(3));
                sach.setNamXuatBan(rs.getInt(4));
                sach.setSoLuong(rs.getInt(11));
                sach.setSoTrang(rs.getInt(5));
                sach.setGiaTien(rs.getDouble(6));
                sach.setNhaXuatBan(rs.getString(7));
                sach.setMota(rs.getString(10));
                sach.setSoNgayMuon(rs.getInt(11));
                sach.setAvatar(rs.getString(12));
                sachs.add(sach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sachs;
    }

    public static ArrayList<Sach> getPBByNameHet(String name) {
        ArrayList<Sach> sachs = new ArrayList<>();
        String query = "SELECT * FROM Sach_SoLuong WHERE (TenSach LIKE ? OR TacGia LIKE ?) AND TrangThai=N'Hết'";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, "%" + name + "%");
            stm.setString(2, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setSachID(rs.getInt(1));
                sach.setTenSach(rs.getString(2));
                sach.setTacGia(rs.getString(3));
                sach.setNamXuatBan(rs.getInt(4));
                sach.setSoLuong(rs.getInt(11));
                sach.setSoTrang(rs.getInt(5));
                sach.setGiaTien(rs.getDouble(6));
                sach.setNhaXuatBan(rs.getString(7));
                sach.setMota(rs.getString(10));
                sach.setSoNgayMuon(rs.getInt(11));
                sach.setAvatar(rs.getString(12));
                sachs.add(sach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sachs;
    }

    public static boolean checkSachExists(String tenSach, int id) {
        String sql = "SELECT COUNT(*) FROM Sach WHERE TenSach = ? AND Id != ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, tenSach);
            stm.setInt(2, id);  // Tránh việc so sánh với chính sách hiện tại
            ResultSet rs = stm.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Tên sách đã tồn tại và không phải sách hiện tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Tên sách chưa tồn tại hoặc trùng với sách hiện tại
    }

    public static boolean checkSachExistsNoID(String tenSach) {
        String sql = "SELECT COUNT(*) FROM Sach WHERE TenSach = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, tenSach);
            ResultSet rs = stm.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Tên sách đã tồn tại và không phải sách hiện tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Tên sách chưa tồn tại hoặc trùng với sách hiện tại
    }
}
