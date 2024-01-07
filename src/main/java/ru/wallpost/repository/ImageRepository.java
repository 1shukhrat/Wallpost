package ru.wallpost.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.wallpost.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
