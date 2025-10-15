package org.example.asm.Repository;

import org.example.asm.Model.Notification;
import org.example.asm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUser(User user);
}