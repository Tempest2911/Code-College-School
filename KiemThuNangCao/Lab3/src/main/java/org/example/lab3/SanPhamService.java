package org.example.lab3;

import org.example.lab3.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamService {
    private final List<SanPham> danhSach = new ArrayList<>();

    // Thêm sản phẩm: soLuong > 0 và < 100
    public boolean them(SanPham sp) {
        if (sp.getSoLuong() <= 0 || sp.getSoLuong() >= 100) {
            return false;
        }
        danhSach.add(sp);
        return true;
    }

    // Sửa sản phẩm: kiểm tra maSanPham duy nhất và bắt đầu bằng "SP"
    public boolean sua(String id, SanPham spMoi) {
        for (SanPham sp : danhSach) {
            if (sp.getId().equals(id)) {
                if (!spMoi.getMaSanPham().startsWith("SP")) {
                    return false;
                }
                for (SanPham spCheck : danhSach) {
                    if (!spCheck.getId().equals(id) &&
                            spCheck.getMaSanPham().equals(spMoi.getMaSanPham())) {
                        return false; // mã sản phẩm trùng
                    }
                }
                sp.setMaSanPham(spMoi.getMaSanPham());
                sp.setTenSanPham(spMoi.getTenSanPham());
                sp.setGia(spMoi.getGia());
                sp.setMauSac(spMoi.getMauSac());
                sp.setKichThuoc(spMoi.getKichThuoc());
                sp.setSoLuong(spMoi.getSoLuong());
                return true;
            }
        }
        return false;
    }

    // Xóa sản phẩm
    public boolean xoa(String id) {
        return danhSach.removeIf(sp -> sp.getId().equals(id));
    }

    public List<SanPham> getDanhSach() {
        return danhSach;
    }
}
