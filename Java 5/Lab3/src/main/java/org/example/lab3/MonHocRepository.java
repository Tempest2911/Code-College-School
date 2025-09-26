package org.example.lab3;

import java.util.ArrayList;
import java.util.List;

public class MonHocRepository {

    private static List<MonHoc> monHocs = new ArrayList<>();
    static {
        monHocs.add(new MonHoc("MH001", "Buôn bán máy bay", 4, "Digital Marketing", List.of("MinhDQ8"), true));
        monHocs.add(new MonHoc("MH002", "Lập Trình Java 5", 3, "Công Nghệ Thông Tin", List.of("DungNA29"), true));
        monHocs.add(new MonHoc("MH003", "Vẽ SIGMA", 3, "Thiết kế đồ họa", List.of("TienNH21"), false));
        monHocs.add(new MonHoc("MH004", "Ferrari", 3, "Ô tô cơ khí", List.of("NguyenVV4"), false));
    }

    public List<MonHoc> getMonHoc() {
        return monHocs;
    }

    public MonHoc timMonHocTheoID(String maMon) {
        return monHocs.stream().filter(ny -> ny.getMaMon().equalsIgnoreCase(maMon)).findFirst().orElse(null);
    }
}
