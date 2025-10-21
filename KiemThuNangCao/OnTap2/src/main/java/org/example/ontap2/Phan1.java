package org.example.ontap2;

public class Phan1 {

    public int sum(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Array cannot be null");
        int total = 0;
        for (int n : arr) {
            total += n;
        }
        return total;
    }

}
