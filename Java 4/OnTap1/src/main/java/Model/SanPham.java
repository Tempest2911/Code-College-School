package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ma", length = 20)
    private String ma;

    @Nationalized
    @Column(name = "ten", length = 30)
    private String ten;

    @Nationalized
    @Column(name = "mota", length = 30)
    private String mota;

    @Nationalized
    @Column(name = "website", length = 30)
    private String website;

    @Column(name = "gia_ban", precision = 20)
    private BigDecimal giaBan;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "id_loai_sp")
    private Integer idLoaiSp;

    @Column(name = "trang_thai")
    private Integer trangThai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getIdLoaiSp() {
        return idLoaiSp;
    }

    public void setIdLoaiSp(Integer idLoaiSp) {
        this.idLoaiSp = idLoaiSp;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

}