// java
package org.example.springcustomauthwithdb.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springcustomauthwithdb.Enity.User;
import org.example.springcustomauthwithdb.Repository.UserDAO;
import org.example.springcustomauthwithdb.Enity.UserRole;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findById(username).orElse(null);
        log.info("Trying to authenticate user: {}", username);
        if (user != null && user.getEnabled() != null && user.getEnabled()) {
            String password = user.getPassword();
            log.info("Loaded user: {} with password: {}", username, password);
            Set<UserRole> userRoles = user.getUserRoles();
            String[] roles = userRoles.stream()
                .map(ur -> ur.getRole().getId().substring(5))
                .toArray(String[]::new);
            log.info("User roles: {}", (Object) roles);
            return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(password)
                .roles(roles)
                .build();
        }
        log.warn("User not found or disabled: {}", username);
        throw new UsernameNotFoundException("User not found or disabled");
    }
}