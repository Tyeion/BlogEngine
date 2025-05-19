package com.example.BlogEngine.DAO;

import com.example.BlogEngine.model.Comment;
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
public class CommentDAO {

    private final EntityManager entityManager;

    @Autowired
    public CommentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveComment(Comment comment) {
        entityManager.persist(comment);
    }

    public Comment getComment(Long id) {
        return entityManager.find(Comment.class, id);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
        Root<Comment> root = cq.from(Comment.class);
        cq.where(cb.equal(root.get("post").get("id"), postId));
        cq.orderBy(cb.asc(root.get("createdAt")));
        return entityManager.createQuery(cq).getResultList();
    }

    public void updateComment(Comment comment) {
        entityManager.merge(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }
}