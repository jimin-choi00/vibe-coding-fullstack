package com.example.vibeapp;

import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        // Init sample data
        for (long i = 1; i <= 10; i++) {
            posts.add(new Post(i, "Vibe Coding Post " + i, "This is the content for post " + i,
                    LocalDateTime.now().minusDays(10 - i), (int) (Math.random() * 100)));
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public List<Post> findPage(int page, int size) {
        int fromIndex = page * size;
        if (fromIndex >= posts.size()) {
            return new ArrayList<>();
        }
        int toIndex = Math.min(fromIndex + size, posts.size());

        // Sort by 'no' descending to show latest posts first
        List<Post> sortedPosts = new ArrayList<>(posts);
        sortedPosts.sort((p1, p2) -> p2.getNo().compareTo(p1.getNo()));

        return sortedPosts.subList(fromIndex, toIndex);
    }

    public long getTotalCount() {
        return posts.size();
    }

    public void save(Post post) {
        if (post.getNo() == null) {
            long maxNo = posts.stream()
                    .mapToLong(Post::getNo)
                    .max()
                    .orElse(0L);
            post.setNo(maxNo + 1);
            posts.add(post);
        } else {
            Post existing = findByNo(post.getNo());
            if (existing != null) {
                existing.setTitle(post.getTitle());
                existing.setContent(post.getContent());
                existing.setUpdatedAt(LocalDateTime.now());
            }
        }
    }

    public Post findByNo(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst()
                .orElse(null);
    }

    public void deleteByNo(Long no) {
        posts.removeIf(post -> post.getNo().equals(no));
    }
}
