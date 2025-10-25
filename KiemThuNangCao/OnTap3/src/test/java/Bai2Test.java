import org.example.ontap3.Bai2.SanPham;
import org.example.ontap3.Bai2.Service;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Bai2Test {
    Service service = new Service();

    @Test
    void testAddHopLe() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        assertTrue(service.them(sp));
    }

    @Test
    void testSLBienTren() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 100, "Thời trang");
        assertTrue(service.them(sp));
    }

    @Test
    void testSLBienDuoi() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 1, "Thời trang");
        assertTrue(service.them(sp));
    }

    @Test
    void testSLCanBienDuoi() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 2, "Thời trang");
        assertTrue(service.them(sp));
    }

    @Test
    void testSLCanBienTren() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 99, "Thời trang");
        assertTrue(service.them(sp));
    }

    @Test
    void testSLNgoaiBienDuoi() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 0, "Thời trang");
        assertThrows(IllegalArgumentException.class, () -> service.them(sp));
    }

    @Test
    void testSLNgoaiBienTren() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 101, "Thời trang");
        assertThrows(IllegalArgumentException.class, () -> service.them(sp));
    }

    @Test
    void testAddNull() {
        assertThrows(IllegalArgumentException.class, () -> service.them(null));
    }

    @Test
    void testMaTrung() {
        SanPham sp1 = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 40, "Thời trang");
        SanPham sp2 = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 80, "Thời trang");
        assertTrue(service.them(sp1));
        assertThrows(IllegalArgumentException.class, () -> service.them(sp2));
    }

    // ====================== TEST SỬA ======================

    @Test
    void testSuaHopLe() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP01", "Áo thun", 2025, 300.0f, 30, "Thời trang");
        assertTrue(service.sua("SP01", spMoi));
    }

    @Test
    void testSuaSPBienTren() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 100, "Thời trang");
        assertTrue(service.sua("SP01", spMoi));
    }

    @Test
    void testSuaSPBienDuoi() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 1, "Thời trang");
        assertTrue(service.sua("SP01", spMoi));
    }

    @Test
    void testSuaSPCanBienTren() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 99, "Thời trang");
        assertTrue(service.sua("SP01", spMoi));
    }

    @Test
    void testSuaSPCanBienDuoi() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 2, "Thời trang");
        assertTrue(service.sua("SP01", spMoi));
    }

    @Test
    void testSuaSPNgoaiBienTren() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 0, "Thời trang");
        assertThrows(IllegalArgumentException.class, () -> service.sua("SP01", spMoi));
    }

    @Test
    void testSuaSPNgoaiBienDuoi() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 1001, "Thời trang");
        assertThrows(IllegalArgumentException.class, () -> service.sua("SP01", spMoi));
    }

    @Test
    void testSuaMaKhongTonTai() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        SanPham spMoi = new SanPham("SP02", "Áo thun", 2025, 300.0f, 30, "Thời trang");
        assertThrows(IllegalArgumentException.class, () -> service.sua("SP03", spMoi));
    }

    // ====================== TEST XÓA ======================

    @Test
    void testXoaHopLe() {
        SanPham sp = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        service.them(sp);
        assertTrue(service.xoa("SP01"));
    }

    @Test
    public void testTimKiem_TheoTen() {
        Service service = new Service(); // tạo mới để không phụ thuộc test khác

        // Thêm sản phẩm trước khi tìm
        SanPham sp1 = new SanPham("SP01", "Áo sơ mi", 2024, 250.5f, 50, "Thời trang");
        SanPham sp2 = new SanPham("SP02", "Quần jean", 2024, 300.0f, 30, "Thời trang");
        service.them(sp1);
        service.them(sp2);

        // Thực hiện tìm kiếm
        List<SanPham> result = service.timKiem("Quần");

        // Kiểm tra kết quả
        Assert.assertFalse(result.isEmpty(), "Không tìm thấy sản phẩm chứa từ khóa 'Áo'");
        Assert.assertEquals(result.get(0).getMa(), "SP02");
        System.out.println("✅ Tìm thấy: " + result.size() + " sản phẩm có chữ 'Áo'");
    }

    @Test()
    public void testTimKiem_KhongThay() {
        assertThrows(IllegalArgumentException.class, () ->  service.timKiem("Balo"));
    }

}
