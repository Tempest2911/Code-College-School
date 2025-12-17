package org.example.ontap7_backend.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DonHangResponse {
    private Integer id;
    private String maDonHang;
    private String ngayDat;
    private Double tongTien;
    private String tenKhachHang;
    private String diaChi;
}
