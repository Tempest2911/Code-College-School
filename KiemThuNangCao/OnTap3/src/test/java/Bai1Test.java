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

//    @Test
//    void testtrongKhoang(){
//        assertEquals(62750, bai1.sumEvenNumbers(500));
//    }
//
//    //Kỹ thuật biên
//    @Test
//    void testBienTren(){
//        assertEquals(250500, bai1.sumEvenNumbers(1000));
//    }
//
//    @Test
//    void testBienDuoi() {
//        assertEquals(0, bai1.sumEvenNumbers(1));
//    }
//
//    @Test
//    void testCanBienTren(){
//        assertEquals(249500, bai1.sumEvenNumbers(999));
//    }
//
//    @Test
//    void testCanBienDuoi() {
//        assertEquals(2, bai1.sumEvenNumbers(2));
//    }
//
//    @Test
//    void testNgoaiBienTren(){
//       assertThrows(IllegalArgumentException.class, () -> bai1.sumEvenNumbers(1001));
//    }
//
//    @Test
//    void testNgoaiBienDuoi() {
//        assertThrows(IllegalArgumentException.class, () -> bai1.sumEvenNumbers(0));
//    }

    // Giai Thừa

//    @Test
//    void testTinhGiaiThua() {
//        assertEquals(120, bai1.tinhGiaiThua(5));
//    }
//
//    @Test
//    void testTinhGiaiThuaBenTren() {
//        assertEquals(3628800, bai1.tinhGiaiThua(10));
//    }
//
//    @Test
//    void testTinhGiaiThuaKoHopLe() {
//        assertThrows(IllegalArgumentException.class, () -> bai1.tinhGiaiThua(0));
//    }

    // Tính Trung bình cộng
//    @Test
//    void testTinhTrungBinhCong() {
//        int[] arr = {2, 4, 6, 8, 10};
//        assertEquals(6.0, bai1.tinhTrungBinhCong(arr));
//    }
//
//    @Test
//    void testTinhTrungBinhCongNull() {
//        int[] arr = {};
//        assertThrows(IllegalArgumentException.class, () -> bai1.tinhTrungBinhCong(arr));
//    }


    //Tính Tuổi
//
//    @Test
//    void testTinhTuoiHopLe() {
//        assertEquals(24, bai1.calculateAge(2001));
//    }
//
//    @Test
//    void testTinhTuoiKoHopLe1() {
//        assertThrows(IllegalArgumentException.class, () -> bai1.calculateAge(1800));
//    }
//
//    @Test
//    void testTinhTuoiKoHopLe2() {
//        assertThrows(IllegalArgumentException.class, () -> bai1.calculateAge(2099));
//    }

    //Check nguyên tố
    @Test
    void testCheckNguyenTo() {
      assertEquals(21536, bai1.sumPrimeNumbers(500));
    }


}
