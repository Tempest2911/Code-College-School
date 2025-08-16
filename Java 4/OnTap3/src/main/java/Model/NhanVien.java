package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "nhan_vien")
public class NhanVien {

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
    @Column(name = "ten_dem", length = 30)
    private String tenDem;

    @Nationalized
    @Column(name = "ho", length = 30)
    private String ho;

    @Nationalized
    @Column(name = "gioi_tinh", length = 10)
    private String gioiTinh;

    @Nationalized
    @Column(name = "dia_chi", length = 100)
    private String diaChi;

    @Column(name = "sdt", length = 30)
    private String sdt;

    @Column(name = "mat_khau", length = 50)
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_cv")
    private ChucVu idCv;

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

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public ChucVu getIdCv() {
        return idCv;
    }

    public void setIdCv(ChucVu idCv) {
        this.idCv = idCv;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }


    public NhanVien() {
    }

    public NhanVien(Integer id, String ma, String ten, String tenDem, String ho, String gioiTinh, String diaChi, String sdt, String matKhau, ChucVu idCv, Integer trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.matKhau = matKhau;
        this.idCv = idCv;
        this.trangThai = trangThai;
    }
}