package org.example.th03089_27102025;

import java.util.ArrayList;
import java.util.List;

public class SachService {

    List<Sach> danhSach = new ArrayList<>();

    public boolean them(Sach sachMoi) {
        if (sachMoi.getMaSach() == null || sachMoi.getMaSach().trim().isEmpty() || sachMoi.getTenSach() == null || sachMoi.getTenSach().trim().isEmpty() || sachMoi.getTacGia() == null || sachMoi.getTacGia().trim().isEmpty()) {
            throw new IllegalArgumentException("Các trường không được để trống");
        }

        if (sachMoi.getNamXuatBan() < 1990 || sachMoi.getNamXuatBan() > 2025) {
            throw new IllegalArgumentException("Năm phải nằm trong khoảng 1990-2025");
        }

        danhSach.add(sachMoi);
        return true;
    }

    public boolean sua(String ma, Sach sachMoi) {
        if (ma == null || ma.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã sách cần sửa không được trống");
        }

        if (sachMoi.getMaSach() == null || sachMoi.getMaSach().trim().isEmpty() || sachMoi.getTenSach() == null || sachMoi.getTenSach().trim().isEmpty() || sachMoi.getTacGia() == null || sachMoi.getTacGia().trim().isEmpty()) {
            throw new IllegalArgumentException("Các trường không được để trống");
        }

        if (sachMoi.getNamXuatBan() < 1990 || sachMoi.getNamXuatBan() > 2025) {
            throw new IllegalArgumentException("Năm phải nằm trong khoảng 1990-2025");
        }

        for (int i = 0; i < danhSach.size(); i++) {
            Sach sp = danhSach.get(i);
            if (sp.getMaSach().equalsIgnoreCase(ma)) {
                sp.setTenSach(sachMoi.getTenSach());
                sp.setTacGia(sachMoi.getTacGia());
                sp.setNamXuatBan(sachMoi.getNamXuatBan());
                sp.setGia(sachMoi.getGia());
                return true;
            }
        }

        throw new IllegalArgumentException("Không tìm thấy sách có mã: " + ma);
    }
}
