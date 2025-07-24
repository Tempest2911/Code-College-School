package org.example;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "NewsletterSubscriber")
public class NewsletterSubscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "subscribed")
    private Boolean subscribed ;

    @Column(name = "subscribed_at ")
    private LocalDate subscribed_at  ;

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

    public LocalDate getSubscribed_at() {
        return subscribed_at;
    }

    public void setSubscribed_at(LocalDate subscribed_at) {
        this.subscribed_at = subscribed_at;
    }

    @Transient
    public String getSubscribedStatus() {
        return Boolean.TRUE.equals(subscribed) ? "Yes" : "No";
    }
}