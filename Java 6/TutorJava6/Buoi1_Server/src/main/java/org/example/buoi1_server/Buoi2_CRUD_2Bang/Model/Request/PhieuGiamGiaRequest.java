package org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuGiamGiaRequest {

    // Cac thuoc tinh con lai (BeanUtils)
    @NotBlank(message = "Ma khong duoc de trong")
    private String ma;

    @NotBlank(message = "Ten khong duoc de trong")
    private String ten;

    @NotNull(message = "So luong khong duoc de trong")
    private Integer soLuong;

    @NotBlank(message = "Loai giam khong de trong")
    private String loaiGiam;

    // Rieng khoa ngoai chung ta se mapping kieu khac
    @NotNull(message = "Phai chon phieu giam gia")
    private  Integer loaiPhieuID;
}
