package org.example.ontap6_backend.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "lich_chieu")
public class LichChieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phim_id")
    private Phim phim;

    @NotEmpty(message = "Ngày chiếu không được để trống")
    @Column(name = "ngay_chieu")
    private String ngayChieu;

    @Column(name = "gio_chieu")
    private String gioChieu;

    @NotEmpty(message = "Phòng chiếu không được để trống")
    @Size(max = 50)
    @Nationalized
    @Column(name = "phong_chieu", length = 50)
    private String phongChieu;

    @NotNull(message = "Giá vé không được để trống")
    @Column(name = "gia_ve")
    private Double giaVe;

    @Size(max = 50)
    @Nationalized
    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Size(max = 100)
    @Nationalized
    @Column(name = "nguoi_xu_ly", length = 100)
    private String nguoiXuLy;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "so_ghe_trong")
    private Integer soGheTrong;

}