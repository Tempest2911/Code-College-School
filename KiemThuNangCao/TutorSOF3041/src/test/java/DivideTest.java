import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivideTest {
    private Divide div;

    @BeforeEach
    public void SetUp() {
        div = new Divide();
    }

    @Test
    public void PhanVungTuongDuong_ChiaGiaTriHopLe() {
        int result = div.divide(25, 5);
        int expected = 5;
        assertEquals(expected, result);

    }

    @Test
    public void KyThuatBien_chiaGiaTriHopLeLa0() {
        int result = div.divide(0, 1);
        int expected = 0;
        assertEquals(expected, result);

    }

    @Test
    public void KyThuatBien_chiaGiaTriHopLeLa1() {
        int result = div.divide(1, 1);
        int expected = 1;
        assertEquals(expected, result);

    }

    @Test
    public void KyThuatBien_chiaGiaTriHopLeLa99() {
        int result = div.divide(99, 1);
        int expected = 99;
        assertEquals(expected, result);

    }

    @Test
    public void KyThuatBien_chiaGiaTriHopLeLa100() {
        int result = div.divide(100, 1);
        int expected = 100;
        assertEquals(expected, result);
    }

    @Test
    public void KiemTraGiaTriKhongHopLeLaAm1() {
        assertThrows(IllegalArgumentException.class, () -> {
            div.divide(-1, 5);
        });
    }

    @Test
    public void KiemTraGiaTriKhongHopLeLa101() {
        assertThrows(IllegalArgumentException.class, () -> {
            div.divide(101, 5);
        });
    }
}
