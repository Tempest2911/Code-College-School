package org.example.ontap4_backend.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.ontap4_backend.entity.KhachHang;

@Getter
@Data
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
