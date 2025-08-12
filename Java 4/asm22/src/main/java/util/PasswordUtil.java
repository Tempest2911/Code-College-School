package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // cost/work factor (12 là hợp lý)
    private static final int WORK_FACTOR = 12;

    public static String hash(String plain) {
        if (plain == null) return null;
        return BCrypt.hashpw(plain, BCrypt.gensalt(WORK_FACTOR));
    }

    public static boolean verify(String plain, String hashed) {
        if (plain == null || hashed == null) return false;
        try {
            return BCrypt.checkpw(plain, hashed);
        } catch (Exception e) {
            return false;
        }
    }

    // helper để detect hash kiểu BCrypt (dùng cho migrate)
    public static boolean isBCryptHash(String s) {
        return s != null && s.startsWith("$2a$") || (s != null && s.startsWith("$2y$")) || (s != null && s.startsWith("$2b$"));
    }
}
