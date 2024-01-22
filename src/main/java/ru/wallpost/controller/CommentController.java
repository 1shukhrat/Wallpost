package ru.wallpost.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.wallpost.DTO.CommentDTO;
import ru.wallpost.DTO.PostCommentDTO;
import ru.wallpost.entity.Comment;
import ru.wallpost.mapper.CommentMapper;
import ru.wallpost.service.CommentService;
import ru.wallpost.util.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAll(@RequestParam("postId") long postId, @RequestParam("page") int page) {
        List<Comment> comments = commentService.getAll(postId, page);
        return new ResponseEntity<>(CommentMapper.toDTO(comments), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> add(@Valid @RequestBody PostCommentDTO postCommentDTO,
                                          BindingResult bindingResult) throws IllegalArgumentException {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Комментарий не должен быть пустым");
        }
        Comment comment = commentService.add(postCommentDTO);
        return new ResponseEntity<>(CommentMapper.toDTO(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") long id) throws IllegalArgumentException {
        commentService.remove(id);
        return new ResponseEntity<>(new MessageResponse("Комментарий успешно удален", LocalDateTime.now()),
                HttpStatus.OK);
    }
}
