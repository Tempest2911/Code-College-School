package org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Request;

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

    private String ma;

    private String ten;

    private Integer soLuong;

    private String loaiGiam;

    // Rieng khoa ngoai mapping kieu khac
    private Integer loaiPhieuID;
}
