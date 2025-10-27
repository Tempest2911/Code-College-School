package org.example.ontap3.Bai22;

import java.util.ArrayList;
import java.util.List;

public class SinhVienService {
    List<SinhVien> list = new ArrayList<>();

    public boolean add(SinhVien s) {
        //tên sinh viên không được rỗng, không chứa số hoặc ký tự đặc biệt
        if (s.getTen() == null || !s.getTen().matches("^[\\p{L}\\s]+$")) {
            throw new IllegalArgumentException("Tên sinh viên không được rỗng và không chứa số hoặc ký tự đặc biệt");
        }
        //điểm sinh viên phải từ 0.0 đến 10.0
        if (s.getDiem() < 0.0 || s.getDiem() > 10.0) {
            throw new IllegalArgumentException("Điểm sinh viên phải từ 0.0 đến 10.0");
        }
        //tuổi sinh viên phải từ 18 đến 60
        if (s.getTuoi() < 18 || s.getTuoi() > 30) {
            throw new IllegalArgumentException("Tuổi sinh viên phải từ 18 đến 30");
        }
        list.add(s);
        return true;
    }

    public boolean sua(String id, SinhVien s) {
        //tên sinh viên không được rỗng, không chứa số hoặc ký tự đặc biệt
        if (s.getTen() == null || !s.getTen().matches("^[\\p{L}\\s]+$")) {
            throw new IllegalArgumentException("Tên sinh viên không được rỗng và không chứa số hoặc ký tự đặc biệt");
        }
        //điểm sinh viên phải từ 0.0 đến 10.0
        if (s.getDiem() < 0.0 || s.getDiem() > 10.0) {
            throw new IllegalArgumentException("Điểm sinh viên phải từ 0.0 đến 10.0");
        }
        //tuổi sinh viên phải từ 18 đến 60
        if (s.getTuoi() < 18 || s.getTuoi() > 30) {
            throw new IllegalArgumentException("Tuổi sinh viên phải từ 18 đến 30");
        }
        for (int i = 0; i < list.size(); i++) {
            SinhVien sv = list.get(i);
            if (sv.getId().equalsIgnoreCase(id)) {
                sv.setTen(s.getTen());
                sv.setDiem(s.getDiem());
                sv.setLop(s.getLop());
                sv.setTuoi(s.getTuoi());
                return true;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy sinh viên có mã: " + id);
    }


}
