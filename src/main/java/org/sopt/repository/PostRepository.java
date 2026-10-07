package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class PostRepository {
    private final Map<Long, Post> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    public Long generateId() {
        return sequence.incrementAndGet();
    }

    public Post save(Post post) {
        store.put(post.getId(), post);
        return post;
    }

    public List<Post> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(Post::getId))
                .toList();
    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void delete(Post post) {
        store.remove(post.getId());
    }
}
