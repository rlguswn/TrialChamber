package rlguswn.trial_chamber.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.domain.Post;
import rlguswn.trial_chamber.domain.Problem;
import rlguswn.trial_chamber.dto.ProblemForm;
import rlguswn.trial_chamber.dto.ProblemUpdateForm;
import rlguswn.trial_chamber.repository.ProblemRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class ProblemService {

    @Autowired
    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Long createProblem(ProblemForm form, Member member, Post post) {
        validateProblemFormData(form);

        Problem problem = new Problem(
            member,
            post,
            form.getTitle(),
            form.getDescription(),
            form.getTrialType(),
            form.getAutoGrade()
        );

        problemRepository.save(problem);
        return problem.getId();
    }

    private static void validateProblemFormData(ProblemForm form) {
        if (form.getTitle() == null || form.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        }
        if (form.getDescription() == null || form.getDescription().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        if (form.getTrialType() == null || form.getTrialType().isEmpty()) {
            throw new IllegalArgumentException("문제의 유형을 선택해주세요.");
        }
    }

    public List<Problem> findProblems() {
        return problemRepository.findAll();
    }

    public Optional<Problem> findOne(Long problemId) {
        return problemRepository.findById(problemId);
    }

    public List<Problem> findProblemsByPostId(Long postId) {
        return problemRepository.findByPostId(postId);
    }

    public Problem updateProblem(Long problemId, ProblemUpdateForm form) {
        validateProblemUpdateFormData(form);

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("문제를 찾을 수 없습니다."));

        problem.setTitle(form.getTitle());
        problem.setDescription(form.getDescription());
        problem.setTrialType(form.getTrialType());
        problem.setAutoGrade(form.getAutoGrade());

        problemRepository.save(problem);
        return problem;
    }

    private static void validateProblemUpdateFormData(ProblemUpdateForm form) {
        if (form.getTitle() == null || form.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        }
        if (form.getDescription() == null || form.getDescription().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        if (form.getTrialType() == null || form.getTrialType().isEmpty()) {
            throw new IllegalArgumentException("문제의 유형을 선택해주세요.");
        }
    }

    public boolean deleteById(Long problemId) {
        return problemRepository.deleteByProblemId(problemId);
    }
}
