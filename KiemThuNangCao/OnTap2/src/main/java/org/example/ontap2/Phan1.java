package org.example.ontap2;

public class Phan1 {

    public static int TinhTongHamTu1Den1000(int[] arg){
        int tong = 0;
        for (int i : arg){
            tong += i;
        }
        return tong;
    }

    public static boolean KiemTraTongHam1Den1000(int[] arg){
        int tong = TinhTongHamTu1Den1000(arg);
        return tong >= 1 && tong <= 1000;
    }
}
