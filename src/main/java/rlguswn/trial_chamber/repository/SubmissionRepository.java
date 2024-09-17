package rlguswn.trial_chamber.repository;

import rlguswn.trial_chamber.domain.Submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository {
    Submission save(Submission submission);
    Optional<Submission> findById(Long id);
    List<Submission> findByPostId(Long postId);
    List<Submission> findAll();
    boolean deleteBySubmissionId(Long id);
}
