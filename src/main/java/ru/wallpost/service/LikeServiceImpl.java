package ru.wallpost.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.wallpost.entity.Like;
import ru.wallpost.entity.Post;
import ru.wallpost.entity.User;
import ru.wallpost.repository.LikeRepository;
import ru.wallpost.repository.PostRepository;


import java.util.List;
import java.util.Optional;


@Service
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Like> getAll(long postId, int page) {
        return likeRepository.findAllByPostId(postId, PageRequest.of(page, 20)).getContent();
    }

    @Transactional
    @Override
    public Like add(long postId) throws IllegalArgumentException {
        User user = (User) AuthService.getAuthenticated();
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            return likeRepository.save(Like.builder()
                    .post(postOptional.get())
                    .owner(user)
                    .build());
        } else {
            throw new IllegalArgumentException("Пост не найден");
        }
    }

    @Transactional
    @Override
    public void remove(long id) throws IllegalArgumentException {
        User user = (User) AuthService.getAuthenticated();
        Optional<Like> optionalLike = likeRepository.findById(id);
        if (optionalLike.isPresent() && optionalLike.get().getOwner().equals(user)) {
            likeRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Комментарий не найден");
        }
    }
}
