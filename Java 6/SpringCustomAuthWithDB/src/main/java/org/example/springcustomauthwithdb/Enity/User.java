package org.example.springcustomauthwithdb.Enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "Enabled", nullable = false)
    private Boolean enabled = false;

    @OneToMany(mappedBy = "username")
    private Set<UserRole> userRoles = new LinkedHashSet<>();


}