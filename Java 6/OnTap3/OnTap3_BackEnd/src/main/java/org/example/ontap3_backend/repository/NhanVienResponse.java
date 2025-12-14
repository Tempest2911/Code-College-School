package org.example.ontap3_backend.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.ontap3_backend.entity.ChucVu;

@Data
@Getter
@Setter
@AllArgsConstructor
public class NhanVienResponse {
private Integer id;
    private String maNhanVien;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String maChucVu;
    private String tenChucVu;
}
