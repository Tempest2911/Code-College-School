package org.example.asm.Repository;

import org.example.asm.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findByRole(String role); // để Admin load danh sách Staff
}
