package BaiTap;

public class Bai1 {

    public int tinhTong(double a, double b){
        if (a % 1 != 0 || b % 1 != 0) {
            throw new IllegalArgumentException("Phai la so nguyen");
        }
        return (int) (a + b);
    }
}
