/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;
import java.util.Date;
/**
 *
 * @author MINHDUC
 */
public class PhieuTra {
    public int phieuTraID;
    public int chiTietMuonID;
    public int phieuPhatID;
    public int nhanVienID;
    public int idDocGia;
    public int sachID;
    public int chiTietID;
    public Date ngayTra;
    public String ghiChu;
    public String trangThaiSach;

    public int getPhieuTraID() {
        return phieuTraID;
    }

    public void setPhieuTraID(int phieuTraID) {
        this.phieuTraID = phieuTraID;
    }

    public int getChiTietMuonID() {
        return chiTietMuonID;
    }

    public void setChiTietMuonID(int chiTietMuonID) {
        this.chiTietMuonID = chiTietMuonID;
    }

    public int getPhieuPhatID() {
        return phieuPhatID;
    }

    public void setPhieuPhatID(int phieuPhatID) {
        this.phieuPhatID = phieuPhatID;
    }

    public int getNhanVienID() {
        return nhanVienID;
    }

    public void setNhanVienID(int nhanVienID) {
        this.nhanVienID = nhanVienID;
    }

    public int getIdDocGia() {
        return idDocGia;
    }

    public void setIdDocGia(int idDocGia) {
        this.idDocGia = idDocGia;
    }

    public int getSachID() {
        return sachID;
    }

    public void setSachID(int sachID) {
        this.sachID = sachID;
    }

    public int getChiTietID() {
        return chiTietID;
    }

    public void setChiTietID(int chiTietID) {
        this.chiTietID = chiTietID;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThaiSach() {
        return trangThaiSach;
    }

    public void setTrangThaiSach(String trangThaiSach) {
        this.trangThaiSach = trangThaiSach;
    }

    public PhieuTra() {
    }

    public PhieuTra(int phieuTraID, int chiTietMuonID, int phieuPhatID, int nhanVienID, int idDocGia, int sachID, int chiTietID, Date ngayTra, String ghiChu, String trangThaiSach) {
        this.phieuTraID = phieuTraID;
        this.chiTietMuonID = chiTietMuonID;
        this.phieuPhatID = phieuPhatID;
        this.nhanVienID = nhanVienID;
        this.idDocGia = idDocGia;
        this.sachID = sachID;
        this.chiTietID = chiTietID;
        this.ngayTra = ngayTra;
        this.ghiChu = ghiChu;
        this.trangThaiSach = trangThaiSach;
    }
    
}
