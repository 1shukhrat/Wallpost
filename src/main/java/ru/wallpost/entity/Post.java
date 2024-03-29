package ru.wallpost.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "post")
public class Post implements Comparable<Post> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private long id;

    @Column(name = "post_text")
    private String text;

    @Column(name = "post_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE})
    private Set<Image> images = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE})
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.REMOVE})
    private Set<Comment> comments = new TreeSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && Objects.equals(text, post.text) && Objects.equals(date, post.date) && Objects.equals(owner, post.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, owner);
    }

    @Override
    public int compareTo(Post o) {
        return this.getDate().compareTo(o.getDate());
    }
}
