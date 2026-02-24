package com.example.vibeapp;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsPage(int page, int size) {
        return postRepository.findPage(page, size);
    }

    public long getTotalPostsCount() {
        return postRepository.getTotalCount();
    }

    public Post getPostByNo(Long no) {
        return postRepository.findByNo(no);
    }

    public void createPost(String title, String content) {
        Post post = new Post(title, content);
        postRepository.save(post);
    }

    public void updatePost(Long no, String title, String content) {
        Post post = new Post();
        post.setNo(no);
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
