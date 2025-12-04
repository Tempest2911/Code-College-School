package org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuGiamGiaResponse {
    // Mã phiếu, Tên phiếu, Số lượng, Loại giảm, Mã loại phiếu, Tên loại phiếu

    private String maPhieu;

    private String tenPhieu;

    private Integer soLuong;

    private String loai;

    private String maLoaiPhieu;

    private String tenLoaiPhieu;


}
