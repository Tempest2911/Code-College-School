import org.example.ontap2.Phan1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SOF3041Test {


    private Phan1 p1;

    @BeforeEach
    void setUp() {
        p1 = new Phan1();
    }

    @Test
    public void TestBienTren() {
        int[] arg = {1000};
        assertTrue(p1.KiemTraTongHam1Den1000(arg));
    }

    @Test
    public void TestBienDuoi() {
        int[] arg = {1};
        assertTrue(p1.KiemTraTongHam1Den1000(arg));
    }

    @Test
    public void TestCanBienTren() {
        int[] arg = {999};
        assertTrue(p1.KiemTraTongHam1Den1000(arg));
    }

    @Test
    public void TestCanBienDuoi() {
        int[] arg = {2};
        assertTrue(p1.KiemTraTongHam1Den1000(arg));
    }

    @Test
    public void TestDuoiBienDuoi() {
        int[] arg = {0};
        assertFalse(p1.KiemTraTongHam1Den1000(arg));
    }

    @Test
    public void TestTrenBienTren() {
        int[] arg = {1001};
        assertFalse(p1.KiemTraTongHam1Den1000(arg));
    }
}
