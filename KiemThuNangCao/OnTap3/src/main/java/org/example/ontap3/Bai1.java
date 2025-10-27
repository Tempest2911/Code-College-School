package org.example.ontap3;

import static com.google.common.math.IntMath.isPrime;

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

    public int sumEvenNumbers(int n) {
        if (n < 1 || n > 1000) {
            throw new IllegalArgumentException("Giá trị n phải nằm trong khoảng 1-1000");
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public int tinhGiaiThua(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("Giá trị n phải nằm trong khoảng 1-1000");
        }

        int giaiThua = 1;

        for (int i = 1; i <= n; i++) {
            giaiThua *= i;
        }

        return giaiThua;
    }

    //Tính Trung bình cộng của mảng số nguyên
    public double tinhTrungBinhCong(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Mảng không được null hoặc rỗng");
        }

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        return (double) sum / arr.length;
    }

    //Tinh tuoi
    public int calculateAge(int birthYear) {
        int currentYear = 2025;
        if (birthYear > currentYear) {
            throw new IllegalArgumentException("Năm sinh không hợp lệ");
        } else if (birthYear < 1900) {
            throw new IllegalArgumentException("Năm sinh quá xa");
        }
        return currentYear - birthYear;
    }

    //Tính tổng số nguyên tố
    public int sumPrimeNumbers(int n) {
        if (n < 1 || n > 1000) {
            throw new IllegalArgumentException("Giá trị n phải nằm trong khoảng 1-1000");
        }

        int sum = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

}
