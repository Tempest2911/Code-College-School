/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;

/**
 *
 * @author duck
 */
public class DocGiaInFo {

    public String hoTen;
    public String sdt;
    public String cccd;

    public DocGiaInFo() {
    }

    public DocGiaInFo(String hoTen, String sdt, String cccd) {
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.cccd = cccd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

}
