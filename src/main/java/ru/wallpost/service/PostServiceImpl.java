package ru.wallpost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.wallpost.DTO.AddPostDTO;
import ru.wallpost.entity.Image;
import ru.wallpost.entity.Post;
import ru.wallpost.entity.User;
import ru.wallpost.repository.PostRepository;
import ru.wallpost.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ImageService imageService;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.imageService = imageService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAll(int page) throws IllegalStateException{
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        return postRepository.
                findAllBySubscribersOrOrderByDateDesc(user.getSubscriptions(), PageRequest.of(page, 10))
                .getContent();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAllByUser(long userId, int page) throws IllegalStateException{
        return postRepository.
                findAllByOwnerIdOrderByDateDesc(userId, PageRequest.of(page, 10))
                .getContent();
    }
    @Transactional(readOnly = true)
    @Override
    public List<Post> getAllByAuthenticated(int page) throws IllegalStateException{
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        return getAllByUser(user.getId(), page);
    }

    @Transactional
    @Override
    public Post post(AddPostDTO addPostDTO) throws IOException, IllegalStateException {
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        Post post = Post.builder()
                .text(addPostDTO.getText())
                .date(LocalDateTime.now())
                .owner(user)
                .build();
        Set<Image> images = new LinkedHashSet<>();
        for (MultipartFile image: addPostDTO.getImages()) {
            Image savedImage = imageService.save(image);
            savedImage.setPost(post);
            images.add(savedImage);
        }
        post.setImages(images);
        return postRepository.save(post);
    }


    @Transactional
    @Override
    public void remove(long id) throws IOException, IllegalStateException {
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent() && user.getPosts().contains(postOptional.get())) {
            Post post = postOptional.get();
            for (Image image: post.getImages()) {
                imageService.remove(image.getId());
            }
            user.getPosts().remove(post);
            postRepository.delete(post);
        } else {
            throw new NoSuchElementException("Данный пост не найден");
        }
    }
}
