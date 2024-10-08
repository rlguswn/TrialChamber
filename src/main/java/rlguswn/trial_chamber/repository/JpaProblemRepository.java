package rlguswn.trial_chamber.repository;

import jakarta.persistence.EntityManager;
import rlguswn.trial_chamber.domain.Post;
import rlguswn.trial_chamber.domain.Problem;

import java.util.List;
import java.util.Optional;

public class JpaProblemRepository implements ProblemRepository {

    private final EntityManager em;

    public JpaProblemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Problem save(Problem problem) {
        em.persist(problem);
        return problem;
    }

    @Override
    public Optional<Problem> findById(Long id) {
        Problem problem = em.find(Problem.class, id);
        return Optional.ofNullable(problem);
    }

    @Override
    public List<Problem> findByPostId(Long postId) {
        return em.createQuery("select p from Problem p where p.post.id = :postId", Problem.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    @Override
    public List<Problem> findAll() {
        return em.createQuery("select p from Problem p", Problem.class).getResultList();
    }

    @Override
    public boolean deleteByProblemId(Long id) {
        Problem problem = em.find(Problem.class, id);
        if (problem != null) {
            em.remove(problem);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
