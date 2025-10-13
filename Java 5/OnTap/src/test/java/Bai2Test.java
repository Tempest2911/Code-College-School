import org.example.ontap.Phan2.Sach;
import org.example.ontap.Phan2.SachService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Bai2Test {

    SachService service;

    @BeforeEach
    void setUp() {
        service = new SachService();
    }

    @Test
    void testAddBookHopLe() {
        Sach sach = new Sach("001", "Java", 25, "Phong", 10);
        assertTrue(service.themSach(sach));
    }

    @Test
    void testAddBook_CanBienTren() {
        Sach sach = new Sach("002", "Java", 50, "Phong", 6);
        assertTrue(service.themSach(sach));
    }

    @Test
    void testAddBook_CanBienDuoi() {
        Sach sach = new Sach("002", "Java", 0, "Phong", 6);
        assertTrue(service.themSach(sach));
    }

    @Test
    void testAddBook_BienTren() {
        Sach sach = new Sach("002", "Java", 51, "Phong", 6);
        assertThrows(IllegalArgumentException.class, () -> service.themSach(sach));
    }

    @Test
    void testAddBook_BienDuoi() {
        Sach sach = new Sach("002", "Java", -1, "Phong", 6);
        assertThrows(IllegalArgumentException.class, () -> service.themSach(sach));
    }

    @Test
    void testAddBook_ChuaKiTu() {
        Sach sach = new Sach("002", "Java@#$%%", 40, "Phong", 6);
        assertThrows(IllegalArgumentException.class, () -> service.themSach(sach));
    }

    @Test
    void testEditBookHopLe() {
        Sach sach = new Sach("001", "Java", 25, "Phong", 10);
        service.themSach(sach);

        Sach updatedSach = new Sach("001", "Java Core", 30, "Phong", 12);
        assertTrue(service.suaSach("001", updatedSach));
    }

    @Test
    void testEditBook_SoTrangCanBienTren() {
        Sach sach = new Sach("001", "Java", 25, "Phong", 10);
        service.themSach(sach);

        Sach updatedSach = new Sach("001", "Java Core", 50, "Phong", 12);
        assertTrue(service.suaSach("001", updatedSach));
    }

    @Test
    void testEditBook_SoTrangCanBienDuoi() {
        Sach sach = new Sach("001", "Java", 25, "Phong", 10);
        service.themSach(sach);

        Sach updatedSach = new Sach("001", "Java Core", 0, "Phong", 12);
        assertTrue(service.suaSach("001", updatedSach));
    }

    @Test
    void testEditBook_SoTrangBienDuoi() {
        Sach sach = new Sach("001", "Java", 25, "Phong", 10);
        service.themSach(sach);

        Sach updatedSach = new Sach("001", "Java Core", -1, "Phong", 12);
        assertThrows(IllegalArgumentException.class, () -> service.suaSach("001", updatedSach));
    }

    @Test
    void testEditBook_SoTrangBienTren() {
        Sach sach = new Sach("001", "Java", 25, "Phong", 10);
        service.themSach(sach);

        Sach updatedSach = new Sach("001", "Java Core", 90, "Phong", 12);
        assertThrows(IllegalArgumentException.class, () -> service.suaSach("001", updatedSach));
    }

    @Test
    void testEditBook_CoKiTu() {
        Sach sach = new Sach("001", "LALALALA", 25, "Phong", 10);
        service.themSach(sach);

        Sach updatedSach = new Sach("001", "Java$%#%", 25, "Phong", 12);
        assertThrows(IllegalArgumentException.class, () -> service.suaSach("001", updatedSach));
    }
}
