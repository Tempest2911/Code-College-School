/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;

import java.util.Date;

/**
 *
 * @author duck
 */
public class PhieuPhat {

    public Integer idDocGia;
    public String hoTen;
    public String sdt;
    public String cccd;
    public Integer phieuPhatID;
    public Date ngayLam;
    public String lyDo;
    public Double soTienPhat;
    public String trangThai;
    public String anh;

    public PhieuPhat() {
    }

    public PhieuPhat(Integer idDocGia, String hoTen, String sdt, String cccd, Integer phieuPhatID, Date ngayLam, String lyDo, Double soTienPhat, String trangThai, String anh) {
        this.idDocGia = idDocGia;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.cccd = cccd;
        this.phieuPhatID = phieuPhatID;
        this.ngayLam = ngayLam;
        this.lyDo = lyDo;
        this.soTienPhat = soTienPhat;
        this.trangThai = trangThai;
        this.anh = anh;
    }

    public Integer getIdDocGia() {
        return idDocGia;
    }

    public void setIdDocGia(Integer idDocGia) {
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

    public Integer getPhieuPhatID() {
        return phieuPhatID;
    }

    public void setPhieuPhatID(Integer phieuPhatID) {
        this.phieuPhatID = phieuPhatID;
    }

    public Date getNgayLam() {
        return ngayLam;
    }

    public void setNgayLam(Date ngayLam) {
        this.ngayLam = ngayLam;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public Double getSoTienPhat() {
        return soTienPhat;
    }

    public void setSoTienPhat(Double soTienPhat) {
        this.soTienPhat = soTienPhat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    
}
