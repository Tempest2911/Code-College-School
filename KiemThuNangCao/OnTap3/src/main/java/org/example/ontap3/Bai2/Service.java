package org.example.ontap3.Bai2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private List<SanPham> danhSach = new ArrayList<>();

    public boolean them(SanPham sp) {
        if (sp == null) {
            throw new IllegalArgumentException("Sản phẩm không được null");
        }

        if (sp.getMa() == null || !sp.getMa().matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Mã sản phẩm chỉ được chứa chữ và số, không có ký tự đặc biệt");
        }

        if (sp.getTen() == null || !sp.getTen().matches("^[\\p{L}\\s0-9]+$")) {
            throw new IllegalArgumentException("Tên sản phẩm không được chứa ký tự đặc biệt");
        }

        if (sp.getSoLuong() < 1 || sp.getSoLuong() > 100) {
            throw new IllegalArgumentException("Số lượng không hợp lệ (1–100)");
        }

        for (SanPham s : danhSach) {
            if (s.getMa().equalsIgnoreCase(sp.getMa())) {
                throw new IllegalArgumentException("Mã sản phẩm đã tồn tại");
            }
        }

        danhSach.add(sp);
        return true;
    }

    public boolean sua(String ma, SanPham spMoi) {
        if (ma == null || ma.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã sản phẩm cần sửa không được trống");
        }
        if (spMoi == null) {
            throw new IllegalArgumentException("Sản phẩm mới không được null");
        }

        if (spMoi.getMa() == null || !spMoi.getMa().matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Mã sản phẩm chỉ được chứa chữ và số, không có ký tự đặc biệt");
        }

        if (spMoi.getTen() == null || !spMoi.getTen().matches("^[\\p{L}\\s0-9]+$")) {
            throw new IllegalArgumentException("Tên sản phẩm không được chứa ký tự đặc biệt");
        }

        if (spMoi.getSoLuong() < 1 || spMoi.getSoLuong() > 100) {
            throw new IllegalArgumentException("Số lượng không hợp lệ (1–100)");
        }

        for (int i = 0; i < danhSach.size(); i++) {
            SanPham sp = danhSach.get(i);
            if (sp.getMa().equalsIgnoreCase(ma)) {
                sp.setTen(spMoi.getTen());
                sp.setNamBaoHanh(spMoi.getNamBaoHanh());
                sp.setGia(spMoi.getGia());
                sp.setSoLuong(spMoi.getSoLuong());
                sp.setDanhMuc(spMoi.getDanhMuc());
                return true;
            }
        }

        throw new IllegalArgumentException("Không tìm thấy sản phẩm có mã: " + ma);
    }

    public boolean xoa(String ma) {
        if (ma == null || ma.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã sản phẩm cần xóa không được trống");
        }

        for (SanPham sp : danhSach) {
            if (sp.getMa().equalsIgnoreCase(ma)) {
                danhSach.remove(sp);
                return true;
            }
        }

        throw new IllegalArgumentException("Không tìm thấy sản phẩm có mã: " + ma);
    }

    public List<SanPham> timKiem(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Từ khóa tìm kiếm không được để trống");
        }

        String tuKhoa = keyword.trim().toLowerCase();

        List<SanPham> ketQua = danhSach.stream()
                .filter(sp ->
                        sp.getMa().toLowerCase().contains(tuKhoa)
                                || sp.getTen().toLowerCase().contains(tuKhoa)
                                || sp.getDanhMuc().toLowerCase().contains(tuKhoa))
                .collect(Collectors.toList());

        if (ketQua.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy sản phẩm nào phù hợp với từ khóa: " + keyword);
        }

        return ketQua;
    }
}
