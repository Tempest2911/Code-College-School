import org.example.th03089_27102025.Bai1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Bai1Test {
    Bai1 bai1 = new Bai1();

    @Test
    void testHopLe() {
        assertTrue(bai1.KiemTraSoNguyenDuong(80));
    }

    @Test
    void testCanBienTren() {
        assertTrue(bai1.KiemTraSoNguyenDuong(99));
    }

    @Test
    void testCanBienDuoi() {
        assertTrue(bai1.KiemTraSoNguyenDuong(2));
    }

    @Test
    void testNgoaiBienTren() {
        assertThrows(IllegalArgumentException.class, () -> bai1.KiemTraSoNguyenDuong(999));
    }

    @Test
    void testNgoaiBienDuoi() {
        assertThrows(IllegalArgumentException.class, () -> bai1.KiemTraSoNguyenDuong(0));
    }
}
