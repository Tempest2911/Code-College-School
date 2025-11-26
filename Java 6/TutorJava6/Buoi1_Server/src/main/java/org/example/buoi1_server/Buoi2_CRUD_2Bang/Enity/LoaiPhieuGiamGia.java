package org.example.buoi1_server.Buoi2_CRUD_2Bang.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "loai_phieu_giam_gia")
public class LoaiPhieuGiamGia {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Nationalized
    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "loaiPhieu")
    private Set<PhieuGiamGia> phieuGiamGias = new LinkedHashSet<>();

}