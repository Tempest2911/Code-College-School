package org.example.ontap1.repository;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DonHangResponse {
    private Integer id;
    private String maDonHang;
    private LocalDate ngayDat;
    private Double tongTien;
    private String tenKhachHang;
    private String diaChi;

    public DonHangResponse(Integer id, String maDonHang, LocalDate ngayDat, Double tongTien, String tenKhachHang, String diaChi) {
        this.id = id;
        this.maDonHang = maDonHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
    }
}