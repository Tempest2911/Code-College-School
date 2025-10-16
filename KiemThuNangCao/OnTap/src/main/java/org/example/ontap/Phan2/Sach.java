package org.example.ontap.Phan2;

public class Sach {
    String id;
    String ten;
    int SoTrang;
    String tenTacGia;
    int lanTaiBan;

    public Sach() {
    }

    public Sach(String id, String ten, int soTrang, String tenTacGia, int lanTaiBan) {
        this.id = id;
        this.ten = ten;
        SoTrang = soTrang;
        this.tenTacGia = tenTacGia;
        this.lanTaiBan = lanTaiBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoTrang() {
        return SoTrang;
    }

    public void setSoTrang(int soTrang) {
        SoTrang = soTrang;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getLanTaiBan() {
        return lanTaiBan;
    }

    public void setLanTaiBan(int lanTaiBan) {
        this.lanTaiBan = lanTaiBan;
    }
}
