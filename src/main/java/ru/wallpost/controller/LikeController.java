package ru.wallpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wallpost.DTO.LikeDTO;
import ru.wallpost.entity.Like;
import ru.wallpost.mapper.LikeMapper;
import ru.wallpost.service.LikeService;
import ru.wallpost.util.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<List<LikeDTO>> getAll(@RequestParam("postId") long postId, @RequestParam("page") int page) {
        List<Like> likes = likeService.getAll(postId, page);
        return new ResponseEntity<>(LikeMapper.toDTO(likes), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LikeDTO> add(@RequestParam("postId") long postId) throws IllegalArgumentException {
        Like like = likeService.add(postId);
        return new ResponseEntity<>(LikeMapper.toDTO(like), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") long id) throws IllegalArgumentException {
        likeService.remove(id);
        return new ResponseEntity<>(new MessageResponse("Лайк успешно удален", LocalDateTime.now()),
                HttpStatus.OK);
    }
}
