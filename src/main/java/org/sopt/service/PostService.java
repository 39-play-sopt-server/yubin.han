package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostSummaryResponse;
import org.sopt.dto.PostUpdateRequest;
import org.sopt.exception.PostNotFoundException;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long createPost(PostCreateRequest request) {
        Post post = new Post(
                postRepository.generateId(),
                request.category(),
                request.title(),
                request.content(),
                request.author()
        );
        return postRepository.save(post).getId();
    }

    public List<PostSummaryResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostSummaryResponse::from)
                .toList();
    }

    public PostResponse getPost(Long id) {
        Post post = findPost(id);
        post.increaseViewCount();
        return PostResponse.from(post);
    }

    public void validatePostExists(Long id) {
        findPost(id);
    }

    public void updatePost(Long id, PostUpdateRequest request) {
        Post post = findPost(id);
        post.update(request.category(), request.title(), request.content());
    }

    public void deletePost(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }
}
