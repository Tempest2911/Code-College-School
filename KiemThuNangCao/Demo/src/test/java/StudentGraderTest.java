import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentGraderTest {
    private StudentGrader Grader;

    @BeforeEach
    public void setUp() {
        Grader = new StudentGrader();
    }

    @Test
    public void grader_1(){
        String result = Grader.XepLoai(56);
        String expected = "Xếp loại: D";
        assertEquals(result, expected);
    }

    @Test
    public void grader_2(){
        String result = Grader.XepLoai(30);
        String expected = "Xếp loại: F";
        assertEquals(result, expected);
    }

    @Test
    public void grader_3(){
        String result = Grader.XepLoai(69);
        String expected = "Xếp loại: C";
        assertEquals(result, expected);
    }

    @Test
    public void grader_4(){
        String result = Grader.XepLoai(78);
        String expected = "Xếp loại: B";
        assertEquals(result, expected);
    }

    @Test
    public void grader_5(){
        String result = Grader.XepLoai(100);
        String expected = "Xếp loại: A";
        assertEquals(result, expected);
    }

    @Test
    public void TestDiemAm(){
        assertThrows(IllegalArgumentException.class, () -> {
            Grader.XepLoai(-10);
        });
    }
}

