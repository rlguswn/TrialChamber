package rlguswn.trial_chamber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.dto.MemberForm;
import rlguswn.trial_chamber.dto.MemberUpdateForm;
import rlguswn.trial_chamber.service.MemberService;

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
        memberService.join(form);
        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디와 비밀번호를 다시 확인해주세요.");
        }
        return "members/loginMemberForm";
    }

    @GetMapping("/members/my-info")
    public String myInfo(Model model) {
        Member member = memberService.getLoginMember();
        if (member != null) {
            model.addAttribute("member", member);
        } else {
            model.addAttribute("errorMessage", "멤버 정보를 찾을 수 없습니다.");
        }
        return "members/myInfo";
    }

    @GetMapping("/members/my-info/edit")
    public String myInfoEditForm(Model model, MemberUpdateForm form) {
        Member member = memberService.getLoginMember();
        if (member != null) {
            form.setName(member.getName());
            form.setUsername(member.getUsername());
            model.addAttribute("form", form);
        } else {
            model.addAttribute("errorMessage", "멤버 정보를 찾을 수 없습니다.");
        }
        return "members/updateMemberForm";
    }

    @PostMapping("/members/my-info/edit")
    public String myInfoEdit(Model model, MemberUpdateForm form) {
        memberService.updateMember(form);
        return "redirect:/members/my-info";
    }
}
