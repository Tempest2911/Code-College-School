package org.example.thicuoimon_backend.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DonDatHangResponse {
    private Integer id;
    private String maDonHang;
    private String ngayDat;
    private Double tongTien;
    private String tenSanPham;
    private String nhaSanXuat;


}
