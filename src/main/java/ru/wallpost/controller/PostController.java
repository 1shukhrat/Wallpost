package ru.wallpost.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wallpost.DTO.AddPostDTO;
import ru.wallpost.DTO.PostDTO;
import ru.wallpost.entity.Post;
import ru.wallpost.mapper.PostMapper;
import ru.wallpost.service.PostService;
import ru.wallpost.util.MessageResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostDTO>> getAll(@RequestParam("page") int page) throws IllegalStateException {
        List<Post> posts = postService.getAll(page);
        return new ResponseEntity<>(PostMapper.toDTO(posts), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PostDTO>> getAllByUser(@RequestParam("user") long userId,
                                                      @RequestParam("page") int page) throws IllegalStateException{
        List<Post> posts = postService.getAllByUser(userId, page);
        return new ResponseEntity<>(PostMapper.toDTO(posts), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<List<PostDTO>> getAllByUser(@RequestParam("page") int page) throws IllegalStateException {
        List<Post> posts = postService.getAllByAuthenticated(page);
        return new ResponseEntity<>(PostMapper.toDTO(posts), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> post(@ModelAttribute AddPostDTO addPostDTO) throws IOException, IllegalStateException {
        Post newPost = postService.post(addPostDTO);
        return new ResponseEntity<>(PostMapper.toDTO(newPost), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> remove(@PathVariable("id") long id) throws IOException,
            IllegalStateException, IllegalArgumentException {
        postService.remove(id);
        return new ResponseEntity<>(new MessageResponse("Пост успешно удален", LocalDateTime.now()),
                HttpStatus.OK);
    }
}
