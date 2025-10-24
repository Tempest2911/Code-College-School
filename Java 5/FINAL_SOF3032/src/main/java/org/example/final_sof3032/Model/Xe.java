package org.example.final_sof3032.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "xe")
public class Xe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Nationalized
    @Column(name = "ma_xe", length = 20)
    private String maXe;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ten_xe")
    private String tenXe;

    @Column(name = "gia", precision = 21, scale = 1)
    private BigDecimal gia;

    @Column(name = "ngay_san_xuat")
    private LocalDate ngaySanXuat;

    @Column(name = "so_cho_ngoi")
    private Integer soChoNgoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_loai_xe")
    private LoaiXe idLoaiXe;

}