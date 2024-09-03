package rlguswn.trial_chamber.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rlguswn.trial_chamber.domain.Member;
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

    public Long join(Member member) {
        validateDuplicateMember(member);
        validateMemberData(member);

        String encodedParssword = bCryptPasswordEncoder.encode(member.getPassword());
        member.setPassword(encodedParssword);

        member.setRole("ROLE_USER");
        memberRepository.save(member);
        return member.getId();
    }

    private static void validateMemberData(Member member) {
        if (member.getUsername() == null || member.getUsername().isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
        if (member.getUsername().length() < 4 || member.getUsername().length() > 16) {
            throw new IllegalArgumentException("이름은 4 ~ 16 글자를 사용해야 합니다.");
        }
        if (!Pattern.compile("[a-zA-Z]").matcher(member.getUsername()).find()) {
            throw new IllegalArgumentException("이름에 문자가 존재하지 않습니다.");
        }

        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요");
        }
        if (member.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8 글자 이상을 사용해야 합니다.");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByUsername(member.getUsername())
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
                .orElseThrow(() -> new RuntimeException(username + "유저는 존재하지 않습니다."));
    }

    public Optional<Member> login(String username, String password) {
        return memberRepository.findByUsername(username)
                .filter(member -> member.getPassword().equals(password));
    }
}
