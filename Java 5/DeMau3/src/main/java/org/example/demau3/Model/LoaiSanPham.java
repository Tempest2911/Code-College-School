package org.example.demau3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "loai_san_pham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "ma_loai", nullable = false, length = 20)
    private String maLoai;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "ten_loai", nullable = false, length = 100)
    private String tenLoai;

    @Size(max = 255)
    @Nationalized
    @Column(name = "mo_ta")
    private String moTa;

    @OneToMany(mappedBy = "idLoaiSanPham")
    private Set<SanPham> sanPhams = new LinkedHashSet<>();

}