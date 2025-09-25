package org.example.lab3.Lab32;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonHoc {
    private String maMon;
    private String tenMon;
    private Integer soTinChi;
    private String chuyenNghanh;
    private String giangVien;
    private Boolean batBuoc;

    public MonHoc() {
    }

    public MonHoc(String maMon, String tenMon, Integer soTinChi, String chuyenNghanh, String giangVien, Boolean batBuoc) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
        this.chuyenNghanh = chuyenNghanh;
        this.giangVien = giangVien;
        this.batBuoc = batBuoc;
    }
}
