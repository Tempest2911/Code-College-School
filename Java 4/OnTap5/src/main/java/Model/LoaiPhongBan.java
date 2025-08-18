package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "loai_phong_ban")
public class LoaiPhongBan {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ma", length = 50)
    private String ma;

    @Nationalized
    @Column(name = "ten", length = 50)
    private String ten;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Nationalized
    @Column(name = "ghi_chu", length = 1000)
    private String ghiChu;

    @OneToMany(mappedBy = "loaiPhongBan")
    private Set<PhongBan> phongBans = new LinkedHashSet<>();

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

    public Set<PhongBan> getPhongBans() {
        return phongBans;
    }

    public void setPhongBans(Set<PhongBan> phongBans) {
        this.phongBans = phongBans;
    }

}