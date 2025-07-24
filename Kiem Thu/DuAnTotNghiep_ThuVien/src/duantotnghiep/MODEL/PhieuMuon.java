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
public class PhieuMuon {

    public int chiTietMuonID;
    public int phieuMuonID;
    public int nhanVienID;
    public int docGiaID;
    public int sachID;
    public int chiTietID;
    public Date ngayMuon;
    public int soNgayMuon;
    public Date ngayTra;
    public String trangThai;
    public String ghiChu;

    public int getChiTietMuonID() {
        return chiTietMuonID;
    }

    public void setChiTietMuonID(int chiTietMuonID) {
        this.chiTietMuonID = chiTietMuonID;
    }

    public int getPhieuMuonID() {
        return phieuMuonID;
    }

    public void setPhieuMuonID(int phieuMuonID) {
        this.phieuMuonID = phieuMuonID;
    }

    public int getNhanVienID() {
        return nhanVienID;
    }

    public void setNhanVienID(int nhanVienID) {
        this.nhanVienID = nhanVienID;
    }

    public int getDocGiaID() {
        return docGiaID;
    }

    public void setDocGiaID(int docGiaID) {
        this.docGiaID = docGiaID;
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

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getSoNgayMuon() {
        return soNgayMuon;
    }

    public void setSoNgayMuon(int soNgayMuon) {
        this.soNgayMuon = soNgayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public PhieuMuon() {
    }

    public PhieuMuon(int chiTietMuonID, int phieuMuonID, int nhanVienID, int docGiaID, int sachID, int chiTietID, Date ngayMuon, int soNgayMuon, Date ngayTra, String trangThai, String ghiChu) {
        this.chiTietMuonID = chiTietMuonID;
        this.phieuMuonID = phieuMuonID;
        this.nhanVienID = nhanVienID;
        this.docGiaID = docGiaID;
        this.sachID = sachID;
        this.chiTietID = chiTietID;
        this.ngayMuon = ngayMuon;
        this.soNgayMuon = soNgayMuon;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    
}
