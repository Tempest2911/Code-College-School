/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MINHDUC
 */
public class DaoNhanVien {

    static String getAll = "SELECT * FROM NhanVien where Role=N'Nhân Viên'";

    static String createNV = "INSERT INTO NhanVien (NameAC,Pass,Role, TenNhanVien, CCCD, NamSinh, SDT, Email, GioiTinh,Avatar) VALUES (?,?,N'Nhân Viên', ?, ?, ?, ?, ?, ?,?)";
    static String updateNV = "UPDATE NhanVien SET NameAC = ?, Pass = ?, Role = ?, TenNhanVien = ?, CCCD = ?, NamSinh = ?, SDT = ?, Email = ?, GioiTinh = ?,Avatar=? WHERE NhanVienID = ?";
    static String deleteNV = "DELETE FROM NhanVien WHERE NhanVienID=?";
    static String searchByName = "SELECT * FROM NhanVien WHERE TenNhanVien LIKE ? OR CCCD LIKE ? OR SDT LIKE ?";

    public static ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAll)) {

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.nhanVienID = rs.getInt("NhanVienID");
                nv.NameAC = rs.getString("NameAC");
                nv.Pass = rs.getString("Pass");
                nv.Role = rs.getString("Role");
                nv.tenNhanVien = rs.getString("TenNhanVien");
                nv.cccd = rs.getString("CCCD");
                nv.namSinh = rs.getInt("NamSinh");
                nv.sdt = rs.getString("SDT");
                nv.email = rs.getString("Email");
                nv.gioiTinh = rs.getString("GioiTinh");
                nv.Avatar = rs.getString("Avatar");
                danhSachNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachNhanVien;
    }

    public static boolean createNhanVien(String nameAC, String pass, String tenNhanVien,
            String cccd, int namSinh, String sdt, String email,
            String gioiTinh, String Avatar) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(createNV)) {

            stm.setString(1, nameAC);
            stm.setString(2, pass);
            stm.setString(3, tenNhanVien);
            stm.setString(4, cccd);
            stm.setInt(5, namSinh);
            stm.setString(6, sdt);
            stm.setString(7, email);
            stm.setString(8, gioiTinh);
            stm.setString(9, Avatar);

            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateNhanVien(int nhanVienID, String nameAC, String pass, String role,
            String tenNhanVien, String cccd, int namSinh, String sdt,
            String email, String gioiTinh, String Avatar) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(updateNV)) {

            stm.setString(1, nameAC);
            stm.setString(2, pass);
            stm.setString(3, role);
            stm.setString(4, tenNhanVien);
            stm.setString(5, cccd);
            stm.setInt(6, namSinh);
            stm.setString(7, sdt);
            stm.setString(8, email);
            stm.setString(9, gioiTinh);
            stm.setString(10, Avatar);
            stm.setInt(11, nhanVienID);

            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteNhanVien(int IDNhanVien) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(deleteNV)) {
            stm.setInt(1, IDNhanVien);
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<NhanVien> searchNhanVien(String keyword) {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(searchByName)) {

            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + keyword + "%");
            stm.setString(3, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.nhanVienID = rs.getInt(1);
                nv.NameAC = rs.getString(2);
                nv.Pass = rs.getString(3);
                nv.Role = rs.getString(4);
                nv.tenNhanVien = rs.getString(5);
                nv.cccd = rs.getString(6);
                nv.namSinh = rs.getInt(7);
                nv.sdt = rs.getString(8);
                nv.email = rs.getString(9);
                nv.gioiTinh = rs.getString(10);
                nv.Avatar = rs.getString(11);

                nhanViens.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhanViens;
    }

    public static boolean isCCCDExists(String cccd) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE CCCD = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cccd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSDTExists(String sdt) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE SDT = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE Email = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isCCCDExistsUPD(String cccd, int nhanVienID) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE CCCD = ? AND NhanVienID <> ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cccd);
            ps.setInt(2, nhanVienID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSDTExistsUPD(String sdt, int nhanVienID) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE SDT = ? AND NhanVienID <> ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ps.setInt(2, nhanVienID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailExistsUPD(String email, int nhanVienID) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE Email = ? AND NhanVienID <> ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setInt(2, nhanVienID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNameACExists(String nameAC) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE NameAC = ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nameAC);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
