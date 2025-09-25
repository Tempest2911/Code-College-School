package org.example.lab3;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MonHoc {
    private String maMon;
    private String tenMon;
    private Integer soTinChi;
    private String chuyenNghanh;
    private List<String> giangVien;
    private Boolean batBuoc;

    public MonHoc() {
    }

    public MonHoc(String maMon, String tenMon, Integer soTinChi, String chuyenNghanh, List<String> giangVien, Boolean batBuoc) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
        this.chuyenNghanh = chuyenNghanh;
        this.giangVien = giangVien;
        this.batBuoc = batBuoc;
    }
}
