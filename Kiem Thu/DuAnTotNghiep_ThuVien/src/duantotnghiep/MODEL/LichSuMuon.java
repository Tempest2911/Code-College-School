/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;

import java.sql.Date;

/**
 *
 * @author MINHDUC
 */
public class LichSuMuon {

    public int idLichSuMuon;
    public String HoTen;
    public String SDT;
    public String CCCD;
    public int sachID;
    public int chiTietID;
    public int idDocGia;
    public Date ngayMuon;
    public Date ngayTra;
    public String trangThaiSach;

    public LichSuMuon() {
    }

    public LichSuMuon(int idLichSuMuon, String HoTen, String SDT, String CCCD, int sachID, int chiTietID, int idDocGia, Date ngayMuon, Date ngayTra, String trangThaiSach) {
        this.idLichSuMuon = idLichSuMuon;
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.CCCD = CCCD;
        this.sachID = sachID;
        this.chiTietID = chiTietID;
        this.idDocGia = idDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.trangThaiSach = trangThaiSach;
    }

    public int getIdLichSuMuon() {
        return idLichSuMuon;
    }

    public void setIdLichSuMuon(int idLichSuMuon) {
        this.idLichSuMuon = idLichSuMuon;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
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

    public int getIdDocGia() {
        return idDocGia;
    }

    public void setIdDocGia(int idDocGia) {
        this.idDocGia = idDocGia;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThaiSach() {
        return trangThaiSach;
    }

    public void setTrangThaiSach(String trangThaiSach) {
        this.trangThaiSach = trangThaiSach;
    }

    
}
