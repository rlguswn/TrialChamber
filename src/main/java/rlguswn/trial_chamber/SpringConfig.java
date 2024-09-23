package rlguswn.trial_chamber;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rlguswn.trial_chamber.repository.*;
import rlguswn.trial_chamber.service.MemberService;
import rlguswn.trial_chamber.service.PostService;
import rlguswn.trial_chamber.service.ProblemService;
import rlguswn.trial_chamber.service.SubmissionService;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public ProblemService problemService() {
        return new ProblemService(problemRepository());
    }

    @Bean
    public SubmissionService submissionService() {
        return new SubmissionService(submissionRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public PostRepository postRepository() {
        return new JpaPostRepository(em);
    }

    @Bean
    public ProblemRepository problemRepository() {
        return new JpaProblemRepository(em);
    }

    @Bean
    public SubmissionRepository submissionRepository() {
        return new JpaSubmissionRepository(em);
    }
}
