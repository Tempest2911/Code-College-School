/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;

/**
 *
 * @author MINHDUC
 */
public class DocGia {
    public int idDocGia;
    public String hoTen;
    public String sdt;
    public String cccd;
    public int pointKhachHang;
    public String trangThai;
    public String rank;

    public DocGia() {
    }

    public DocGia(int idDocGia, String hoTen, String sdt, String cccd, int pointKhachHang, String trangThai, String rank) {
        this.idDocGia = idDocGia;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.cccd = cccd;
        this.pointKhachHang = pointKhachHang;
        this.trangThai = trangThai;
        this.rank = rank;
    }

    public int getIdDocGia() {
        return idDocGia;
    }

    public void setIdDocGia(int idDocGia) {
        this.idDocGia = idDocGia;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public int getPointKhachHang() {
        return pointKhachHang;
    }

    public void setPointKhachHang(int pointKhachHang) {
        this.pointKhachHang = pointKhachHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    
    
}
