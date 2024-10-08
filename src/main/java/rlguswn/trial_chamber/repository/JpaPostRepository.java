package rlguswn.trial_chamber.repository;

import jakarta.persistence.EntityManager;
import rlguswn.trial_chamber.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class JpaPostRepository implements PostRepository {

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findByMemberId(Long memberId) {
        return em.createQuery("select p from Post p where p.memberId = :memberId", Post.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Override
    public boolean deleteByPostId(Long id) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            em.remove(post);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Post> findBeforeDeadline() {
        LocalDateTime now = LocalDateTime.now();
        return em.createQuery("select p from Post p where p.deadline is not null and p.deadline > :now", Post.class)
                .setParameter("now", now)
                .getResultList();
    }

    @Override
    public List<Post> findAfterDeadline() {
        LocalDateTime now = LocalDateTime.now();
        return em.createQuery("select p from Post p where p.deadline is not null and p.deadline < :now", Post.class)
                .setParameter("now", now)
                .getResultList();
    }

    @Override
    public List<Post> findTemporary() {
        return em.createQuery("select p from Post p where p.deadline is null", Post.class)
                .getResultList();
    }
}
