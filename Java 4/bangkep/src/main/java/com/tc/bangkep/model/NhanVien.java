package com.tc.bangkep.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")

public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ho_ten")
    private String hoten;

    @Column(name = "chuc_vu")
    private String chucvu;

    @Column(name = "luong")
    private Integer luong;

    @Column(name = "dang_lam_viec")
    private Boolean dangLamViec;

    @ManyToOne
    @JoinColumn(name = "phong_ban_id", referencedColumnName = "id")
    private PhongBan phongBan;

}
