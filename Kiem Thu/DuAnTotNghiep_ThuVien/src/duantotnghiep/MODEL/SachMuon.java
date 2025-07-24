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
public class SachMuon {

    public int sachID;
    public String tenSach;
    public String tacGia;
    public int namXuatBan;
    public int soTrang;
    public double giaTien;
    public String nhaXuatBan;
    public int SoNgayToiDa;
    public int chiTietID;
    public Date ngayMuon;
    public Date hanTra;
    public String trangThai;
    public String ghiChu;

    public int getSachID() {
        return sachID;
    }

    public void setSachID(int sachID) {
        this.sachID = sachID;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getSoNgayToiDa() {
        return SoNgayToiDa;
    }

    public void setSoNgayToiDa(int SoNgayToiDa) {
        this.SoNgayToiDa = SoNgayToiDa;
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

    public Date getHanTra() {
        return hanTra;
    }

    public void setHanTra(Date hanTra) {
        this.hanTra = hanTra;
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

    public SachMuon() {
    }

    public SachMuon(int sachID, String tenSach, String tacGia, int namXuatBan, int soTrang, double giaTien, String nhaXuatBan, int SoNgayToiDa, int chiTietID, Date ngayMuon, Date hanTra, String trangThai, String ghiChu) {
        this.sachID = sachID;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soTrang = soTrang;
        this.giaTien = giaTien;
        this.nhaXuatBan = nhaXuatBan;
        this.SoNgayToiDa = SoNgayToiDa;
        this.chiTietID = chiTietID;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

}
