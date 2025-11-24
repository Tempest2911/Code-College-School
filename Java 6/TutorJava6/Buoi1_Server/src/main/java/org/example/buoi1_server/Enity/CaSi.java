package org.example.buoi1_server.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ca_si")
public class CaSi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "ten_ca_si", length = 100)
    private String tenCaSi;

    @Nationalized
    @Column(name = "que_quan", length = 100)
    private String queQuan;

    @Column(name = "tuoi")
    private Integer tuoi;

    @Nationalized
    @Column(name = "cong_ty", length = 100)
    private String congTy;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @OneToMany(mappedBy = "caSi")
    private Set<BaiHat> baiHats = new LinkedHashSet<>();

}