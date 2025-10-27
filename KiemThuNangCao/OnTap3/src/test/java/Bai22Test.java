import org.example.ontap3.Bai22.SinhVien;
import org.example.ontap3.Bai22.SinhVienService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Bai22Test {
    SinhVienService sinhvien = new SinhVienService();

    @Test
    public void testThemSinhVienHopLe() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 8.5, "CNTT", 20);
        assertTrue(sinhvien.add(sinhVien));
    }

    @Test
    public void testThemSinhVienKhongHopLe() {
        SinhVien sinhVien = new SinhVien("SV01", "", 8.5, "CNTT", 20);
        assertThrows(IllegalArgumentException.class, () -> sinhvien.add(sinhVien));
    }

    @Test
    public void testThemSinhVienDiemBienTren() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 10.0, "CNTT", 20);
        assertTrue(sinhvien.add(sinhVien));
    }

    @Test
    public void testThemSinhVienDiemBienDuoi() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 0.0, "CNTT", 20);
        assertTrue(sinhvien.add(sinhVien));
    }

    @Test
    public void testThemSinhVienTuoiNgoaiPhanVung() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 8.0, "CNTT", 99);
        assertThrows(IllegalArgumentException.class, () -> sinhvien.add(sinhVien));
    }

    // Sửa

    @Test
    public void testSuaSinhVienHopLe() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 8.5, "CNTT", 20);
        sinhvien.add(sinhVien);
        SinhVien sinhVienSua = new SinhVien("SV01", "Tran Van B", 9.0, "CNTT", 21);
        assertTrue(sinhvien.sua("SV01", sinhVienSua));
    }

    @Test
    public void testSuaSinhVienKhongHopLe1() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 8.5, "CNTT", 20);
        sinhvien.add(sinhVien);
        SinhVien sinhVienSua = new SinhVien("SV01", "Nguyen Van A", 11.5, "CNTT", 20);
        assertThrows(IllegalArgumentException.class, () -> sinhvien.sua("SV01", sinhVienSua));
    }

    @Test
    public void testSuaSinhVienKhongHopLe2() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 8.5, "CNTT", 20);
        sinhvien.add(sinhVien);
        SinhVien sinhVienSua = new SinhVien("SV01", "Tran Van B", 9.0, "CNTT", 90);
        assertThrows(IllegalArgumentException.class, () -> sinhvien.sua("SV01", sinhVienSua));
    }

    @Test
    public void testSuaSinhVienDiemBienTren() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 8.5, "CNTT", 20);
        sinhvien.add(sinhVien);
        SinhVien sinhVienSua = new SinhVien("SV01", "Tran Van B", 10.0, "CNTT", 21);
        assertTrue(sinhvien.sua("SV01", sinhVienSua));
    }

    @Test
    public void testSuaSinhVienTuoiBienDuoi() {
        SinhVien sinhVien = new SinhVien("SV01", "Nguyen Van A", 8.5, "CNTT", 20);
        sinhvien.add(sinhVien);
        SinhVien sinhVienSua = new SinhVien("SV01", "Tran Van B", 9.0, "CNTT", 30);
        assertTrue(sinhvien.sua("SV01", sinhVienSua));
    }
}
