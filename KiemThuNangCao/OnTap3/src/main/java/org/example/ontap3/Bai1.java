package org.example.ontap3;

public class Bai1 {
    public int sumOddNumbers(int n) {
        if (n < 1 || n > 1000) {
            throw new IllegalArgumentException("Giá trị n phải nằm trong khoảng 1-1000");
        }

        int sum = 0;
        for (int i = 1; i <= n; i += 2) {
            sum += i;
        }

        return sum;
    }

    //tính tổng sô chẵn

    public int sumEvenNumbers(int n) {
        if (n < 1 || n > 1000) {
            throw new IllegalArgumentException("Giá trị n phải nằm trong khoảng 1-1000");
        }

        int sum = 0;
        for (int i = 1; i <= n; i += 2) {
            sum += i;
        }

        return sum;
    }
}
