package ru.wallpost.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wallpost.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Slice<Like> findAllByPostId(long id, Pageable p);
}
