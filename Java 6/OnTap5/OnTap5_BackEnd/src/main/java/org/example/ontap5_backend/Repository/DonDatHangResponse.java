package org.example.ontap5_backend.Repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DonDatHangResponse {
    private Integer id;
    private String maDonHang;
    private String ngayDat;
    private Double tongTien;
    private String tenSanPham;
    private String nhaSanXuat;


}
