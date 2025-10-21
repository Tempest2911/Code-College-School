import org.example.ontap2.Phan1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SOF3041Test {

    Phan1 sumArray = new Phan1();

    // Phân vùng tương đương
    @Test
    void testEmptyArray() {
        assertEquals(0, sumArray.sum(new int[]{}));
    }

    @Test
    void testPositiveNumbers() {
        assertEquals(6, sumArray.sum(new int[]{1, 2, 3}));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-6, sumArray.sum(new int[]{-1, -2, -3}));
    }

    @Test
    void testMixedNumbers() {
        assertEquals(0, sumArray.sum(new int[]{-2, 3, -1}));
    }

    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> sumArray.sum(null));
    }

    // Giá trị biên
    @Test
    void testSingleElement() {
        assertEquals(5, sumArray.sum(new int[]{5}));
    }

    @Test
    void testTwoElementsZero() {
        assertEquals(0, sumArray.sum(new int[]{0, 0}));
    }

    @Test
    void testIntegerOverflow() {
        int[] arr = {Integer.MAX_VALUE, 1};
        int result = sumArray.sum(arr);
        System.out.println("Kết quả tràn số: " + result);
    }
}
