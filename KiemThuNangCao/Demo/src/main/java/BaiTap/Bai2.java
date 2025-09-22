package BaiTap;

public class Bai2 {

    public int tinhTong(double a, double b){
        if (b == 0) {
            throw new IllegalArgumentException("Khong the chia cho 0");
        }
        return (int) (a / b);
    }
}
