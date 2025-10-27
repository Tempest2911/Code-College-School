package org.example.ontap3.Bai22;

public class SinhVien {
    String id;
    String ten;
    Double diem;
    String lop;
    int tuoi;

    public SinhVien(String id, String ten, Double diem, String lop, int tuoi) {
        this.id = id;
        this.ten = ten;
        this.diem = diem;
        this.lop = lop;
        this.tuoi = tuoi;
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

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
}
