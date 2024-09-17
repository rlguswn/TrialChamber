package rlguswn.trial_chamber.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.domain.Problem;
import rlguswn.trial_chamber.domain.Submission;
import rlguswn.trial_chamber.dto.SubmissionForm;
import rlguswn.trial_chamber.dto.SubmissionUpdateForm;
import rlguswn.trial_chamber.repository.SubmissionRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class SubmissionService {

    @Autowired
    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public Long createSubmission(SubmissionForm form, Problem problem, Member member) {
        validateSubmissionFormData(form);

        Submission submission = new Submission(
                problem,
                member,
                form.getContent()
        );

        submissionRepository.save(submission);
        return submission.getId();
    }

    private static void validateSubmissionFormData(SubmissionForm form) {
        if (form.getContent() == null || form.getContent().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
    }

    public List<Submission> findSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> findOne(Long submissionId) {
        return submissionRepository.findById(submissionId);
    }

    public List<Submission> findSubmissionsByPostId(Long postId) {
        return submissionRepository.findByPostId(postId);
    }

    public Submission updateSubmission(Long submissionId, SubmissionUpdateForm form) {
        validateSubmissionUpdateFormData(form);

        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("제출이력을 찾을 수 없습니다."));

        submission.setContent(form.getContent());

        submissionRepository.save(submission);
        return submission;
    }

    private static void validateSubmissionUpdateFormData(SubmissionUpdateForm form) {
        if (form.getContent() == null || form.getContent().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
    }
}
