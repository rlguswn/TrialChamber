package rlguswn.trial_chamber.repository;

import rlguswn.trial_chamber.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);
    List<Member> findAll();
}