import org.example.th03089_27102025.SachService;
import org.example.th03089_27102025.Sach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Bai2Test {

    SachService sachService = new SachService();

    @Test
    public void testThemHopLe() {
        Sach sach = new Sach("S01", "Sigma", "Phong", 2007, 999);
        assertTrue(sachService.them(sach));
        Sach sachMoi = new Sach("S01", "Sigma", "Quyet", 2000, 999);
        assertTrue(sachService.sua("S01", sachMoi));
    }

    @Test
    public void testSuaBienTren() {
        Sach sach = new Sach("S01", "Sigma", "Phong", 2000, 999);
        assertTrue(sachService.them(sach));
        Sach sachMoi = new Sach("S01", "Sigma", "Phong", 2025, 999);
        assertTrue(sachService.sua("S01", sachMoi));
    }

    @Test
    public void testSuaBienDuoi() {
        Sach sach = new Sach("S01", "Sigma", "Phong", 2000, 999);
        assertTrue(sachService.them(sach));
        Sach sachMoi = new Sach("S01", "Sigma", "Phong", 1990, 999);
        assertTrue(sachService.sua("S01", sachMoi));
    }

    @Test
    public void testSuaCanBienDuoi() {
        Sach sach = new Sach("S01", "Sigma", "Phong", 2000, 999);
        assertTrue(sachService.them(sach));
        Sach sachMoi = new Sach("S01", "Sigma", "Phong", 1991, 999);
        assertTrue(sachService.sua("S01", sachMoi));
    }

    @Test
    public void testSuaCanBienTren() {
        Sach sach = new Sach("S01", "Sigma", "Phong", 2000, 999);
        assertTrue(sachService.them(sach));
        Sach sachMoi = new Sach("S01", "Sigma", "Phong", 2024, 999);
        assertTrue(sachService.sua("S01", sachMoi));
    }

    @Test
    public void testSuaNullKhongHopLe() {
        Sach sach = new Sach("S01", "Sigma", "Phong", 2000, 999);
        assertTrue(sachService.them(sach));
        Sach sachMoi = new Sach("S01", null, null, 2024, 999);
        assertThrows(IllegalArgumentException.class, () -> sachService.sua("S01", sachMoi));
    }
}
