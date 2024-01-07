package ru.wallpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wallpost.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
