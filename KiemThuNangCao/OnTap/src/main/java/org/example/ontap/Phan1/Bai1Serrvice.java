package org.example.ontap.Phan1;

public class Bai1Serrvice {

    public boolean tinhTB(int diemToan, int diemLy, int diemHoa) {
        if (diemToan < 0 || diemToan > 10 || diemLy < 0 || diemLy > 10 || diemHoa < 0 || diemHoa > 10) {
            throw new IllegalArgumentException("Điểm phải từ 0 đến 10");
        }
        float tb = (diemToan + diemLy + diemHoa) / 3.0f;
        return tb >= 5.0f;
    }

}
