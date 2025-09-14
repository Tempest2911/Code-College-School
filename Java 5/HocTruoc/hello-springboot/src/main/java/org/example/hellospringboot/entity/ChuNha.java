package org.example.hellospringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "chu_nha")
public class ChuNha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "ten", length = 100)
    private String ten;

    @Nationalized
    @Column(name = "dia_chi", length = 200)
    private String diaChi;

    @Nationalized
    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Nationalized
    @Column(name = "email", length = 100)
    private String email;

}