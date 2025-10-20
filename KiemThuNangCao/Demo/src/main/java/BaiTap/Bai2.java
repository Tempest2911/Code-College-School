package BaiTap;

public class Bai2 {

    public double cong(double a, double b) {
        return a + b;
    }

    public double tru(double a, double b) {
        return a - b;
    }

    public double nhan(double a, double b) {
        return a * b;
    }

    public double chia(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Khong the chia cho 0");
        }
        return a / b;
    }
}
