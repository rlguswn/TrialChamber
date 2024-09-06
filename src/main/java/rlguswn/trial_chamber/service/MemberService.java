package rlguswn.trial_chamber.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.dto.MemberForm;
import rlguswn.trial_chamber.dto.MemberUpdateForm;
import rlguswn.trial_chamber.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Transactional
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(MemberForm form) {
        validateDuplicateMember(form);
        validateMemberFormData(form);

        String encodedParssword = bCryptPasswordEncoder.encode(form.getPassword());
        Member member = new Member(
                form.getUsername(),
                encodedParssword,
                form.getName(),
                "ROLE_Pending"
        );

        memberRepository.save(member);
        return member.getId();
    }

    private static void validateMemberFormData(MemberForm form) {
        if (form.getUsername() == null || form.getUsername().isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력해주세요.");
        }
        if (form.getUsername().length() < 4 || form.getUsername().length() > 16) {
            throw new IllegalArgumentException("아이디는 4 ~ 16 글자를 사용해야 합니다.");
        }
        if (!Pattern.compile("[a-zA-Z]").matcher(form.getUsername()).find()) {
            throw new IllegalArgumentException("아이디에 문자가 존재하지 않습니다.");
        }

        if (form.getPassword() == null || form.getPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요");
        }
        if (form.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8 글자 이상을 사용해야 합니다.");
        }

        if (form.getName() == null || form.getName().isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
        if (form.getName().length() < 2) {
            throw new IllegalArgumentException("이름은 최소 2 글자를 사용해야 합니다.");
        }
    }

    private void validateDuplicateMember(MemberForm form) {
        memberRepository.findByUsername(form.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));
    }

    public Optional<Member> login(String username, String password) {
        return memberRepository.findByUsername(username)
                .filter(member -> member.getPassword().equals(password));
    }

    public Member getLoginMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));
    }

    public Long updateMember(MemberUpdateForm form) {
        validateMemberUpdateFormData(form);
        Member member = memberRepository.findByUsername(form.getUsername())
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));
        if (identification(member, form.getPassword())) {
            member.setName(form.getName());

            memberRepository.save(member);
            return member.getId();
        }
        throw new RuntimeException("본인의 정보만 수정할 수 있습니다.");
    }

    public Boolean identification(Member member, String password) {
        return member.equals(getLoginMember()) &&
                bCryptPasswordEncoder.matches(password, member.getPassword());
    }

    private static void validateMemberUpdateFormData(MemberUpdateForm form) {
        if (form.getUsername() == null || form.getUsername().isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력해주세요.");
        }
        if (form.getUsername().length() < 4 || form.getUsername().length() > 16) {
            throw new IllegalArgumentException("아이디는 4 ~ 16 글자를 사용해야 합니다.");
        }
        if (!Pattern.compile("[a-zA-Z]").matcher(form.getUsername()).find()) {
            throw new IllegalArgumentException("아이디에 문자가 존재하지 않습니다.");
        }

        if (form.getPassword() == null || form.getPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요");
        }
        if (form.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8 글자 이상을 사용해야 합니다.");
        }

        if (form.getName() == null || form.getName().isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
        if (form.getName().length() < 2) {
            throw new IllegalArgumentException("이름은 최소 2 글자를 사용해야 합니다.");
        }
    }
}
