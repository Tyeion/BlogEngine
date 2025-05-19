package com.example.BlogEngine.Controller;

import com.example.BlogEngine.DAO.PostDAO;
import com.example.BlogEngine.DAO.UserDAO;
import com.example.BlogEngine.DTO.PostRequest;
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
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postDAO.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post post = postDAO.getPost(id);
        return post != null
                ? ResponseEntity.ok(post)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest postRequest) {
        User author = userDAO.getUser(postRequest.getAuthorId());
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(author);

        postDAO.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest postRequest
    ) {
        Post existingPost = postDAO.getPost(id);
        if (existingPost != null) {
            User author = userDAO.getUser(postRequest.getAuthorId());
            if (author == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
            }

            existingPost.setTitle(postRequest.getTitle());
            existingPost.setContent(postRequest.getContent());
            existingPost.setAuthor(author);

            postDAO.updatePost(existingPost);
            return ResponseEntity.ok(existingPost);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        Post post = postDAO.getPost(id);
        if (post != null) {
            postDAO.deletePost(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}