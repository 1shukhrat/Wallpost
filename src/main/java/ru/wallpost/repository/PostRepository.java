package ru.wallpost.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.wallpost.entity.Post;
import ru.wallpost.entity.User;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.owner in :subs order by p.date desc")
    Slice<Post> findAllBySubscriptionsOrOrderByDateDesc(@Param("subs") Set<User> subscribers, Pageable p);

    Slice<Post> findAllByOwnerIdOrderByDateDesc(long userId, Pageable p);
}
