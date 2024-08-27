package rlguswn.trial_chamber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.service.MemberService;

import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
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

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginMemberForm";
    }

//    @PostMapping("/members/login")
//    public String login(MemberLoginForm form, Model model) {
//        Optional<Member> member = memberService.login(form.getUsername(), form.getPassword());
//        if (member.isPresent()) {
//            return "redirect:/";
//        } else {
//            model.addAttribute("error", "이름 혹은 비밀번호를 다시 한번 확인해주세요.");
//            return "members/loginMemberForm";
//        }
//    }
}
