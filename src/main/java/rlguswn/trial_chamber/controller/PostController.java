package rlguswn.trial_chamber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rlguswn.trial_chamber.domain.Member;
import rlguswn.trial_chamber.domain.Post;
import rlguswn.trial_chamber.dto.PostForm;
import rlguswn.trial_chamber.dto.PostUpdateForm;
import rlguswn.trial_chamber.service.MemberService;
import rlguswn.trial_chamber.service.PostService;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @Autowired
    public PostController(PostService postService, MemberService memberService) {
        this.postService = postService;
        this.memberService = memberService;
    }

    @GetMapping("/post/new")
    public String createPostForm() {
        return "posts/createPostForm";
    }

    @PostMapping("/post/new")
    public String createPost(PostForm form) {
        Member member = memberService.getLoginMember();
        Long postId = postService.createPost(form, member);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/{postId}")
    public String postDetail(@PathVariable Long postId, Model model) {
        Optional<Post> post = postService.findOne(postId);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        } else {
            model.addAttribute("errorMessage", "포스트 정보를 찾을 수 없습니다.");
        }
        return "posts/postDetail";
    }

    @GetMapping("/post")
    public String postList(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/post/{postId}/edit")
    public String postEditForm(@PathVariable Long postId, Model model) {
        Optional<Post> post = postService.findOne(postId);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        } else {
            model.addAttribute("errorMessage", "포스트 정보를 찾을 수 없습니다.");
        }
        return "posts/updatePostForm";
    }

    @PostMapping("/post/{postId}/edit")
    public String postEdit(@PathVariable Long postId, Model model, PostUpdateForm form) {
        Post post = postService.updatePost(postId, form);
        model.addAttribute("post", post);
        return "posts/postDetail";
    }

    @PostMapping("/post/{postId}/delete")
    public String postDelete(@PathVariable Long postId, Model model) {
        boolean result = postService.deleteById(postId);
        if (result) {
            model.addAttribute("notice", "성공적으로 삭제되었습니다.");
            return "redirect:/post";
        }
        model.addAttribute("errorMessage", "삭제 과정에 문제가 발생했습니다.");
        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/before")
    public String postBefore(Model model) {
        List<Post> posts = postService.findPostsBeforeDeadline();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/post/after")
    public String postAfter(Model model) {
        List<Post> posts = postService.findPostsAfterDeadline();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/post/temporary")
    public String postTemporary(Model model) {
        List<Post> posts = postService.findPostsTemporary();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }
}
