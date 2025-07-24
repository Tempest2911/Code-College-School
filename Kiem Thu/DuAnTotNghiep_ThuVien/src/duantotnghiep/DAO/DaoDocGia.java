/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.DocGia;
import java.awt.Image;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author MINHDUC
 */
public class DaoDocGia {

    static String getAll = "select * from DocGia";
    static String searchByName = "SELECT * FROM DocGia WHERE HoTen LIKE ? OR CCCD LIKE ? OR SDT LIKE ?";
    static String query = "SELECT ID_DocGia, CCCD, SDT, [Rank] FROM DocGia WHERE HoTen = ?";

    static String queryCCCD = "SELECT ID_DocGia, HoTen, SDT, [Rank] FROM DocGia WHERE CCCD = ?";

    static String querySDT = "SELECT ID_DocGia, HoTen, CCCD, [Rank] FROM DocGia WHERE SDT = ?";
    static String querySachID = "Select TenSach from Sach Where SachID=?";
    static String queryUpdate = "UPDATE DocGia SET HoTen=?, SDT=?,CCCD=?,PointKhachHang=?, TrangThai=? Where ID_DocGia= ?";
    static String queryDelete = "DELETE FROM DocGia WHERE ID_DocGia = ?";

    public static ArrayList<DocGia> getAll() {
        ArrayList<DocGia> docGias = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(getAll)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DocGia docGia = new DocGia();
                docGia.idDocGia = rs.getInt("ID_DocGia");
                docGia.hoTen = rs.getString("HoTen");
                docGia.sdt = rs.getString("SDT");
                docGia.cccd = rs.getString("CCCD");
                docGia.pointKhachHang = rs.getInt("PointKhachHang");
                docGia.trangThai = rs.getString("TrangThai");
                docGia.rank = rs.getString("Rank");
                docGias.add(docGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return docGias;
    }

    public static ArrayList<DocGia> searchDocGia(String keyword) {
        ArrayList<DocGia> docGias = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(searchByName)) {
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + keyword + "%");
            stm.setString(3, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DocGia docGia = new DocGia();
                docGia.idDocGia = rs.getInt(1);
                docGia.hoTen = rs.getString(2);
                docGia.sdt = rs.getString(3);
                docGia.cccd = rs.getString(4);
                docGia.pointKhachHang = rs.getInt(5);
                docGia.trangThai = rs.getString(6);
                docGia.rank = rs.getString(7);
                docGias.add(docGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return docGias;
    }

    public static ArrayList<String> getTenDocGia(String keyword) {
        ArrayList<String> tenDocGiaList = new ArrayList<>();
        String sql = "SELECT HoTen FROM DocGia WHERE HoTen LIKE ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tenDocGiaList.add(rs.getString("HoTen"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenDocGiaList;
    }

    public static void layThongTinTheoTen(String ten, JTextField txtID_DocGia, JTextField txtCCCD, JTextField txtSDT, JTextField txtRank) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, ten);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                txtID_DocGia.setText(rs.getString("ID_DocGia"));
                txtCCCD.setText(rs.getString("CCCD"));
                txtSDT.setText(rs.getString("SDT"));
                txtRank.setText(rs.getString("Rank"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getSDTDocGia(String keyword) {
        ArrayList<String> tenDocGiaList = new ArrayList<>();
        String sql = "SELECT SDT FROM DocGia WHERE SDT LIKE ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tenDocGiaList.add(rs.getString("SDT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenDocGiaList;
    }

    public static void layThongTinTheoSDT(String SDT, JTextField txtID_DocGia, JTextField txtHoTen, JTextField txtCCCD, JTextField txtRank) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            PreparedStatement stm = con.prepareStatement(querySDT);
            stm.setString(1, SDT);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                txtID_DocGia.setText(rs.getString("ID_DocGia"));
                txtHoTen.setText(rs.getString("HoTen"));
                txtCCCD.setText(rs.getString("CCCD"));
                txtRank.setText(rs.getString("Rank"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getCCCDDocGia(String keyword) {
        ArrayList<String> tenDocGiaList = new ArrayList<>();
        String sql = "SELECT CCCD FROM DocGia WHERE CCCD LIKE ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tenDocGiaList.add(rs.getString("CCCD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenDocGiaList;
    }

    public static void layThongTinTheoCCCD(String cccd, JTextField txtID_DocGia, JTextField txtHoTen, JTextField txtSDT, JTextField txtRank) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            PreparedStatement stm = con.prepareStatement(queryCCCD);
            stm.setString(1, cccd);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                txtID_DocGia.setText(rs.getString("ID_DocGia"));
                txtHoTen.setText(rs.getString("HoTen"));
                txtSDT.setText(rs.getString("SDT"));
                txtRank.setText(rs.getString("Rank"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> getIDSach(String keyword) {
        ArrayList<Integer> tenDocGiaList = new ArrayList<>();
        String sql = "SELECT SachID FROM Sach_SoLuong WHERE SachID LIKE ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tenDocGiaList.add(rs.getInt("SachID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenDocGiaList;
    }

    public static void layThongTinTheoSachID(int SachID, JTextField txtTenSach) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl)) {
            PreparedStatement stm = con.prepareStatement(querySachID);
            stm.setInt(1, SachID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                txtTenSach.setText(rs.getString("TenSach"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DocGia getDocGiaByID(int id) {
        DocGia docGia = null;
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "SELECT * FROM DocGia WHERE ID_DocGia = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                docGia = new DocGia();
                docGia.setIdDocGia(rs.getInt("ID_DocGia"));
                docGia.setHoTen(rs.getString("HoTen"));
                docGia.setSdt(rs.getString("SDT"));
                docGia.setCccd(rs.getString("CCCD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return docGia;
    }

    public static boolean updateDocGia(DocGia docGia) {
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(queryUpdate)) {
            stm.setString(1, docGia.hoTen);
            stm.setString(2, docGia.sdt);
            stm.setString(3, docGia.cccd);
            stm.setInt(4, docGia.pointKhachHang);
            stm.setString(5, docGia.trangThai);
            stm.setInt(6, docGia.idDocGia);
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteDocGia(int idDocGia) {

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(queryDelete)) {
            stm.setInt(1, idDocGia);
            int rows = stm.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean KiemTraTrungCCCD(String cccd, int Id) {
        String sql = "SELECT COUNT(*) FROM DocGia WHERE CCCD = ? AND ID_DocGia != ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, cccd);
            stm.setInt(2, Id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean KiemTraTrungSDT(String sdt, int Id) {
        String sql = "SELECT COUNT(*) FROM DocGia WHERE SDT = ? AND ID_DocGia != ?";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, sdt);
            stm.setInt(2, Id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static DocGia getTenDocGiaByID(int id) {
        DocGia docGia = null;
        String Query = "SELECT HoTen FROM DocGia WHERE ID_DocGia = ?";

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(Query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                docGia = new DocGia();
                docGia.setHoTen(rs.getString("HoTen")); // Lấy tên độc giả

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return docGia;
    }
}
