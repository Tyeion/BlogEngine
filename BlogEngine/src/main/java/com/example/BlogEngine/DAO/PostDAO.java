package com.example.BlogEngine.DAO;

import com.example.BlogEngine.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PostDAO {

    private final EntityManager entityManager;

    @Autowired
    public PostDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void savePost(Post post) {
        entityManager.persist(post);
    }

    public Post getPost(Long id) {
        return entityManager.find(Post.class, id);
    }

    public List<Post> getAllPosts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> root = cq.from(Post.class);
        cq.orderBy(cb.desc(root.get("createdAt")));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Post> getPostsByUser(Long userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> root = cq.from(Post.class);
        cq.where(cb.equal(root.get("author").get("id"), userId));
        cq.orderBy(cb.desc(root.get("createdAt")));
        return entityManager.createQuery(cq).getResultList();
    }

    public void updatePost(Post post) {
        entityManager.merge(post);
    }

    public void deletePost(Long id) {
        Post post = entityManager.find(Post.class, id);
        if (post != null) {
            entityManager.remove(post);
        }
    }
}