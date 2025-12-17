package org.example.ontap6_backend.Repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LichChieuResponse {

    private Integer id;
    private String ngayChieu;
    private String phongChieu;
    private Double giaVe;
    private String tenPhim;
    private String daoDien;

}
