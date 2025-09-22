
import BaiTap.Bai1;
import BaiTap.Bai2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Bai2Test {
    private Bai2 bai2;

    @BeforeEach
    public void setUp() {
        bai2 = new Bai2();
    }

    @Test
    public void test1() {
        int result = bai2.tinhTong(6, 3);
        int expected = 2;
        assertEquals(result, expected);
    }

    @Test
    public void test2() {
        int result = bai2.tinhTong(100, 25);
        int expected = 4;
        assertEquals(result, expected);
    }

    @Test
    public void test3() {
        int result = bai2.tinhTong(346, 2);
        int expected = 173;
        assertEquals(result, expected);
    }

    @Test
    public void test4() {
        int result = bai2.tinhTong(999, 0);
        int expected = 2;
        assertEquals(result, expected);
    }

    @Test
    public void test5() {
        int result = bai2.tinhTong(10, 0);
        int expected = 1;
        assertEquals(result, expected);
    }
}
