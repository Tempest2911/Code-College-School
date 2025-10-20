import BaiTap.Bai1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bai1Test {
    private Bai1 bai1;

    @BeforeEach
    public void setUp() {
        bai1 = new Bai1();
    }

    @Test
    public void test1() {
        int result = bai1.tinhTong(2, 3);
        int expected = 5;
        assertEquals(expected, result);
    }

    @Test
    public void test2() {
        int result = bai1.tinhTong(2, 3.7);
        int expected = 5;
        assertEquals(expected, result);
    }

    @Test
    public void test3() {
        int result = bai1.tinhTong(2.2, 3);
        int expected = 5;
        assertEquals(expected, result);
    }

    @Test
    public void test4() {
        int result = bai1.tinhTong(-2, 3);
        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void test5() {
        int result = bai1.tinhTong(0, 0);
        int expected = 0;
        assertEquals(expected, result);
    }
}
