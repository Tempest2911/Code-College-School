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
public class ChiTietSach {
    public int ChiTietID;
    public int SachID;
    public String TrangThai;
    public String TinhTrangSach;
    public Date NgayNhap;

    public ChiTietSach() {
    }

    public ChiTietSach(int ChiTietID, int SachID, String TrangThai, String TinhTrangSach, Date NgayNhap) {
        this.ChiTietID = ChiTietID;
        this.SachID = SachID;
        this.TrangThai = TrangThai;
        this.TinhTrangSach = TinhTrangSach;
        this.NgayNhap = NgayNhap;
    }

    public int getChiTietID() {
        return ChiTietID;
    }

    public void setChiTietID(int ChiTietID) {
        this.ChiTietID = ChiTietID;
    }

    public int getSachID() {
        return SachID;
    }

    public void setSachID(int SachID) {
        this.SachID = SachID;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getTinhTrangSach() {
        return TinhTrangSach;
    }

    public void setTinhTrangSach(String TinhTrangSach) {
        this.TinhTrangSach = TinhTrangSach;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public Object ChiTietID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
