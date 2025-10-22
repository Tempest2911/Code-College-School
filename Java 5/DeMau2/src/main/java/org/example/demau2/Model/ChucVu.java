package org.example.demau2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "chuc_vu")
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Nationalized
    @Column(name = "ma_chuc_vu", length = 20)
    private String maChucVu;

    @Size(max = 100)
    @Nationalized
    @Column(name = "ten_chuc_vu", length = 100)
    private String tenChucVu;

    @Size(max = 255)
    @Nationalized
    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "he_so_luong")
    private Double heSoLuong;

    @OneToMany(mappedBy = "idChucVu")
    private Set<NhanVien> nhanViens = new LinkedHashSet<>();

}