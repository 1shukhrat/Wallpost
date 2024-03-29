package ru.wallpost.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wallpost.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Slice<Comment> findAllByPostId(long postId, Pageable p);
}
