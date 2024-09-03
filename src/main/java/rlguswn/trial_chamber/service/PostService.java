package rlguswn.trial_chamber.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import rlguswn.trial_chamber.domain.Post;
import rlguswn.trial_chamber.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long createPost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> findPostsByMemberId(Long memberId) {
        return postRepository.findByMemberId(memberId);
    }
}
