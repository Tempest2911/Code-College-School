package org.example.ontap2backend.repository;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ChamCongResponse {
    private Integer id;
    private String ngayCham;
    private Double soGioLam;
    private Double phat;
    private String tenNhanVien;
    private String phongBan;
}
