import org.example.ontap.Phan1.Bai1Serrvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Bai1Test {

    private Bai1Serrvice bai1Serrvice;

    @BeforeEach
    void setUp() {
        bai1Serrvice = new Bai1Serrvice();
    }

    @Test
    void testAllScoresAtLowerBoundary() {
        assertFalse(bai1Serrvice.tinhTB(0, 0, 0));
    }

    @Test
    void testAllScoresAtUpperBoundary() {
        assertTrue(bai1Serrvice.tinhTB(10, 10, 10));
    }

    @Test
    void testScoreBelowLowerBoundary() {
        assertThrows(IllegalArgumentException.class, () -> bai1Serrvice.tinhTB(-1, 5, 5));
    }

    @Test
    void testScoreAboveUpperBoundary() {
        assertThrows(IllegalArgumentException.class, () -> bai1Serrvice.tinhTB(5, 5, 11));
    }

    @Test
    void testAverageExactlyFive() {
        assertTrue(bai1Serrvice.tinhTB(6, 5, 4));
    }
}
