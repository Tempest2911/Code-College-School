package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "phong_ban")
public class PhongBan {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "loai_phong_ban_id")
    private LoaiPhongBan loaiPhongBan;

    @Column(name = "ma", length = 20)
    private String ma;

    @Nationalized
    @Column(name = "ten", length = 50)
    private String ten;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Nationalized
    @Column(name = "ghi_chu", length = 1000)
    private String ghiChu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LoaiPhongBan getLoaiPhongBan() {
        return loaiPhongBan;
    }

    public void setLoaiPhongBan(LoaiPhongBan loaiPhongBan) {
        this.loaiPhongBan = loaiPhongBan;
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

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public PhongBan() {
    }

    public PhongBan(Integer id, LoaiPhongBan loaiPhongBan, String ma, String ten, Integer soLuong, String ghiChu) {
        this.id = id;
        this.loaiPhongBan = loaiPhongBan;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }
}