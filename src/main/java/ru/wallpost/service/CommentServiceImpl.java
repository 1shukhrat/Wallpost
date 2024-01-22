package ru.wallpost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wallpost.DTO.PostCommentDTO;
import ru.wallpost.entity.Comment;
import ru.wallpost.entity.Post;
import ru.wallpost.entity.User;
import ru.wallpost.repository.CommentRepository;
import ru.wallpost.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll(long postId, int page) {
        return commentRepository.findAllByPostId(postId, PageRequest.of(page, 20)).getContent();
    }

    @Transactional
    @Override
    public Comment add(PostCommentDTO postCommentDTO) throws IllegalArgumentException{
        User user = (User) AuthService.getAuthenticated();
        Optional<Post> postOptional = postRepository.findById(postCommentDTO.getPostId());
        if (postOptional.isPresent()) {
            return commentRepository.save(Comment.builder()
                    .date(LocalDateTime.now())
                    .text(postCommentDTO.getText())
                    .post(postOptional.get())
                    .owner(user)
                    .build());
        } else {
            throw new IllegalArgumentException("Пост не найден");
        }
    }

    @Transactional
    @Override
    public void remove(long id) throws IllegalArgumentException{
        User user = (User) AuthService.getAuthenticated();
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent() && optionalComment.get().getOwner().equals(user)) {
            commentRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Комментарий не найден");
        }
    }
}
