package org.example.lamview10table.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "UserID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Username", length = 50)
    private String username;

    @Nationalized
    @Column(name = "PasswordHash")
    private String passwordHash;

    @Nationalized
    @Column(name = "Role", length = 50)
    private String role;

    @Nationalized
    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "CreatedAt")
    private Instant createdAt;

    @Nationalized
    @Column(name = "Status", length = 50)
    private String status;

}