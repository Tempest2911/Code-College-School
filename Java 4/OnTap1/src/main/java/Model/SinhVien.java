package Model;

public class SinhVien {
    String ma;
    String ten;
    int tuoi;
    boolean gioiTinh;
    String nghanhHoc;

    public SinhVien() {
    }

    public SinhVien(String ma, String ten, int tuoi, boolean gioiTinh, String nghanhHoc) {
        this.ma = ma;
        this.ten = ten;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
        this.nghanhHoc = nghanhHoc;
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

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNghanhHoc() {
        return nghanhHoc;
    }

    public void setNghanhHoc(String nghanhHoc) {
        this.nghanhHoc = nghanhHoc;
    }
}
