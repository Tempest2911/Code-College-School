package org.example.buoi5;

import java.util.ArrayList;
import java.util.List;

public class MonHocRepository {

    private static List<MonHoc> monHocs = new ArrayList<>();
    static {
        monHocs.add(new MonHoc("MH001", "Toán Cao Cấp", 4, "Toán", "Phong", true));
        monHocs.add(new MonHoc("MH002", "Lập Trình Java", 3, "Công Nghệ Thông Tin", "Hung", true));
        monHocs.add(new MonHoc("MH003", "Cơ Sở Dữ Liệu", 3, "Công Nghệ Thông Tin", "Quyet", false));
        monHocs.add(new MonHoc("MH004", "Mạng Máy Tính", 3, "Công Nghệ Thông Tin", "Binh", false));
    }

    public List<MonHoc> getMonHoc() {
        return monHocs;
    }

    public MonHoc timMonHocTheoID(String maMon) {
        return monHocs.stream().filter(ny -> ny.getMaMon().equalsIgnoreCase(maMon)).findFirst().orElse(null);
    }
}
