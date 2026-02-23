package com.example.vibeapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        Post post = postService.getPostByNo(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
            model.addAttribute("post", post);
            return "post_detail";
        }
        return "redirect:/posts";
    }

    @GetMapping("/posts/new")
    public String newForm() {
        return "post_new_form";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/add")
    public String add(String title, String content) {
        postService.createPost(title, content);
        return "redirect:/posts";
    }
}
