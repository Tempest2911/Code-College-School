package org.example.onchohungvaquyet;

public class Bai1 {

    public int sumOddNumbers(int n) {
        if (n < 1 || n > 1000) {
            throw new IllegalArgumentException("Giá trị n phải nằm trong khoảng 1-1000");
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                sum += i;
            }
        }
        return sum;
    }

}
