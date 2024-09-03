package rlguswn.trial_chamber.repository;

import rlguswn.trial_chamber.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findByMemberId(Long memberId);
    List<Post> findAll();
}
