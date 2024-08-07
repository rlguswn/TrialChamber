package rlguswn.trial_chamber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());

        memberService.join(member);
        return "redirect:/";
    }
}
