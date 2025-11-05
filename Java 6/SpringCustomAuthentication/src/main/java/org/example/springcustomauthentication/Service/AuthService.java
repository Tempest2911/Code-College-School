package org.example.springcustomauthentication.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service("auth")

public class AuthService {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    //Get Username
    public String getUsername() {
        return this.getAuthentication().getName();
    }

    // Giả sử chuỗi cai trò trong String có dạng "ROLE_ADMIN", "ROLE_USER",...
    public List<String> getRoles() {
        return this.getAuthentication().getAuthorities().stream()
                .map(authority -> authority.getAuthority().substring(5)).toList();
    }

    //Kiểm tra người dùng đã xác thực hay chưa
    public boolean isAuthenticated() {
        String username = this.getUsername();
        return username != null && !username.equals("anonymousUser");
    }

    //Kiểm tra người dùng có vai trò cụ thể hay không
    public boolean hasRole(String role) {
        var grantedRoles = this.getRole();
        return Stream.of(role).anyMatch(grantedRoles::contains);
    }

    // Get the first role as a String (for display)
    public String getRole() {
        List<String> roles = this.getRoles();
        return roles.isEmpty() ? "" : roles.get(0);
    }

}
