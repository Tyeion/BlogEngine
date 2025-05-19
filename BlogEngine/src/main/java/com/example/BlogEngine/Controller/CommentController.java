package com.example.BlogEngine.Controller;

import com.example.BlogEngine.DAO.CommentDAO;
import com.example.BlogEngine.DAO.PostDAO;
import com.example.BlogEngine.DAO.UserDAO;
import com.example.BlogEngine.dto.CommentRequest;
import com.example.BlogEngine.model.Comment;
import com.example.BlogEngine.model.Post;
import com.example.BlogEngine.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentDAO.getCommentsByPost(postId));
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        Post post = postDAO.getPost(commentRequest.getPostId());
        User user = userDAO.getUser(commentRequest.getUserId());

        if (post == null || user == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    post == null ? "Post not found" : "User not found"
            );
        }

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setPost(post);
        comment.setAuthor(user);

        commentDAO.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        Comment comment = commentDAO.getComment(id);
        if (comment != null) {
            commentDAO.deleteComment(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}