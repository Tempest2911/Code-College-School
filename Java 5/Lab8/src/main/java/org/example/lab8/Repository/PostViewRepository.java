package org.example.lab8.Repository;

import org.example.lab8.Model.PostView;
import org.example.lab8.Model.User;
import org.example.lab8.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
    boolean existsByUserAndPost(User user, Post post);
}
