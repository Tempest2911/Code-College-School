import org.example.onchohungvaquyet.Bai1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Bai1Test {

    Bai1 bai1 = new Bai1();

    @Test
    public void testHopLe() {
        assertEquals(62500 ,bai1.sumOddNumbers(500) );
    }

    @Test
    public void testBienTren() {
        assertEquals(250000 ,bai1.sumOddNumbers(1000) );
    }

    @Test
    public void testBienDuoi() {
        assertEquals(1 ,bai1.sumOddNumbers(1) );
    }

    @Test
    public void testNgoaiBienTren() {
        assertThrows(IllegalArgumentException.class, () -> {bai1.sumOddNumbers(1001);});
    }

    @Test
    public void testNgoaiBienDuoi() {
        assertThrows(IllegalArgumentException.class, () -> {bai1.sumOddNumbers(0);});
    }
}
