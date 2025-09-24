package org.example.buoi5;

import java.util.ArrayList;
import java.util.List;

public class NguoiYeuRepository {

    private static List<NguoiYeu> danhSachNguoiYeu = new ArrayList<>();
    static {
        danhSachNguoiYeu.add(new NguoiYeu("Lan", false, 1995, 1.65));
        danhSachNguoiYeu.add(new NguoiYeu("Hoa", false, 1996, 1.70));
        danhSachNguoiYeu.add(new NguoiYeu("Huy", true, 1994, 1.75));
        danhSachNguoiYeu.add(new NguoiYeu("Minh", true, 1993, 1.80));
    }

    public List<NguoiYeu> getNguoiYeu() {
        return danhSachNguoiYeu;
    }

    public NguoiYeu timNguoiYeuTheoTen(String nickname) {
        return danhSachNguoiYeu.stream().filter(ny -> ny.getNickname().equalsIgnoreCase(nickname)).findFirst().orElse(null);
    }
}
