package org.example.lab3;

import org.example.lab3.SanPham;
import org.example.lab3.SanPhamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SanPhamServiceTest {
    private SanPhamService service;

    @BeforeEach
    void setUp() {
        service = new SanPhamService();
    }

    // Giá trị biên: soLuong = 0 (không hợp lệ)
    @Test
    void testThem_SoLuongBang0() {
        SanPham sp = new SanPham("1", "SP01", "Sản phẩm A", 1000, "Đỏ", "M", 0);
        assertFalse(service.them(sp));
    }

    // Giá trị biên: soLuong = 1 (hợp lệ)
    @Test
    void testThem_SoLuongBang1() {
        SanPham sp = new SanPham("2", "SP02", "Sản phẩm B", 2000, "Xanh", "L", 1);
        assertTrue(service.them(sp));
    }

    // Giá trị biên: soLuong = 99 (hợp lệ)
    @Test
    void testThem_SoLuongBang99() {
        SanPham sp = new SanPham("3", "SP03", "Sản phẩm C", 3000, "Vàng", "XL", 99);
        assertTrue(service.them(sp));
    }

    // Giá trị biên: soLuong = 100 (không hợp lệ)
    @Test
    void testThem_SoLuongBang100() {
        SanPham sp = new SanPham("4", "SP04", "Sản phẩm D", 4000, "Đen", "S", 100);
        assertFalse(service.them(sp));
    }

    // Trường hợp trung bình hợp lệ
    @Test
    void testThem_SoLuongHopLeGiuaKhoang() {
        SanPham sp = new SanPham("5", "SP05", "Sản phẩm E", 5000, "Trắng", "M", 50);
        assertTrue(service.them(sp));
    }

    @Test
    void testSua_MaSanPhamKhongBatDauSP() {
        SanPham sp = new SanPham("1", "SP01", "Sản phẩm A", 1000, "Đỏ", "M", 10);
        service.them(sp);

        SanPham spMoi = new SanPham("1", "XX01", "Sản phẩm A mới", 2000, "Xanh", "L", 20);
        assertFalse(service.sua("1", spMoi));
    }

    @Test
    void testSua_MaSanPhamBiTrung() {
        SanPham sp1 = new SanPham("1", "SP01", "Sản phẩm A", 1000, "Đỏ", "M", 10);
        SanPham sp2 = new SanPham("2", "SP02", "Sản phẩm B", 2000, "Xanh", "L", 20);
        service.them(sp1);
        service.them(sp2);

        SanPham spMoi = new SanPham("1", "SP02", "Sản phẩm A mới", 3000, "Vàng", "XL", 30);
        assertFalse(service.sua("1", spMoi));
    }

    @Test
    void testSua_HopLe() {
        SanPham sp = new SanPham("1", "SP01", "Sản phẩm A", 1000, "Đỏ", "M", 10);
        service.them(sp);

        SanPham spMoi = new SanPham("1", "SP10", "Sản phẩm A mới", 2000, "Xanh", "L", 20);
        assertTrue(service.sua("1", spMoi));
    }

}
