package org.example.springcustomauthwithdb.Service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("auth")
public class AuthService {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUsername() {
        Authentication a = getAuthentication();
        if (a == null) return null;
        return a.getName();
    }

    public List<String> getRoles() {
        Authentication a = getAuthentication();
        if (a == null) return List.of();
        return a.getAuthorities().stream()
                .map(ga -> {
                    String auth = ga.getAuthority();
                    if (auth != null && auth.startsWith("ROLE_")) {
                        return auth.substring(5);
                    }
                    return auth;
                })
                .filter(java.util.Objects::nonNull)
                .toList();
    }

    public boolean isAuthenticated() {
        Authentication a = getAuthentication();
        if (a == null) return false;
        // loại trừ anonymous authentication
        return a.isAuthenticated() && !(a instanceof AnonymousAuthenticationToken);
    }

    public boolean hasRole(String role) {
        if (role == null) return false;
        return getRoles().contains(role);
    }

    public String getRole() {
        List<String> roles = getRoles();
        return roles.isEmpty() ? "" : roles.get(0);
    }
}

