public class Divide {
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divider cannot be zero");
        } else if (a < 0 || a > 100 || b < 0 || b > 100){
            throw new IllegalArgumentException("Input must be between 0 and 100");
        } else {
            return a/b;
        }
    }
}
