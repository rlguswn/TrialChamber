package rlguswn.trial_chamber.repository;

import jakarta.persistence.EntityManager;
import rlguswn.trial_chamber.domain.Submission;

import java.util.List;
import java.util.Optional;

public class JpaSubmissionRepository implements SubmissionRepository {

    private final EntityManager em;

    public JpaSubmissionRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Submission save(Submission submission) {
        em.persist(submission);
        return submission;
    }

    @Override
    public Optional<Submission> findById(Long id) {
        Submission submission = em.find(Submission.class, id);
        return Optional.ofNullable(submission);
    }

    @Override
    public List<Submission> findByPostId(Long postId) {
        return em.createQuery("select s from Submission s " +
                        "join s.problem p" +
                        "join p.post post" +
                        "where post.id = :postId", Submission.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    @Override
    public List<Submission> findAll() {
        return em.createQuery("select s from Submission s", Submission.class).getResultList();
    }

    @Override
    public boolean deleteBySubmissionId(Long id) {
        Submission submission = em.find(Submission.class, id);
        if (submission != null) {
            em.remove(submission);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Submission> findByMemberId(Long memberId) {
        return em.createQuery("select s from Submission s where s.member.id = :memberId", Submission.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
