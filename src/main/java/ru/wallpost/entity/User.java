package ru.wallpost.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "\"user\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "login", unique = true, nullable = false)
    @NotBlank
    private String login;

    @NotBlank
    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "mood")
    private String mood;

    @Column(name = "avatar", unique = true, nullable = false)
    private String avatarLink;

    @Transient
    private final String ROLE = "USER";

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<Post> posts = new TreeSet<>();

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE})
    private Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.REMOVE})
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "\"subscription\"",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "subscriber_id"}))
    private Set<User> subscribers = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "\"subscription\"",
            joinColumns = @JoinColumn(name = "subscriber_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "subscriber_id"}))
    private Set<User> subscriptions = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(getROLE()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(mood, user.mood) && Objects.equals(avatarLink, user.avatarLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, mood, avatarLink);
    }
}
