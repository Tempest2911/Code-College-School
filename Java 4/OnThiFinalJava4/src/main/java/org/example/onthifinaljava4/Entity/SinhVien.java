package org.example.onthifinaljava4.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SinhVien {
    //ma - String, ten - String, tuoi - int, gioiTinh - boolean, nganhHoc - String
    private String ma;
    private String ten;
    private Integer tuoi;
    private Boolean goitinh;
    private String nganhhoc;
}
