package rlguswn.trial_chamber;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rlguswn.trial_chamber.repository.JpaMemberRepository;
import rlguswn.trial_chamber.repository.JpaPostRepository;
import rlguswn.trial_chamber.repository.MemberRepository;
import rlguswn.trial_chamber.repository.PostRepository;
import rlguswn.trial_chamber.service.MemberService;
import rlguswn.trial_chamber.service.PostService;

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
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public PostRepository postRepository() {
        return new JpaPostRepository(em);
    }
}
