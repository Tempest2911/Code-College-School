package org.example.ontap.Phan2;

import java.util.ArrayList;
import java.util.List;

public class SachService {

    List<Sach> danhSach = new ArrayList<>();

    public boolean themSach(Sach sach) {
        //tên sách không chứa ký tự đặc biệt
        if (sach.getTen().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("Tên sách không được chứa ký tự đặc biệt");
        } else if(sach.getSoTrang() <0 || sach.getSoTrang() >50){
            throw new IllegalArgumentException("Số trang phải lớn hơn 0 và nhỏ hơn 50");
        }
        danhSach.add(sach);
        return true;
    }

    public boolean suaSach(String id, Sach sach) {

        for (Sach sach1 : danhSach) {
            if (sach1.getId().equals(id)) {
                if (sach.getTen().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                    throw new IllegalArgumentException("Tên sách không được chứa ký tự đặc biệt");
                }else if(sach.getSoTrang() <0 || sach.getSoTrang() >50){
                    throw new IllegalArgumentException("Số trang phải lớn hơn 0 và nhỏ hơn 1000");
                }
                sach1.setId(sach.getId());
                sach1.setTen(sach.getTen());
                sach1.setSoTrang(sach.getSoTrang());
                sach1.setTenTacGia(sach.getTenTacGia());
                sach1.setLanTaiBan(sach.getLanTaiBan());
                return true;
            }
        }
        return false;
    }


}
