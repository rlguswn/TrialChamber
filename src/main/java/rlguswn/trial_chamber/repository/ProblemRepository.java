package rlguswn.trial_chamber.repository;

import rlguswn.trial_chamber.domain.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository {
    Problem save(Problem problem);
    Optional<Problem> findById(Long id);
    List<Problem> findByPostId(Long postId);
    List<Problem> findAll();
    boolean deleteByProblemId(Long id);
}
