package org.example.lab8.Repository;

import org.example.lab8.Model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByToken(String token);
}
