package ru.wallpost.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "\"comment\"",
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class Comment implements Comparable<Comment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

    @Column(name = "comment_text", nullable = false)
    private String text;

    @Column(name = "comment_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(text, comment.text) && Objects.equals(date, comment.date) && Objects.equals(post, comment.post) && Objects.equals(owner, comment.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, post, owner);
    }

    @Override
    public int compareTo(Comment o) {
        return this.getDate().compareTo(o.getDate());
    }
}
