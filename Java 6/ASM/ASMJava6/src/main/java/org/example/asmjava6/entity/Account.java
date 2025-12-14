package org.example.asmjava6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @Nationalized
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Nationalized
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Nationalized
    @Column(name = "Fullname", nullable = false, length = 50)
    private String fullname;

    @Nationalized
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Nationalized
    @ColumnDefault("'user.png'")
    @Column(name = "Photo", length = 50)
    private String photo;

    @ColumnDefault("1")
    @Column(name = "Activated", nullable = false)
    private Boolean activated;

    @JsonIgnore
    @OneToMany(mappedBy = "username")
    private Set<Authority> authorities = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "username")
    private Set<Order> orders = new LinkedHashSet<>();

}