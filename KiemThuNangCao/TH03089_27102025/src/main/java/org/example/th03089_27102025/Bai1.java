package org.example.th03089_27102025;

public class Bai1 {
    public boolean KiemTraSoNguyenDuong(int n) {
        if (n < 1 || n > 100) {
            throw new IllegalArgumentException("Giá trị n phải nằm trong khoảng 1-100");
        }  if (n <= 0) {
            throw new IllegalArgumentException("Giá trị n phải là số nguyên dương");
        }

        return true;
    }


}
