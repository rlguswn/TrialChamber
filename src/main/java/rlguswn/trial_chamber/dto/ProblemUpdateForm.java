package rlguswn.trial_chamber.dto;

public class ProblemUpdateForm {

    private String title;
    private String description;
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
