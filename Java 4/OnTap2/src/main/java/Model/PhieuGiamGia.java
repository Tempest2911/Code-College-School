package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "loai_phieu_id")
    private LoaiPhieuGiamGia loaiPhieu;

    @Column(name = "ma")
    private String ma;

    @Nationalized
    @Column(name = "ten")
    private String ten;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Nationalized
    @Column(name = "loai_giam")
    private String loaiGiam;

    @Column(name = "dieu_kieu_toi_thieu")
    private String dieuKieuToiThieu;

    @Column(name = "gia_tri_toi_da")
    private String giaTriToiDa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LoaiPhieuGiamGia getLoaiPhieu() {
        return loaiPhieu;
    }

    public void setLoaiPhieu(LoaiPhieuGiamGia loaiPhieu) {
        this.loaiPhieu = loaiPhieu;
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

    public String getLoaiGiam() {
        return loaiGiam;
    }

    public void setLoaiGiam(String loaiGiam) {
        this.loaiGiam = loaiGiam;
    }

    public String getDieuKieuToiThieu() {
        return dieuKieuToiThieu;
    }

    public void setDieuKieuToiThieu(String dieuKieuToiThieu) {
        this.dieuKieuToiThieu = dieuKieuToiThieu;
    }

    public String getGiaTriToiDa() {
        return giaTriToiDa;
    }

    public void setGiaTriToiDa(String giaTriToiDa) {
        this.giaTriToiDa = giaTriToiDa;
    }

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(Integer id, LoaiPhieuGiamGia loaiPhieu, String ma, String ten, Integer soLuong, String loaiGiam, String dieuKieuToiThieu, String giaTriToiDa) {
        this.id = id;
        this.loaiPhieu = loaiPhieu;
        this.ma = ma;
        this.ten = ten;
        this.soLuong = soLuong;
        this.loaiGiam = loaiGiam;
        this.dieuKieuToiThieu = dieuKieuToiThieu;
        this.giaTriToiDa = giaTriToiDa;
    }
}