package ru.wallpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wallpost.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
