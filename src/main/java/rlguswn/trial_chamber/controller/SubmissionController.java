package rlguswn.trial_chamber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.domain.Problem;
import rlguswn.trial_chamber.domain.Submission;
import rlguswn.trial_chamber.dto.SubmissionForm;
import rlguswn.trial_chamber.dto.SubmissionUpdateForm;
import rlguswn.trial_chamber.service.MemberService;
import rlguswn.trial_chamber.service.ProblemService;
import rlguswn.trial_chamber.service.SubmissionService;

import java.util.List;
import java.util.Optional;

@Controller
public class SubmissionController {

    private final MemberService memberService;
    private final ProblemService problemService;
    private final SubmissionService submissionService;

    public SubmissionController(MemberService memberService, ProblemService problemService, SubmissionService submissionService) {
        this.memberService = memberService;
        this.problemService = problemService;
        this.submissionService = submissionService;
    }

    @GetMapping("/submission/new")
    public String createSubmissionForm() {
        return "submissions/createSubmissionForm";
    }

    @PostMapping("/submission/new")
    public String createSubmission(SubmissionForm form, Model model) {
        Member member = memberService.getLoginMember();
        Optional<Problem> problem = problemService.findOne(form.getProblemId());
        if (problem.isPresent()) {
            Long postId = problem.get().getPost().getId();
            submissionService.createSubmission(form, problem.get(), member);
            return "redirect:/post/" + postId;
        }
        model.addAttribute("errorMessage", "문제 정보를 찾을 수 없습니다.");
        return "redirect:/submission/new";
    }

    @GetMapping("/submission")
    public String submissionList(Model model) {
        List<Submission> submissions = submissionService.findSubmissions();
        model.addAttribute("submissions", submissions);
        return "submissions/submissionList";
    }

    @GetMapping("/submission/{submissionId}")
    public String submissionDetail(@PathVariable Long submissionId, Model model) {
        Optional<Submission> submission = submissionService.findOne(submissionId);
        if (submission.isPresent()) {
            model.addAttribute("submission", submission);
        } else {
            model.addAttribute("errorMessage", "풀이 정보를 찾을 수 없습니다.");
        }
        return "submissions/submissionDetail";
    }

    @GetMapping("/submission/{submissionId}/edit")
    public String submissionEditForm(@PathVariable Long submissionId, Model model) {
        Optional<Submission> submission = submissionService.findOne(submissionId);
        if (submission.isPresent()) {
            model.addAttribute("submission", submission);
        } else {
            model.addAttribute("errorMessage", "풀이 정보를 찾을 수 없습니다.");
        }
        return "submissions/updateSubmissionForm";
    }

    @PostMapping("/submission/{submissionId}/edit")
    public String submissionEdit(@PathVariable Long submissionId, Model model, SubmissionUpdateForm form) {
        Submission submission = submissionService.updateSubmission(submissionId, form);
        return "submissions/submissionDetail";
    }

    @PostMapping("/submission/{submissionId}/delete")
    public String submissionDelete(@PathVariable Long submissionId, Model model) {
        boolean result = submissionService.deleteById(submissionId);
        if (result) {
            model.addAttribute("notice", "성공적으로 삭제되었습니다.");
            return "redirect:/submissions";
        }
        model.addAttribute("errorMessage", "삭제 과정에 문제가 발생했습니다.");
        return "redirect:/submissions/" + submissionId;
    }

    @GetMapping("/submission/my-info")
    public String mySubmission(Model model) {
        Member member = memberService.getLoginMember();
        List<Submission> submissions = submissionService.findSubmissionByMemberId(member.getId());
        model.addAttribute("submissions", submissions);
        return "submissions/submissionList";
    }

    @GetMapping("/submission/members")
    public String submissionByMember(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "submissions/submissionMemberList";
    }

    @GetMapping("/submission/members/{memberId}")
    public String submissionByMemberDetail(@PathVariable Long memberId, Model model) {
        List<Submission> submissions = submissionService.findSubmissionByMemberId(memberId);
        model.addAttribute("submissions", submissions);
        return "submissions/submissionList";
    }

    @GetMapping("/submission/post/{postId}")
    public String submissionByPostDetail(@PathVariable Long postId, Model model) {
        List<Submission> submissions = submissionService.findSubmissionsByPostId(postId);
        model.addAttribute("submissions", submissions);
        return "submissions/submissionList";
    }
}
