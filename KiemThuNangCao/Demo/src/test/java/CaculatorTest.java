import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaculatorTest {
    @Test
    public void add_1(){
        Caculator caculator = new Caculator();
        int result = caculator.add(2, 2);
        int expected = 4;
        assertEquals(result, expected);
    }
    @Test
    public void add_2(){
        Caculator caculator = new Caculator();
        int result = caculator.add1(2, 2);
        int expected = 0;
        assertEquals(result, expected);
    }
    @Test
    public void add_3(){
        Caculator caculator = new Caculator();
        int result = caculator.add2(2, 2);
        int expected = 4;
        assertEquals(result, expected);
    }
    @Test
    public void add_4(){
        Caculator caculator = new Caculator();
        int result = caculator.add3(2, 2);
        int expected = 1;
        assertEquals(result, expected);
    }
}