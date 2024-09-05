package rlguswn.trial_chamber.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.domain.Post;
import rlguswn.trial_chamber.dto.PostForm;
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

    public Long createPost(PostForm form, Member member) {
        validatePostFormData(form);

        Post post = new Post(
                member,
                form.getTitle(),
                form.getDescription(),
                form.getDeadline(),
                form.getPreviewImage()
        );

        postRepository.save(post);
        return post.getId();
    }

    private static void validatePostFormData(PostForm form) {
        if (form.getTitle() == null || form.getTitle().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        }
        if (form.getDescription() == null || form.getDescription().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
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
