package org.example.ontapasm.Model;

public class Loa {

    private Integer maLoa;
    private String tenLoa;
    private Double congSuat;
    private String mauSac;
    private String kenhAmThanh;


    public Loa() {

    }

    public Loa(Integer maLoa, String tenLoa, Double congSuat, String mauSac, String kenhAmThanh) {
        this.maLoa = maLoa;
        this.tenLoa = tenLoa;
        this.congSuat = congSuat;
        this.mauSac = mauSac;
        this.kenhAmThanh = kenhAmThanh;
    }

    public Integer getMaLoa() {
        return maLoa;
    }

    public void setMaLoa(Integer maLoa) {
        this.maLoa = maLoa;
    }

    public String getTenLoa() {
        return tenLoa;
    }

    public void setTenLoa(String tenLoa) {
        this.tenLoa = tenLoa;
    }

    public Double getConSuat() {
        return congSuat;
    }

    public void setConSuat(Double conSuat) {
        this.congSuat = conSuat;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKenhAmThanh() {
        return kenhAmThanh;
    }

    public void setKenhAmThanh(String kenhAmThanh) {
        this.kenhAmThanh = kenhAmThanh;
    }
}
