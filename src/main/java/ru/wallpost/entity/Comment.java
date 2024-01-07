package ru.wallpost.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"comment\"",
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class Comment implements Comparable<Comment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

    @Column(name = "comment_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User owner;

    @Override
    public int compareTo(Comment o) {
        return this.getDate().compareTo(o.getDate());
    }
}
