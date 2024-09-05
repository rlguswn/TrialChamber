package rlguswn.trial_chamber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.dto.PostForm;
import rlguswn.trial_chamber.service.MemberService;
import rlguswn.trial_chamber.service.PostService;

@Controller
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @Autowired
    public PostController(PostService postService, MemberService memberService, MemberService memberService1) {
        this.postService = postService;
        this.memberService = memberService1;
    }

    @GetMapping("/posts/new")
    public String createPostForm() {
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String createPost(PostForm form) {
        Member member = memberService.getLoginMember();
        postService.createPost(form, member);
        return "redirect:/";
    }
}
