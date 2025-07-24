/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;

/**
 *
 * @author MINHDUC
 */
public class NhanVien {
    public int nhanVienID;
    public String NameAC;
    public String Pass;
    public String Role;
    public String tenNhanVien;
    public String cccd;
    public int namSinh;
    public String sdt;
    public String email;
    public String gioiTinh;
    public String Avatar;

    public NhanVien() {
    }

    public NhanVien(int nhanVienID, String NameAC, String Pass, String Role, String tenNhanVien, String cccd, int namSinh, String sdt, String email, String gioiTinh, String Avatar) {
        this.nhanVienID = nhanVienID;
        this.NameAC = NameAC;
        this.Pass = Pass;
        this.Role = Role;
        this.tenNhanVien = tenNhanVien;
        this.cccd = cccd;
        this.namSinh = namSinh;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.Avatar = Avatar;
    }

    public int getNhanVienID() {
        return nhanVienID;
    }

    public void setNhanVienID(int nhanVienID) {
        this.nhanVienID = nhanVienID;
    }

    public String getNameAC() {
        return NameAC;
    }

    public void setNameAC(String NameAC) {
        this.NameAC = NameAC;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    
    
}
