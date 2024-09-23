package rlguswn.trial_chamber.dto;

public class SubmissionForm {

    private Long problemId;
    private String content;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
