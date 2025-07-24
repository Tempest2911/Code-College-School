package org.example.lab4.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
public class NewsletterSubscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "subscribed", nullable = false)
    private Boolean subscribed = false;

    @Column(name = "subscribed_at")
    private Instant subscribedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Instant getSubscribedAt() {
        return subscribedAt;
    }

    public void setSubscribedAt(Instant subscribedAt) {
        this.subscribedAt = subscribedAt;
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                .withZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        return formatter.format(this.subscribedAt);
    }

}