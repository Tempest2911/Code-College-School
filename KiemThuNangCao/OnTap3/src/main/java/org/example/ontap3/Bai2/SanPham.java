package org.example.ontap3.Bai2;

public class SanPham {
    String ma;
    String ten;
    int namBaoHanh;
    float gia;
    int soLuong;
    String danhMuc;

    public SanPham(String ma, String ten, int namBaoHanh, float gia, int soLuong, String danhMuc) {
        this.ma = ma;
        this.ten = ten;
        this.namBaoHanh = namBaoHanh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.danhMuc = danhMuc;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamBaoHanh() {
        return namBaoHanh;
    }

    public void setNamBaoHanh(int namBaoHanh) {
        this.namBaoHanh = namBaoHanh;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }
}
