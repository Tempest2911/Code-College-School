package org.example.springcustomauthwithdb.Repository;

import org.example.springcustomauthwithdb.Enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
}
