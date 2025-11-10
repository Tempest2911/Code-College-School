package org.example.springcustomauthwithdb.Repository;

import org.example.springcustomauthwithdb.Enity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, String> {
}
