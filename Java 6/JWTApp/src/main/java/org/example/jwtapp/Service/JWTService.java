package org.example.jwtapp.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    private Key getSignKey() {
        // Giả sử đây là khóa bí mật dùng để ký JWT
        // Thực tế nên lưu trữ khóa này một cách an toàn, họ dùng random
        String secretKey = "0123456789.0123456789012345678901234567890";
// Chuyển chuỗi thành mảng byte[]
        byte[] keyBytes = secretKey.getBytes();
        // Tạo đối tượng key
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String create(UserDetails user, int expirySeconds) {
        // Lấy thời gian hiện tại
        Long now = System.currentTimeMillis();

        return Jwts.builder()
                //Thiet lap claim tuy y bo sung vao payload cua JWT
                .setClaims(Map.of("name", "Poly"))
                //Thiet lap tieu de
                .setSubject(user.getUsername())
                //Ngay phat hanh token
                .setIssuedAt(new Date(now))
                //Ngay het han
                .setExpiration(new Date(now + 1000 * expirySeconds))
                //Ky token  bang chu ky thuat toan HMAC
                .signWith(this.getSignKey(), SignatureAlgorithm.ES256)
                //Nen va tao Token
                .compact();


    }


}
