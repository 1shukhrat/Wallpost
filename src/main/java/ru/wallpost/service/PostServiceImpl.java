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
import ru.wallpost.util.FileUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.imageService = imageService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAll(int page) throws IllegalStateException{
        User user = (User) AuthService.getAuthenticated();
        return postRepository.
                findAllBySubscriptionsOrOrderByDateDesc(user.getSubscriptions(), PageRequest.of(page, 10))
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
        User user = (User) AuthService.getAuthenticated();
        return getAllByUser(user.getId(), page);
    }

    @Transactional
    @Override
    public Post post(AddPostDTO addPostDTO) throws IOException, IllegalStateException {
        User user = (User) AuthService.getAuthenticated();
        Post post = Post.builder()
                .text(addPostDTO.getText())
                .date(LocalDateTime.now())
                .comments(Collections.emptySet())
                .likes(Collections.emptySet())
                .images(Collections.emptySet())
                .owner(user)
                .build();
        Set<Image> images = new LinkedHashSet<>();
        for (MultipartFile image: addPostDTO.getImages()) {
            String url = FileUtil.save(image);
            Image savedImage = Image.builder().link(url).post(post).build();
            imageService.save(savedImage);
            images.add(savedImage);
        }
        post.setImages(images);
        return postRepository.save(post);
    }


    @Transactional
    @Override
    public void remove(long id) throws IOException, IllegalStateException {
        User user = (User) AuthService.getAuthenticated();
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
