/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.DAO;

import duantotnghiep.MODEL.DocGiaInFo;
import duantotnghiep.MODEL.PhieuPhat;
import duantotnghiep.MODEL.PhieuTra;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.sql.Statement;

/**
 *
 * @author duck
 */
public class PhieuPhatDao {

    public static ArrayList<PhieuPhat> getAll() {
        ArrayList<PhieuPhat> phieuPhats = new ArrayList<>();
        String sql = "SELECT pp.PhieuPhatID, dg.HoTen, dg.SDT, dg.CCCD, "
                + "pp.NgayLam, pp.LyDo, pp.SoTienPhat, pp.TrangThai "
                + "FROM PhieuPhat pp "
                + "JOIN DocGia dg ON pp.ID_DocGia = dg.ID_DocGia";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                PhieuPhat phieuPhat = new PhieuPhat();
                phieuPhat.phieuPhatID = rs.getInt("PhieuPhatID");
                phieuPhat.hoTen = rs.getString("HoTen");
                phieuPhat.sdt = rs.getString("SDT");
                phieuPhat.cccd = rs.getString("CCCD");
                phieuPhat.ngayLam = rs.getDate("NgayLam");
                phieuPhat.lyDo = rs.getString("LyDo");
                phieuPhat.soTienPhat = rs.getDouble("SoTienPhat");
                phieuPhat.trangThai = rs.getString("TrangThai");
                phieuPhats.add(phieuPhat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuPhats;
    }

    public static ArrayList<PhieuPhat> getAllPP() {
        ArrayList<PhieuPhat> phieuPhats = new ArrayList<>();
        String sql = "SELECT * from PhieuPhat Where TrangThai=N'Chưa thanh toán' ";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                PhieuPhat phieuPhat = new PhieuPhat();
                phieuPhat.phieuPhatID = rs.getInt("PhieuPhatID");
                phieuPhat.idDocGia = rs.getInt("ID_DocGia");
                phieuPhat.ngayLam = rs.getDate("NgayLam");
                phieuPhat.lyDo = rs.getString("LyDo");
                phieuPhat.soTienPhat = rs.getDouble("SoTienPhat");
                phieuPhat.trangThai = rs.getString("TrangThai");
                phieuPhats.add(phieuPhat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuPhats;
    }
    
    public static ArrayList<PhieuPhat> getAllPPN() {
        ArrayList<PhieuPhat> phieuPhats = new ArrayList<>();
        String sql = "SELECT * from PhieuPhat";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                PhieuPhat phieuPhat = new PhieuPhat();
                phieuPhat.phieuPhatID = rs.getInt("PhieuPhatID");
                phieuPhat.idDocGia = rs.getInt("ID_DocGia");
                phieuPhat.ngayLam = rs.getDate("NgayLam");
                phieuPhat.lyDo = rs.getString("LyDo");
                phieuPhat.soTienPhat = rs.getDouble("SoTienPhat");
                phieuPhat.trangThai = rs.getString("TrangThai");
                phieuPhats.add(phieuPhat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuPhats;
    }

    public static boolean updateTrangThai(String idPhieuPhat, String trangThai) {
        String sql = "UPDATE PhieuPhat SET TrangThai = ? WHERE PhieuPhatID = ?";
        try (
                Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, trangThai);
            stm.setString(2, idPhieuPhat);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<DocGiaInFo> getAl() {
        ArrayList<DocGiaInFo> docGiaInFos = new ArrayList<>();
        String sql = "SELECT dg.HoTen, dg.SDT, dg.CCCD "
                + "FROM DocGia dg ";
        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                DocGiaInFo dg = new DocGiaInFo();
                dg.hoTen = rs.getString("HoTen");
                dg.sdt = rs.getString("SDT");
                dg.cccd = rs.getString("CCCD");
                docGiaInFos.add(dg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docGiaInFos;
    }

    public static void layThongTinTheo(String sdt, String ten, String cccd, JLabel lblImage) {
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "SELECT Avatar FROM DocGia WHERE SDT = ? AND HoTen = ? AND CCCD = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sdt);
            stmt.setString(2, ten);
            stmt.setString(3, cccd);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String avatarPath = rs.getString("Avatar");
                // Cập nhật JLabel với ảnh
                File imgFile = new File(avatarPath);
                if (imgFile.exists()) {
                    BufferedImage img = ImageIO.read(imgFile);
                    ImageIcon icon = new ImageIcon(img);
                    lblImage.setIcon(icon);
                } else {
                    lblImage.setIcon(null);
                }
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Integer themPhieuPhat(int idDocGia, String lyDo, double soTienPhat) {
        Integer idPhieuPhat = null;
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "INSERT INTO PhieuPhat (ID_DocGia, NgayLam, LyDo, SoTienPhat, TrangThai) VALUES (?, GETDATE(), ?, ?, N'Chưa Thanh Toán')";
            String updatePointDocGia = "UPDATE DocGia SET PointKhachHang = PointKhachHang - 50 WHERE ID_DocGia = ?";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);PreparedStatement updatePointStm = conn.prepareStatement(updatePointDocGia);
            stmt.setInt(1, idDocGia);
            stmt.setString(2, lyDo);
            stmt.setDouble(3, soTienPhat);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idPhieuPhat = rs.getInt(1); // Lấy ID vừa được tạo
                }
                updatePointStm.setInt(1, idDocGia);
                updatePointStm.executeUpdate();
                rs.close();
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idPhieuPhat;
    }

    public static boolean suaPhieuPhat(int idPhieu, String lyDo, double soTienPhat, String trangThai) {
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "UPDATE PhieuPhat SET LyDo = ?, SoTienPhat = ?, TrangThai = ? WHERE PhieuPhatID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, lyDo);
            stmt.setDouble(2, soTienPhat);
            stmt.setString(3, trangThai);
            stmt.setInt(4, idPhieu);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static PhieuPhat timThongTin(String hoTen, String sdt, String cccd) {
        String sql = "SELECT * FROM DocGia WHERE HoTen = ? OR SDT = ? OR CCCD = ?";

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hoTen);
            stmt.setString(2, sdt);
            stmt.setString(3, cccd);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PhieuPhat phieuPhat = new PhieuPhat();
                    phieuPhat.setSdt(rs.getString("SDT"));
                    phieuPhat.setCccd(rs.getString("CCCD"));
                    phieuPhat.setHoTen(rs.getString("HoTen"));
                    return phieuPhat;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Trả về null nếu không tìm thấy
    }

    public static ArrayList<String> getCCCDDocGia(String keyword) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT CCCD FROM DocGia WHERE CCCD LIKE ?";

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("CCCD"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static DocGiaInFo cbo(String hoTen, String sdt, String cccd) {
        String sql = "SELECT * FROM DocGia WHERE HoTen = ? OR SDT = ? OR CCCD = ?";

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hoTen);
            stmt.setString(2, sdt);
            stmt.setString(3, cccd);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DocGiaInFo dg = new DocGiaInFo();
                    dg.setSdt(rs.getString("SDT"));
                    dg.setCccd(rs.getString("CCCD"));
                    dg.setHoTen(rs.getString("HoTen"));
                    return dg;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Trả về null nếu không tìm thấy
    }

    public static ArrayList<String> getTenDocGia(String keyword) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT HoTen FROM DocGia WHERE HoTen LIKE ?";

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("HoTen"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getSDTDocGia(String keyword) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT SDT FROM DocGia WHERE SDT LIKE ?";

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("SDT"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void layThongTinTheoSDT(String sdt, String ten, String cccd) {
        String sql = "SELECT HoTen, CCCD FROM DocGia WHERE SDT = ?";

        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ten = rs.getString("HoTen");
                cccd = rs.getString("CCCD");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Integer layIdDocGia(String tenDocGia, String cccd, String sdt) {
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl)) {
            String sql = "SELECT ID_DocGia FROM DocGia WHERE HoTen = ? AND CCCD = ? AND SDT = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenDocGia);
            stmt.setString(2, cccd);
            stmt.setString(3, sdt);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID_DocGia"); // Trả về ID của độc giả
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<PhieuPhat> timPhieuPhatTheoSDT(String search) {
        ArrayList<PhieuPhat> phieuPhats = new ArrayList<>();
        String sql = "SELECT pp.PhieuPhatID, dg.HoTen, dg.SDT, dg.CCCD, "
                + "pp.NgayLam, pp.LyDo, pp.SoTienPhat, pp.TrangThai "
                + "FROM PhieuPhat pp "
                + "JOIN DocGia dg ON pp.ID_DocGia = dg.ID_DocGia "
                + "WHERE dg.HoTen LIKE ? OR dg.SDT LIKE ? OR dg.CCCD LIKE ?";

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {

            // Gán giá trị có chứa dấu % để tìm kiếm gần đúng
            stm.setString(1, "%" + search + "%");
            stm.setString(2, "%" + search + "%");
            stm.setString(3, "%" + search + "%");

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    PhieuPhat phieuPhat = new PhieuPhat();
                    phieuPhat.phieuPhatID = rs.getInt("PhieuPhatID");
                    phieuPhat.hoTen = rs.getString("HoTen");
                    phieuPhat.sdt = rs.getString("SDT");
                    phieuPhat.cccd = rs.getString("CCCD");
                    phieuPhat.ngayLam = rs.getDate("NgayLam");
                    phieuPhat.lyDo = rs.getString("LyDo");
                    phieuPhat.soTienPhat = rs.getDouble("SoTienPhat");
                    phieuPhat.trangThai = rs.getString("TrangThai");
                    phieuPhats.add(phieuPhat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return phieuPhats;
    }

    public static Integer layIdPhieuPhatMoi() {
        Integer idPhieuPhat = null;
        try {
            String sql = "SELECT MAX(PhieuPhatID) FROM PhieuPhat";
            Connection conn = DriverManager.getConnection(CRUD.connectionUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idPhieuPhat = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPhieuPhat;
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phieuTra;
    }

    public static PhieuPhat getPhieuPhatByID(int id) {
        String sql = "SELECT * FROM PhieuPhat WHERE PhieuPhatID = ?";

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                PhieuPhat phieuPhat = new PhieuPhat();
                phieuPhat.phieuPhatID = rs.getInt("PhieuPhatID");
                phieuPhat.idDocGia = rs.getInt("ID_DocGia");
                phieuPhat.ngayLam = rs.getDate("NgayLam");
                phieuPhat.lyDo = rs.getString("LyDo");
                phieuPhat.soTienPhat = rs.getDouble("SoTienPhat");
                phieuPhat.trangThai = rs.getString("TrangThai");
                return phieuPhat;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static PhieuPhat getPhieuPhatByPhieuTraID(int phieuTraID) {
        String sql = "SELECT PhieuPhatID FROM PhieuTra WHERE PhieuTraID = ?";

        try (Connection con = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, phieuTraID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                int phieuPhatID = rs.getInt("PhieuPhatID");

                // Kiểm tra nếu NULL
                if (rs.wasNull()) {
                    return null;
                }

                return getPhieuPhatByID(phieuPhatID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean capNhatPhieuPhatIDTrongPhieuTra(int phieuTraID, int phieuPhatID) {
        String sql = "UPDATE PhieuTra SET PhieuPhatID = ? WHERE PhieuTraID = ?";
        try (Connection conn = DriverManager.getConnection(CRUD.connectionUrl); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, phieuPhatID);
            stmt.setInt(2, phieuTraID);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
