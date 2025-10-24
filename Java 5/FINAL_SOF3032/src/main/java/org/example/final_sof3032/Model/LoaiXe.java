package org.example.final_sof3032.Model;

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
@Table(name = "loai_xe")
public class LoaiXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Nationalized
    @Column(name = "ma_loai_xe", length = 20)
    private String maLoaiXe;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ten_loai_xe")
    private String tenLoaiXe;

    @Size(max = 255)
    @Nationalized
    @Column(name = "mo_ta")
    private String moTa;

    @Size(max = 255)
    @Nationalized
    @Column(name = "hang_san_xuat")
    private String hangSanXuat;

    @Column(name = "nam_ra_mat")
    private Integer namRaMat;

    @OneToMany(mappedBy = "idLoaiXe")
    private Set<Xe> xes = new LinkedHashSet<>();

}