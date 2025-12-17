package org.example.ontap5_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Nationalized
    @Column(name = "ten_san_pham", length = 150)
    private String tenSanPham;

    @Size(max = 50)
    @Column(name = "ma_san_pham", length = 50)
    private String maSanPham;

    @Size(max = 100)
    @Nationalized
    @Column(name = "loai_san_pham", length = 100)
    private String loaiSanPham;

    @Size(max = 100)
    @Nationalized
    @Column(name = "nha_san_xuat", length = 100)
    private String nhaSanXuat;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ghi_chu")
    private String ghiChu;

    @OneToMany(mappedBy = "sanPham")
    @JsonIgnore
    private Set<DonDatHang> donDatHangs = new LinkedHashSet<>();

}