package org.example.kiemtra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExLoverRepository {

    private static List<ExLover> lovers = new ArrayList<>();
    static {
        lovers.add(new ExLover(1, "Nguyen Van A", LocalDateTime.of(2023, 5, 20, 14, 30), "Khong con yeu nhau", 5));
        lovers.add(new ExLover(2, "Tran Thi B", LocalDateTime.of(2023, 6, 15, 10, 0), "Lua dao tinh cam", 8));
        lovers.add(new ExLover(3, "Le Van C", LocalDateTime.of(2023, 7, 10, 16, 45), "Khac biet quan diem", 3));
    }

    public List<ExLover> getNguoiYeuCu() {
        return lovers;
    }

    public ExLover timNguoiYeuCuTheoID(int id) {
        return lovers.stream().filter(ny -> ny.getId() == id).findFirst().orElse(null);
    }
}
