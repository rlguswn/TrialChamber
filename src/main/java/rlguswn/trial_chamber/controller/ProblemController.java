package rlguswn.trial_chamber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.domain.Post;
import rlguswn.trial_chamber.domain.Problem;
import rlguswn.trial_chamber.dto.ProblemForm;
import rlguswn.trial_chamber.dto.ProblemUpdateForm;
import rlguswn.trial_chamber.service.MemberService;
import rlguswn.trial_chamber.service.PostService;
import rlguswn.trial_chamber.service.ProblemService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProblemController {

    private final ProblemService problemService;
    private final MemberService memberService;
    private final PostService postService;

    public ProblemController(ProblemService problemService, MemberService memberService, PostService postService) {
        this.problemService = problemService;
        this.memberService = memberService;
        this.postService = postService;
    }

    @GetMapping("/post/{postId}/problem/new")
    public String createProblemForm(@PathVariable Long postId, Model model) {
        Optional<Post> post = postService.findOne(postId);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        } else {
            model.addAttribute("errorMessage", "포스트 정보를 찾을 수 없습니다.");
            return "redirect:/post";
        }
        return "problems/createProblemForm";
    }

    @PostMapping("/post/{postId}/problem/new")
    public String createProblem(@PathVariable Long postId, ProblemForm form) {
        Member member = memberService.getLoginMember();
        Optional<Post> post = postService.findOne(postId);
        post.ifPresent(value -> problemService.createProblem(form, member, value));
        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/{postId}/problem")
    public String problemPostList(@PathVariable Long postId, Model model) {
        List<Problem> problems = problemService.findProblemsByPostId(postId);
        model.addAttribute("problems", problems);
        return "problems/problemPostList";
    }

    @GetMapping("/problem")
    public String problemList(Model model) {
        List<Problem> problems = problemService.findProblems();
        model.addAttribute("problems", problems);
        return "problems/problemList";
    }

    @GetMapping("/problem/{problemId}")
    public String problemDetail(@PathVariable Long problemId, Model model) {
        Optional<Problem> problem = problemService.findOne(problemId);
        if (problem.isPresent()) {
            model.addAttribute("problem", problem);
        } else {
            model.addAttribute("errorMessage", "문제 정보를 찾을 수 없습니다.");
        }
        return "problems/problemDetail";
    }

    @GetMapping("/problem/{problemId}/edit")
    public String problemEditForm(@PathVariable Long problemId, Model model) {
        Optional<Problem> problem = problemService.findOne(problemId);
        if (problem.isPresent()) {
            model.addAttribute("problem", problem);
        } else {
            model.addAttribute("errorMessage", "문제 정보를 찾을 수 없습니다.");
        }
        return "problems/updateProblemForm";
    }

    @PostMapping("/problem/{problemId}/edit")
    public String problemEdit(@PathVariable Long problemId, Model model, ProblemUpdateForm form) {
        Problem problem = problemService.updateProblem(problemId, form);
        model.addAttribute("problem", problem);
        return "problems/problemDetail";
    }

    @PostMapping("/problem/{problemId}/delete")
    public String problemDelete(@PathVariable Long problemId, Model model) {
        boolean result = problemService.deleteById(problemId);
        if (result) {
            model.addAttribute("notice", "성공적으로 삭제되었습니다.");
            return "redirect:/problem";
        }
        model.addAttribute("errorMessage", "삭제 과정에 문제가 발생했습니다.");
        return "redirect:/problem/" + problemId;
    }
}
