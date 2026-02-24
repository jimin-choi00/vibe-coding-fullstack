package com.example.vibeapp.post;

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
    public String list(
            @org.springframework.web.bind.annotation.RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {
        int size = 5;
        model.addAttribute("posts", postService.getPostsPage(page, size));

        long totalPosts = postService.getTotalPostsCount();
        int totalPages = (int) Math.ceil((double) totalPosts / size);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalPosts", totalPosts);

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

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        Post post = postService.getPostByNo(no);
        if (post != null) {
            model.addAttribute("post", post);
            return "post_edit_form";
        }
        return "redirect:/posts";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/{no}/save")
    public String update(@PathVariable("no") Long no, String title, String content) {
        postService.updatePost(no, title, content);
        return "redirect:/posts/" + no;
    }

    @GetMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.deletePost(no);
        return "redirect:/posts";
    }
}
