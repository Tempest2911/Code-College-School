package org.example.buoi1_server.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bai_hat")
public class BaiHat {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "ten_bai_hat", length = 100)
    private String tenBaiHat;

    @Nationalized
    @Column(name = "ten_tac_gia", length = 100)
    private String tenTacGia;

    @Column(name = "thoi_luong")
    private Integer thoiLuong;

    @Column(name = "ngay_san_xuat")
    private LocalDate ngaySanXuat;

    @Column(name = "gia")
    private Double gia;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ca_si_id")
    private CaSi caSi;

    @Column(name = "phat_hanh_dia")
    private Boolean phatHanhDia;

    @Column(name = "ngay_ra_mat")
    private LocalDate ngayRaMat;

}