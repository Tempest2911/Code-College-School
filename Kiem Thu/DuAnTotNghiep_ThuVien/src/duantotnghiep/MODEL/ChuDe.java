/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package duantotnghiep.MODEL;

/**
 *
 * @author thuyz
 */
public class ChuDe {
    int chudeid;
    String tenchude;

    public ChuDe() {
    }

    public ChuDe(int chudeid, String tenchude) {
        this.chudeid = chudeid;
        this.tenchude = tenchude;
    }

    public int getChudeid() {
        return chudeid;
    }

    public void setChudeid(int chudeid) {
        this.chudeid = chudeid;
    }

    public String getTenchude() {
        return tenchude;
    }

    public void setTenchude(String tenchude) {
        this.tenchude = tenchude;
    }
    
    
}
