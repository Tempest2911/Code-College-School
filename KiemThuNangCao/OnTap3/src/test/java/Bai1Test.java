import org.example.ontap3.Bai1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Bai1Test {

    Bai1 bai1 = new Bai1();

    //=================== Số lẻ ===================

    //Kỹ thuạt tương đương
//    @Test
//    void testtrongKhoang(){
//        assertEquals(62500, bai1.sumOddNumbers(500));
//    }
//
//    //Kỹ thuật biên
//    @Test
//    void testBienTren(){
//        assertEquals(250000, bai1.sumOddNumbers(1000));
//    }
//
//    @Test
//    void testBienDuoi(){
//        assertEquals(1, bai1.sumOddNumbers(1));
//    }
//
//    @Test
//    void testCanBienTren(){
//        assertEquals(250000, bai1.sumOddNumbers(999));
//    }
//
//    @Test
//    void testCanBienDuoi(){
//        assertEquals(1, bai1.sumOddNumbers(2));
//    }
//
//    @Test
//    void testNgoaiBienTren(){
//        assertThrows(IllegalArgumentException.class, () -> bai1.sumOddNumbers(1001));
//    }
//
//    @Test
//    void testNgoaiBienDuoi(){
//        assertThrows(IllegalArgumentException.class, () -> bai1.sumOddNumbers(1001));
//    }
//
//    //Đoán lỗi
//    @Test
//    void testDoanLoiSoAm(){
//        assertThrows(IllegalArgumentException.class, () -> bai1.sumOddNumbers(-50));
//    }

    //=================== Số chẵn ===================

    @Test
    void testtrongKhoang(){
        assertEquals(62500, bai1.sumEvenNumbers(500));
    }

    //Kỹ thuật biên
    @Test
    void testBienTren(){
        assertEquals(250000, bai1.sumEvenNumbers(1000));
    }

    @Test
    void testBienDuoi() {
        assertEquals(1, bai1.sumEvenNumbers(1));
    }

    @Test
    void testCanBienTren(){
        assertEquals(250000, bai1.sumEvenNumbers(999));
    }

    @Test
    void testCanBienDuoi() {
        assertEquals(1, bai1.sumEvenNumbers(2));
    }

    @Test
    void testNgoaiBienTren(){
       assertThrows(IllegalArgumentException.class, () -> bai1.sumEvenNumbers(1001));
    }

    @Test
    void testNgoaiBienDuoi() {
        assertThrows(IllegalArgumentException.class, () -> bai1.sumEvenNumbers(0));
    }

}
