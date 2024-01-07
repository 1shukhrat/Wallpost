package ru.wallpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wallpost.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
