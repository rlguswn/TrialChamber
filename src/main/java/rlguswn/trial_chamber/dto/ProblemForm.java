package rlguswn.trial_chamber.dto;

public class ProblemForm {

    private String title;
    private String description;
    private String answer;
    private String trialType;
    private Boolean autoGrade;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTrialType() {
        return trialType;
    }

    public void setTrialType(String trialType) {
        this.trialType = trialType;
    }

    public Boolean getAutoGrade() {
        return autoGrade;
    }

    public void setAutoGrade(Boolean autoGrade) {
        this.autoGrade = autoGrade;
    }
}
